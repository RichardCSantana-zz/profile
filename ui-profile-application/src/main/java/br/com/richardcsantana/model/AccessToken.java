package br.com.richardcsantana.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * @author richard.santana
 */
public class AccessToken {

	@Getter
	@Setter
	@JsonProperty("access_token")
	private String accessToken;
	@Getter
	@Setter
	@JsonProperty("refresh_token")
	private String refreshToken;
	@Getter
	@Setter
	@JsonProperty("token_type")
	private String tokenType;
	@Getter
	@Setter
	private String scope;
	@Getter
	@JsonProperty("expires_in")
	private int expiresIn;
	@Getter
	private LocalDateTime expiresTime;
	@Getter
	@Setter
	private String jti;

	public void setExpiresIn(final int expiresIn) {
		this.expiresIn = expiresIn;
		this.expiresTime = LocalDateTime.now().plusSeconds(expiresIn);
	}

}
