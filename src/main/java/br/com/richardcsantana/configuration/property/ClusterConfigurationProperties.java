package br.com.richardcsantana.configuration.property;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author richard.santana
 */
@ConfigurationProperties(prefix = "spring.redis.cluster")
public class ClusterConfigurationProperties {

	@Getter
	@Setter
	private List<String> nodes;

}
