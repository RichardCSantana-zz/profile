package br.com.richardcsantana.service;

import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import br.com.richardcsantana.data.repository.ClientRepository;
import br.com.richardcsantana.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;

/**
 * @author richard.santana
 */
@Service
@RequiredArgsConstructor
public class CacheService {

	private final CacheManager cacheManager;
	private final UserRepository userRepository;
	private final ClientRepository clientRepository;

	public void loadClientCache() {
		this.clientRepository.findAll().forEach(client ->
				this.cacheManager.getCache("client").put(client.getClientId(), client));
	}

	public void loadUserCache() {
		this.userRepository.findAll().forEach(appUser ->
				this.cacheManager.getCache("user").put(appUser.getUsername(), appUser));
	}
}
