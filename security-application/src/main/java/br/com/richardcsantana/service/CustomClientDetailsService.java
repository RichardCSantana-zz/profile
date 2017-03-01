package br.com.richardcsantana.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationService;

/**
 * @author richard.santana
 */
public interface CustomClientDetailsService extends ClientDetailsService, ClientRegistrationService {

	void setPasswordEncoder(PasswordEncoder passwordEncoder);
}
