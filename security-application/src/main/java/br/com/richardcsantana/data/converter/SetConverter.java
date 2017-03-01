package br.com.richardcsantana.data.converter;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author richard.santana
 */
@Converter
public class SetConverter implements AttributeConverter<Set<String>, String> {

	@Override
	public String convertToDatabaseColumn(final Set<String> strings) {
		return strings.stream().collect(Collectors.joining(","));
	}

	@Override
	public Set<String> convertToEntityAttribute(final String s) {
		if (s == null) return null;
		return Arrays.stream(s.split(",")).collect(Collectors.toSet());
	}
}
