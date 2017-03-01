package br.com.richardcsantana.data.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.richardcsantana.data.converter.AuthorityConverter;
import lombok.Getter;
import lombok.Setter;

/**
 * @author richard.santana
 */
@Entity(name = "user_table")
public class AppUser implements Serializable {

	@Id
	@Getter
	@Setter
	@JsonIgnore
	private Long id;
	@Getter
	@Setter
	private String username;
	@Getter
	@Setter
	@JsonIgnore
	private String password;
	@Getter
	@Setter
	private String email;
	@Getter
	@Setter
	@Convert(converter = AuthorityConverter.class)
	private Collection<GrantedAuthority> authorities;
	@Getter
	@Setter
	@JsonIgnore
	private boolean enabled;
}
