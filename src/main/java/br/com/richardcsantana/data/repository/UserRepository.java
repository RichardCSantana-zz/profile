package br.com.richardcsantana.data.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.richardcsantana.data.model.AppUser;

/**
 * @author richard.santana
 */
public interface UserRepository extends CrudRepository<AppUser, Long> {

	Optional<AppUser> findByUsernameAndEnabledTrue(String username);
}

