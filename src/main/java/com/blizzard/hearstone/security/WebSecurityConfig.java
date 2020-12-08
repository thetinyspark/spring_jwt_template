package com.blizzard.hearstone.security;
import com.blizzard.hearstone.security.JwtAuthenticationEntryPoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration 
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JwtAuthenticationEntryPoint entryPoint;
	
	@Autowired
	private JwtRequestFilter filter;
	
	@Autowired 
	private JwtUserDetailsService service;
	
	@Autowired
	public void configureGlobal( AuthenticationManagerBuilder auth ) throws Exception{
		auth.userDetailsService(service).passwordEncoder( this.getEncoder() );
	}
	
	@Bean
	public PasswordEncoder getEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManagerBean();
	}
	


	@Override
	protected void configure( HttpSecurity http ) throws Exception{
		// on désactive la protection contre les failles de type csrf
		// CROSS SITE REQUEST FORGERIES
		http.csrf().disable()
		// on autorise toutes les requêtes
		.authorizeRequests()
		// qui correspondent à ce(s) pattern(s)
		.antMatchers(HttpMethod.OPTIONS, "/**").permitAll() 
		.antMatchers(HttpMethod.GET, "/character").permitAll() 
		.antMatchers(HttpMethod.GET, "/character/{id}").permitAll() 
		.antMatchers(HttpMethod.POST, "/character").permitAll() 
		.antMatchers(HttpMethod.PUT, "/character/{id}").permitAll() 
		.antMatchers(HttpMethod.DELETE, "/character/{id}").permitAll() 
		.antMatchers(HttpMethod.POST, "/authenticate").permitAll() 
		.antMatchers(HttpMethod.POST, "/register").permitAll() 
		// ensuite pour chaque requête ne correspondant aux patterns cités plus haut
		.anyRequest()
		// on doit  être authentifié par le biais d'un jeton jwt
		.authenticated()
		// et 
		.and() 
		// si on ne l'est pas, on a une erreur personnalisée
		.exceptionHandling().authenticationEntryPoint(entryPoint)  
		// et 
		.and()
		// Quand à la session, elle passe en mode STATELESS, çàd qu'elle ne gère pas le multithreading
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore( filter, UsernamePasswordAuthenticationFilter.class);

	}
}
