package br.com.richardcsantana;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

import br.com.richardcsantana.configuration.ClusterConfigurationProperties;
import br.com.richardcsantana.configuration.property.JwtProperty;

@SpringBootApplication
@EnableConfigurationProperties(value = {JwtProperty.class, ClusterConfigurationProperties.class})
@EnableCaching
public class LoginApplication {

	public static void main(final String[] args) {
		SpringApplication.run(LoginApplication.class, args);
		//		System.out.println(new BCryptPasswordEncoder().encode("password"));
	}
}
