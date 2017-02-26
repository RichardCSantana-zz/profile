package br.com.richardcsantana.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import br.com.richardcsantana.data.model.AppUser;

/**
 * @author richard.santana
 */
public interface UserService extends UserDetailsService {
	AppUser save(AppUser appUser);

	AppUser update(String username, AppUser appUser);
}
