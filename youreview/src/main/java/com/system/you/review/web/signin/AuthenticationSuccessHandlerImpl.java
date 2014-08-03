package com.system.you.review.web.signin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.security.SocialAuthenticationToken;

import com.system.you.review.web.domain.Requestor;
import com.system.you.review.web.domain.impl.SessionUtils;

public class AuthenticationSuccessHandlerImpl extends
		SavedRequestAwareAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		if (isAuthenticated(authentication)
				&& isSupportedLoginPlatform(authentication)) {
			Requestor requestor = SessionUtils.getRequestor();
			requestor.authenticateRequestor(authentication);
		}
		super.onAuthenticationSuccess(request, response, authentication);
	}

	private boolean isSupportedLoginPlatform(Authentication auth) {
		Connection<?> connection = ((SocialAuthenticationToken) auth)
				.getConnection();
		if (isFacebookLogin(connection)) {
			return true;
		}
		return false;
	}

	private boolean isFacebookLogin(Connection<?> connection) {
		return connection.getApi() instanceof Facebook;
	}

	private boolean isAuthenticated(Authentication authResult) {
		if (authResult != null) {
			if (authResult instanceof SocialAuthenticationToken) {
				if (authResult.isAuthenticated()) {
					return true;
				}
			}
		}
		return false;
	}
}
