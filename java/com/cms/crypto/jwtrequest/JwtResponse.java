package com.cms.crypto.jwtrequest;

import com.cms.crypto.entity.User;

import lombok.Data;

@Data
public class JwtResponse {

	private User user;
	private String jwtToken;

	public JwtResponse(User user, String jwtToken) {

		this.user = user;
		this.jwtToken = jwtToken;
	}

}
