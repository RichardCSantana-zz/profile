package br.com.richardcsantana.data.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import br.com.richardcsantana.data.converter.AuthorityConverter;
import br.com.richardcsantana.data.converter.MapConverter;
import br.com.richardcsantana.data.converter.SetConverter;
import lombok.Getter;
import lombok.Setter;

/**
 * @author richard.santana
 */
@Entity(name = "client_table")
public class Client implements ClientDetails, Serializable {

	@Id
	@Setter
	@Getter
	private String clientId;

	@Getter
	@Setter
	private String clientSecret;

	@Getter
	@Setter
	@Convert(converter = SetConverter.class)
	private Set<String> resourceIds;

	@Getter
	@Setter
	private boolean secretRequired;

	@Getter
	@Setter
	private boolean scoped;

	@Getter
	@Setter
	@Convert(converter = SetConverter.class)
	private Set<String> scope;

	@Getter
	@Setter
	@Convert(converter = SetConverter.class)
	private Set<String> authorizedGrantTypes;

	@Getter
	@Setter
	@Convert(converter = SetConverter.class)
	private Set<String> registeredRedirectUri;

	@Getter
	@Setter
	@Convert(converter = AuthorityConverter.class)
	private Collection<GrantedAuthority> authorities;

	@Getter
	@Setter
	private Integer accessTokenValiditySeconds;

	@Getter
	@Setter
	private Integer refreshTokenValiditySeconds;

	@Getter
	@Setter
	@Convert(converter = MapConverter.class)
	private Map<String, Object> additionalInformation;

	@Override
	public boolean isAutoApprove(final String scope) {
		return true;
	}
}
