package br.com.richardcsantana.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import br.com.richardcsantana.service.CustomClientDetailsService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author richard.santana
 */
@Configuration
@EnableAuthorizationServer
@EnableResourceServer
@RequiredArgsConstructor
public class OAuth2Configuration extends AuthorizationServerConfigurerAdapter {

	@Getter
	private final PasswordEncoder passwordEncoder;

	@Getter
	private final AuthenticationManager authenticationManager;

	@Getter
	private final CustomClientDetailsService clientDetailsService;

	@Getter
	private final UserDetailsService userDetailsService;

	@Bean
	public TokenStore getTokenStore() {
		return new JwtTokenStore(getAccessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter getAccessTokenConverter() {
		final JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		final KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(
				new ClassPathResource(""), "".toCharArray()); //TODO use configurationProperties
		jwtAccessTokenConverter.setKeyPair(keyStoreKeyFactory.getKeyPair("")); //TODO use configuration properties
		return jwtAccessTokenConverter;
	}

	@Override
	public void configure(final AuthorizationServerSecurityConfigurer security) throws Exception {
		security.passwordEncoder(this.getPasswordEncoder()).tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
		this.clientDetailsService.setPasswordEncoder(getPasswordEncoder());
		clients.withClientDetails(getClientDetailsService());
	}

	@Override
	public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(getTokenStore()).authenticationManager(getAuthenticationManager())
				.userDetailsService(getUserDetailsService()).accessTokenConverter(getAccessTokenConverter());
	}
}
