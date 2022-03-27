package com.bcp.test.controller.security;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bcp.test.config.security.JwtTokenUtil;
import com.bcp.test.dto.jwt.JwtResponse;
import com.bcp.test.dto.user.UserDto;
import com.bcp.test.dto.user.UserRequest;
import com.bcp.test.service.impl.JwtUserDetailsService;

@RestController
@RequestMapping(value = "/v1/security")
@CrossOrigin
public class SecurityController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(
			@Valid @RequestBody UserRequest user) throws Exception {

		authenticate(user.getEmail(), user.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(user.getEmail());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(
			@Valid @RequestBody UserRequest user) throws Exception {
		return ResponseEntity.ok(userDetailsService.save(user));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			UsernamePasswordAuthenticationToken authenticationToken = new  UsernamePasswordAuthenticationToken(username, password);
			System.out.println(authenticationToken.getName());
			authenticationManager.authenticate(authenticationToken);
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
