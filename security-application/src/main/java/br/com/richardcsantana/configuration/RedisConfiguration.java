package br.com.richardcsantana.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import br.com.richardcsantana.configuration.property.ClusterConfigurationProperties;

/**
 * @author richard.santana
 */
@Configuration
public class RedisConfiguration {

	/**
	 * Type safe representation of application.properties
	 */
	@Autowired
	private ClusterConfigurationProperties clusterProperties;

	@Bean
	public RedisConnectionFactory connectionFactory() {

		return new JedisConnectionFactory(
				new RedisClusterConfiguration(this.clusterProperties.getNodes()));
	}

}
