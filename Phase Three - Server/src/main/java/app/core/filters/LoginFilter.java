package app.core.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import app.core.session.SessionContext;

public class LoginFilter implements Filter {

	private SessionContext sessionContext;

	public LoginFilter(SessionContext sessionContext) {
		super();
		this.sessionContext = sessionContext;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Login Filter is working...");
		HttpServletRequest req = (HttpServletRequest) request;
		String token = req.getHeader("token");
		if (req.getMethod().equals("OPTIONS")) {
			chain.doFilter(req, response);
			System.out.println("Preflight Request");
			return;
		}
		if (token != null && sessionContext.getSession(token) != null) {
			chain.doFilter(request, response);
			System.out.println("Session is Valid");
			return;
		}

		System.out.println("NO SESSION - REQUEST IS BLOCKED");
		HttpServletResponse resp = (HttpServletResponse) response;
		resp.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		resp.sendError(HttpStatus.UNAUTHORIZED.value(), "You are not Logged-in OR you are passing an Invalid Token");
	}

}
