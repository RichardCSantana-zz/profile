package br.com.richardcsantana.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientAlreadyExistsException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.stereotype.Service;

import br.com.richardcsantana.data.model.Client;
import br.com.richardcsantana.data.repository.ClientRepository;
import br.com.richardcsantana.service.CustomClientDetailsService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author richard.santana
 */
@Service
@RequiredArgsConstructor
public class CustomClientDetailsServiceImpl implements CustomClientDetailsService {

	@Setter
	@Getter
	private PasswordEncoder passwordEncoder;

	private final ClientRepository clientRepository;

	@Override
	public ClientDetails loadClientByClientId(final String clientId) throws ClientRegistrationException {
		final Optional<Client> details = Optional.ofNullable(this.clientRepository.findOne(clientId));
		return details.orElseThrow(() -> new NoSuchClientException("No client with requested id: " + clientId));
	}

	@Override
	public void addClientDetails(final ClientDetails clientDetails) throws ClientAlreadyExistsException {
		final Client toSave = new Client();
		BeanUtils.copyProperties(clientDetails, toSave);
		toSave.setClientSecret(encode(clientDetails.getClientSecret()));
		try {
			this.clientRepository.save((Client) clientDetails);
		} catch (final DuplicateKeyException ex) {
			throw new ClientAlreadyExistsException(
					"Client already exists: " + clientDetails.getClientId(), ex);
		}
	}

	private String encode(final String password) {
		return this.passwordEncoder == null ? password : this.passwordEncoder.encode(password);
	}

	@Override
	public void updateClientDetails(final ClientDetails clientDetails) throws NoSuchClientException {
		Optional.ofNullable(this.clientRepository.findOne(clientDetails.getClientId())).ifPresent(
				toSave -> {
					BeanUtils.copyProperties(clientDetails, toSave);
					this.clientRepository.save((Client) clientDetails);
				}
		);
	}

	@Override
	public void updateClientSecret(final String clientId, final String secret) throws NoSuchClientException {
		Optional.ofNullable(this.clientRepository.findOne(clientId)).ifPresent(
				toSave -> {
					toSave.setClientSecret(encode(secret));
					this.clientRepository.save(toSave);
				}
		);
	}

	@Override
	public void removeClientDetails(final String clientId) throws NoSuchClientException {
		this.clientRepository.delete(clientId);
	}

	@Override
	public List<ClientDetails> listClientDetails() {
		return this.clientRepository.findAll().stream()
				.map(client -> (ClientDetails) client)
				.collect(Collectors.toList());
	}
}
