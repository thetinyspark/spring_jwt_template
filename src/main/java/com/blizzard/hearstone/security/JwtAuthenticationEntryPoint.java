package com.blizzard.hearstone.security;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1753868235758236790L;
	
	@Override
	public void commence(	HttpServletRequest request, 
							HttpServletResponse response, 
							AuthenticationException exception
						)throws IOException{
		
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Vous ne passerez paaass !");
		
	}
	
}