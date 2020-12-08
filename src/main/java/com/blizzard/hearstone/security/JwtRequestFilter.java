package com.blizzard.hearstone.security; 

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.blizzard.hearstone.security.JwtUserDetailsService;


@Component
public class JwtRequestFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtUserDetailsService service;
	
	@Autowired
	private JwtTokenUtil util;
	  
	
	@Override
	protected void doFilterInternal(
			HttpServletRequest request, 
			HttpServletResponse response,
			FilterChain chain
	)throws ServletException, IOException{
		final String requestTokenHeader = request.getHeader("Authorization");
		
		String username = null; 
		String jwtToken = null;
		
		
		
		// dans la convention JWT, on envoie le token avec le préfixe "Bearer "
		if( requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			
			jwtToken = requestTokenHeader.substring(7);
			
			try {
				username = util.getUserNameFromToken(jwtToken);
			}
			catch( Exception error ) {
				System.out.println(error.getMessage());
			}
		}
		else {
			System.out.println("JWT Token does not begin with a Bearer string");
		}
		
	
		
		if( username != null && SecurityContextHolder.getContext().getAuthentication() == null ) {
			System.out.println("coucou");
			UserDetails details = this.service.loadUserByUsername(username);
			
			if( util.validateToken(jwtToken, details)) {
				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
						details, null, details.getAuthorities()
				); 
				
				token.setDetails( new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(token);
			}
		}
		
		chain.doFilter(request, response);
	}
}