package br.com.richardcsantana.data.converter;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

/**
 * @author richard.santana
 */
@Converter
public class AuthorityConverter implements AttributeConverter<Collection<GrantedAuthority>, String> {
	@Override
	public String convertToDatabaseColumn(final Collection<GrantedAuthority> grantedAuthorities) {
		return AuthorityUtils.authorityListToSet(grantedAuthorities).stream().collect(Collectors.joining(","));
	}

	@Override
	public Collection<GrantedAuthority> convertToEntityAttribute(final String s) {
		return AuthorityUtils.commaSeparatedStringToAuthorityList(s);
	}
}
