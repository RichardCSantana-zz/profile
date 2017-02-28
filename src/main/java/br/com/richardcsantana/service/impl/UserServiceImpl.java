package br.com.richardcsantana.service.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.richardcsantana.data.model.AppUser;
import br.com.richardcsantana.data.repository.UserRepository;
import br.com.richardcsantana.exception.ResourceNotFoundException;
import br.com.richardcsantana.service.UserService;
import lombok.RequiredArgsConstructor;

/**
 * @author richard.santana
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	@Cacheable(value = "user", key = "#username")
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		User user = null;
		final Optional<AppUser> result = this.userRepository.findByUsernameAndEnabledTrue(username);
		if (result.isPresent()) {
			final AppUser appUser = result.get();
			user = new User(appUser.getUsername(), appUser.getPassword(), appUser.isEnabled(),
					true, true, true, appUser.getAuthorities());
		}
		return user;
	}

	@Override
	@CacheEvict(value = "user", key = "#appUser.username")
	public AppUser save(final AppUser appUser) {
		appUser.setPassword(encode(appUser.getPassword()));
		return this.userRepository.save(appUser);
	}

	@Override
	@CacheEvict(value = "user", key = "#username")
	public AppUser update(final String username, final AppUser appUser) throws ResourceNotFoundException {
		final Optional<AppUser> result = this.userRepository.findByUsernameAndEnabledTrue(username);
		if (result.isPresent()) {
			AppUser update = result.get();
			BeanUtils.copyProperties(appUser, update);
			update = this.userRepository.save(update);
			return update;
		}
		throw new ResourceNotFoundException("The user %s is not found", username);
	}

	private String encode(final String password) {
		return this.passwordEncoder == null ? password : this.passwordEncoder.encode(password);
	}
}
