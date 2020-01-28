package com.employee.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService{

	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if("admin".equals(username)){
			return new User("admin", "$2y$12$ZvI8ySzJwMfUkGcvn5noQO/a5LknqHkf9sjnhjhgBoguwGCQRSJyW", new ArrayList<>());
		}
		else	
		throw new UsernameNotFoundException(" User Not Found");
		
	}
}
