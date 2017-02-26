package br.com.richardcsantana.configuration.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

import lombok.Getter;
import lombok.Setter;

/**
 * @author richard.santana
 */
@ConfigurationProperties(prefix = "jwt")
public class JwtProperty {

	@Getter
	@Setter
	private Resource path;
	@Getter
	@Setter
	private String storePass;
	@Getter
	@Setter
	private String alias;
	@Getter
	@Setter
	private String keyPass;
}
