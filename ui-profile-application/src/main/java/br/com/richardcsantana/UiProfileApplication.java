package br.com.richardcsantana;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import br.com.richardcsantana.configuration.property.TokenProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {TokenProperties.class})
public class UiProfileApplication {

	public static void main(final String[] args) {
		SpringApplication.run(UiProfileApplication.class, args);
	}
}
