package br.com.richardcsantana.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * @author richard.santana
 */
public class ProfileInformation {

	@JsonProperty("user_name")
	@Getter
	@Setter
	private String userName;
}
