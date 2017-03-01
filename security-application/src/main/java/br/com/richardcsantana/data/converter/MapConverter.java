package br.com.richardcsantana.data.converter;

import java.io.IOException;
import java.util.Map;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author richard.santana
 */
@Converter
public class MapConverter implements AttributeConverter<Map<String, Object>, String> {

	private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	@Override
	public String convertToDatabaseColumn(final Map<String, Object> stringObjectMap) {
		String result = null;
		try {
			result = OBJECT_MAPPER.writeValueAsString(stringObjectMap);
		} catch (final JsonProcessingException e) {
			e.printStackTrace(); //TODO tratar exceção
		}
		return result;
	}

	@Override
	public Map<String, Object> convertToEntityAttribute(final String value) {
		Map map = null;
		try {
			map = OBJECT_MAPPER.readValue(value, Map.class);
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return map;
	}
}
