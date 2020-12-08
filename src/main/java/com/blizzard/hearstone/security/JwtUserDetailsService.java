package com.blizzard.hearstone.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blizzard.hearstone.model.UserRepository;
import com.blizzard.hearstone.model.entity.User;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired 
	UserRepository repo;
	
	@Autowired 
	private PasswordEncoder encoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		User current = repo.findByName(username); 
		if( current == null ) {
			throw new UsernameNotFoundException("User not found with username " + username);
		}
		else {
			return new org.springframework.security.core.userdetails.User(
					current.getName(), 
					current.getPassword(), 
					new ArrayList<>()
			);
		}
	}
	
	public User save(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return repo.save(user);
	}
}
