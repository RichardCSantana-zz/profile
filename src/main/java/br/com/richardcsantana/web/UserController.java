package br.com.richardcsantana.web;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author richard.santana
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@RequestMapping
	public Principal getPrincipal(final Principal user) {
		return user;
	}

}
