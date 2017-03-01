package br.com.richardcsantana.service;

import java.util.Base64;
import java.util.Collections;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import br.com.richardcsantana.configuration.property.TokenProperties;
import br.com.richardcsantana.model.AccessToken;
import lombok.RequiredArgsConstructor;

/**
 * @author richard.santana
 */
@Service
@RequiredArgsConstructor
public class TokenService {

	private final TokenProperties tokenProperties;

	public AccessToken getAccessToken(final String code) {
		final HttpEntity<MultiValueMap<String, String>> request =
				new HttpEntity<>(getTokenRequestMap(code), getHeader(this.tokenProperties.getClientId(),
						this.tokenProperties.getClientSecret()));

		final RestTemplate restTemplate = new RestTemplate();
		final ResponseEntity<AccessToken> exchange = restTemplate.exchange(this.tokenProperties.getTokenUrl(),
				HttpMethod.POST, request, AccessToken.class);
		return exchange.getBody();
	}

	private MultiValueMap<String, String> getTokenRequestMap(final String code) {
		final MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.put("client_id", Collections.singletonList(this.tokenProperties.getClientId()));
		map.put("grant_type", Collections.singletonList("authorization_code"));
		map.put("redirect_uri", Collections.singletonList(this.tokenProperties.getRedirectUrl()));
		map.put("code", Collections.singletonList(code));
		return map;
	}

	//Melhoria implementar o controle de tempo do token e refresh em caso de token expirado
	public AccessToken getRefreshToken(final AccessToken accessToken) {
		final String clientId = "newClient";
		final MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.put("refresh_token", Collections.singletonList(accessToken.getRefreshToken()));
		map.put("grant_type", Collections.singletonList("refresh_token"));
		final HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map,
				getHeader(this.tokenProperties.getClientId(), this.tokenProperties.getClientSecret()));

		final RestTemplate restTemplate = new RestTemplate();
		final ResponseEntity<AccessToken> exchange = restTemplate.exchange(this.tokenProperties.getTokenUrl(),
				HttpMethod.POST, request, AccessToken.class);
		return exchange.getBody();
	}

	private HttpHeaders getHeader(final String clientId, final String secret) {
		final HttpHeaders headers = new HttpHeaders();
		final String base64 = clientId + ":" + secret;
		headers.add(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder().encodeToString(base64.getBytes()));
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
		return headers;
	}

}
