package com.cms.crypto.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cms.crypto.jwtrequest.JwtRequest;
import com.cms.crypto.jwtrequest.JwtResponse;
import com.cms.crypto.service.JwtService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;


@RestController
@CrossOrigin
@RequestMapping("/authenticate")
@Slf4j
public class JwtController {

	@Autowired
	private JwtService jwtService;

	public JwtController() {
		log.info("jwt Controller");
	}

	//Request for getting token
	@PostMapping(value = "/request")
	@ApiOperation(value = "Authenticattion request for getting token" , notes = "Enter correct userID and Password")
	public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		log.info("Request for token");
		return jwtService.createJwtToken(jwtRequest);
	}
}
