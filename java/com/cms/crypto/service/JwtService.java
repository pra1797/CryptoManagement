package com.cms.crypto.service;

import java.util.HashSet;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cms.crypto.entity.User;
import com.cms.crypto.jwtrequest.JwtRequest;
import com.cms.crypto.jwtrequest.JwtResponse;
import com.cms.crypto.repo.UserRepo;
import com.cms.crypto.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtService implements UserDetailsService {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private AuthenticationManager authenticationManager;

	// returning generated t0ken

	public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {

		log.info("gettting username jwt service");

		String userName = jwtRequest.getUserName();
		User user = userRepo.findByUserName(userName);
		System.out.println(user);
		String newGeneratedToken = jwtUtil.generateToken(userName);
		return new JwtResponse(user, newGeneratedToken);

	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		log.info("Able to find jwt service class");
		User user = userRepo.findByUserName(userName);

		if (user != null) {
			return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getUserPassword(),
					getAuthority(user));
		} else {
			throw new UsernameNotFoundException("User not found with username: " + userName);
		}

	}

	private Set getAuthority(User user) {
		log.info("Autharity to role jwt service");

		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRole().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
		});
		return authorities;
	}

	private void authenticate(String userName, String userPassword) throws Exception {

		System.out.println("credentail exceptions");
		log.info("credential exception jwt service");
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}
