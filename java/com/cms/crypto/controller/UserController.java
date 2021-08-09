package com.cms.crypto.controller;

import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cms.crypto.entity.User;
import com.cms.crypto.repo.ApiResponse;
import com.cms.crypto.request.UserRequest;
import com.cms.crypto.service.UserService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@Slf4j
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;

	// Add new user
	@PostMapping(value = "/adduser")
	@ApiOperation(value = "Register new user")
	public User addUser(@RequestBody UserRequest userRequest) throws Exception {

		log.info("User Controller call");
		return userService.addUser(userRequest);
	}

	// Show all
	@GetMapping("/show")
	@PreAuthorize("hasRole('Admin')")
	@ApiOperation(value = "Show all user details")
	Page<User> getUserDetails(@PageableDefault(sort = { "userId" }) final Pageable page) {
		log.info("Show all users call");
		return userService.getUserDetails(page);
	}

	// Update
	@PutMapping(value = "/update/{userId}")
	@PreAuthorize("hasRole('Holder')")
	@ApiOperation(value = "Update User Details")
	public ResponseEntity<ApiResponse> updateUser(@RequestBody UserRequest userRequest, @PathVariable int userId) throws Exception {
		log.info("update coin details");
		try {
			User user = userService.findById(userId);
			userService.updateWallet(userRequest, userId);
			return new ResponseEntity<>(new ApiResponse(true, "User is updated"), HttpStatus.OK);
		}catch(NoSuchElementException e) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "User does not exist"), HttpStatus.CONFLICT);	
		}
	}

	// Remove user
	@DeleteMapping(value = "/removeuser/{userId}")
	@PreAuthorize("hasRole('Admin')")
	@ApiOperation(value = "Remove User", notes = "Enter Correct userId")
	public Map<String, Boolean> removeUser(@PathVariable("userId") int userId) throws Exception {
		log.info("Remove user call");
	   return userService.removeUser(userId);
	}

}
