package br.com.richardcsantana.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import br.com.richardcsantana.data.model.AppUser;
import br.com.richardcsantana.exception.ResourceNotFoundException;

/**
 * @author richard.santana
 */
public interface UserService extends UserDetailsService {
	AppUser save(AppUser appUser);

	AppUser update(String username, AppUser appUser) throws ResourceNotFoundException;
}
