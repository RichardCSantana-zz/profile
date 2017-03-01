package br.com.richardcsantana.web;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author richard.santana
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@RequestMapping(method = RequestMethod.GET)
	public Principal getPrincipal(final Principal user) {
		return user;
	}

}
