package br.com.richardcsantana.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.richardcsantana.service.CustomClientDetailsService;
import lombok.RequiredArgsConstructor;

/**
 * @author richard.santana
 */
@RestController
@RequestMapping(value = "/client")
@RequiredArgsConstructor
public class ClientController {

	private final CustomClientDetailsService clientDetailsService;

}
