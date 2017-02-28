package br.com.richardcsantana.exception;

/**
 * @author richard.santana
 */
public class ResourceNotFoundException extends Exception {
	public ResourceNotFoundException(final String marker, final String... values) {
		super(String.format(marker, values));
	}
}
