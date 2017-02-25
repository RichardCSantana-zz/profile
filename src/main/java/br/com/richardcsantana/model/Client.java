package br.com.richardcsantana.model;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import lombok.Getter;
import lombok.Setter;

/**
 * @author richard.santana
 */
public class Client implements ClientDetails {

	@Id
	@Setter
	@Getter
	private String clientId;

	@Getter
	@Setter
	private String clientSecret;

	@Getter
	@Setter
	private Set<String> resourceIds;

	@Getter
	@Setter
	private boolean secretRequired;

	@Getter
	@Setter
	private boolean scoped;

	@Getter
	@Setter
	private Set<String> scope;

	@Getter
	@Setter
	private Set<String> authorizedGrantTypes;

	@Getter
	@Setter
	private Set<String> registeredRedirectUri;

	@Getter
	@Setter
	private Collection<GrantedAuthority> authorities;

	@Getter
	@Setter
	private Integer accessTokenValiditySeconds;

	@Getter
	@Setter
	private Integer refreshTokenValiditySeconds;

	@Getter
	@Setter
	private Map<String, Object> additionalInformation;

	@Override
	public boolean isAutoApprove(final String scope) {
		return false; //TODO validar fluxo autoapprove
	}
}
