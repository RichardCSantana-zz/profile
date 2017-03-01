package br.com.richardcsantana.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.richardcsantana.configuration.property.TokenProperties;
import br.com.richardcsantana.model.AccessToken;
import br.com.richardcsantana.model.ProfileInformation;
import br.com.richardcsantana.service.ProfileService;
import br.com.richardcsantana.service.TokenService;
import lombok.RequiredArgsConstructor;

/**
 * @author richard.santana
 */
@Controller
@RequiredArgsConstructor
public class ProfileController {

	private final TokenService tokenService;
	private final ProfileService profileService;
	private final TokenProperties tokenProperties;

	@RequestMapping("/")
	public String profile(final HttpServletRequest request, final Model model) {
		final AccessToken accessToken = (AccessToken) request.getSession().getAttribute("access_token");
		final String destiny;
		if (accessToken == null) {
			destiny = String.format("redirect:%s?response_type=code&redirect_uri=%s&client_id=%s",
					this.tokenProperties.getAuthorizationUrl(), this.tokenProperties.getRedirectUrl(),
					this.tokenProperties.getClientId());
		} else {
			final ProfileInformation profileInformation = this.profileService.getProfileInformation(accessToken);
			model.addAttribute("profileInformation", profileInformation);
			destiny = "profile";
		}
		return destiny;
	}

	@RequestMapping("/login")
	public String login(final HttpServletRequest request, @RequestParam(required = false) final String code) {
		AccessToken accessToken = (AccessToken) request.getSession().getAttribute("access_token");
		if (accessToken == null && code != null) {
			accessToken = this.tokenService.getAccessToken(code);
			request.getSession().setAttribute("access_token", accessToken);
		}
		return "redirect:/";
	}
}
