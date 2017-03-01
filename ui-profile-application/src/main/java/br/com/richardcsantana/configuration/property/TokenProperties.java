package br.com.richardcsantana.configuration.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author richard.santana
 */
@ConfigurationProperties(prefix = "oauth.connection")
public class TokenProperties {

	@Getter
	@Setter
	private String authorizationUrl;
	@Getter
	@Setter
	private String redirectUrl;
	@Getter
	@Setter
	private String clientId;
	@Getter
	@Setter
	private String clientSecret;
	@Getter
	@Setter
	private String tokenUrl;
}
