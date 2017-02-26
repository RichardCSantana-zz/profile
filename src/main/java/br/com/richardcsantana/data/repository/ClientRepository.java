package br.com.richardcsantana.data.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.richardcsantana.data.model.Client;

/**
 * @author richard.santana
 */
public interface ClientRepository extends CrudRepository<Client, String> {

	@Override
	List<Client> findAll();

}
