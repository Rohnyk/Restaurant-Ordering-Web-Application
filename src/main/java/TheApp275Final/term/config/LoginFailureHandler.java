package TheApp275Final.term.config;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String targetUrl = "/user/";
		
		switch (exception.getMessage()) {
		case "User is disabled":
			targetUrl = "/activateaccount?error&errormsg=Please_Activate_Your_Account_before_Logging-In!!";
			break;
			
		case "Bad credentials":
			targetUrl = "/login?error";
			break;

		default:
			targetUrl = "/login?error";
			break;
		}
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

}
