package br.com.richardcsantana.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.richardcsantana.service.CacheService;
import lombok.RequiredArgsConstructor;

/**
 * @author richard.santana
 */
@RestController
@RequestMapping(value = "/cache")
@RequiredArgsConstructor
public class CacheController {

	private final CacheService cacheService;

	@RequestMapping(value = "/load/users", method = RequestMethod.GET)
	public void loadUsers() {
		this.cacheService.loadUserCache();
	}

	@RequestMapping(value = "/load/clients", method = RequestMethod.GET)
	public void loadClients() {
		this.cacheService.loadClientCache();
	}
}
