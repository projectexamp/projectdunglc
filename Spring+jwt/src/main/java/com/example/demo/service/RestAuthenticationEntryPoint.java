package com.example.demo.service;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public final class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
	
//	public void commence(HttpServletRequest request, HttpServletResponse response,
//                         AuthenticationException authException) throws IOException {
////		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
//		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//		response.getWriter().write("Unauthorized");
//	}
        private static final long serialVersionUID = -7858869558953243875L;
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
	}
}
