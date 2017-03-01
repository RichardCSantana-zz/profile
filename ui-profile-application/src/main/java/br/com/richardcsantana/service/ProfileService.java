package br.com.richardcsantana.service;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.richardcsantana.model.AccessToken;
import br.com.richardcsantana.model.ProfileInformation;
import lombok.extern.slf4j.Slf4j;

/**
 * @author richard.santana
 */
@Service
@Slf4j
public class ProfileService {

	public ProfileInformation getProfileInformation(final AccessToken accessToken) {
		final String body = accessToken.getAccessToken().split("\\.")[1];
		final String profile = new String(Base64.getDecoder().decode(body));
		final ObjectMapper mapper = new ObjectMapper();
		final ProfileInformation profileInformation = new ProfileInformation();
		try {
			Optional.ofNullable(mapper.readValue(profile, Map.class)).ifPresent(map ->
					profileInformation.setUserName((String) map.get("user_name")));
		} catch (final IOException e) {
			log.error("Error reading ", body, e);
		}
		return profileInformation;
	}

}
