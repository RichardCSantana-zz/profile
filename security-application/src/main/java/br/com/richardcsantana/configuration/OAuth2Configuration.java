package br.com.richardcsantana.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

import br.com.richardcsantana.configuration.property.JwtProperty;
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
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Getter
	private final AuthenticationManager authenticationManager;

	private final CustomClientDetailsService clientDetailsService;

	private final UserDetailsService userDetailsService;

	private final JwtProperty jwtProperty;

	@Bean
	public TokenStore getTokenStore() {
		return new JwtTokenStore(getAccessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter getAccessTokenConverter() {
		final JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		final KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(
				this.jwtProperty.getPath(), this.jwtProperty.getKeyPass().toCharArray());
		jwtAccessTokenConverter.setKeyPair(keyStoreKeyFactory.getKeyPair(this.jwtProperty.getAlias(),
				this.jwtProperty.getKeyPass().toCharArray()));
		return jwtAccessTokenConverter;
	}

	@Override
	public void configure(final AuthorizationServerSecurityConfigurer security) throws Exception {
		security.passwordEncoder(this.getPasswordEncoder()).tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
		this.clientDetailsService.setPasswordEncoder(getPasswordEncoder());
		clients.withClientDetails(this.clientDetailsService);
	}

	@Override
	public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(getTokenStore()).authenticationManager(getAuthenticationManager())
				.userDetailsService(this.userDetailsService).accessTokenConverter(getAccessTokenConverter());
	}
}
