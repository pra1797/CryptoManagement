package com.cms.crypto.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cms.crypto.entity.Role;
import com.cms.crypto.entity.User;
import com.cms.crypto.interf.UserServIntr;
import com.cms.crypto.repo.RoleRepo;
import com.cms.crypto.repo.UserRepo;
import com.cms.crypto.request.UserRequest;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService implements UserServIntr {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private RoleRepo roleRepo;

	@Autowired
	private PasswordEncoder passEncode;

	public String getEncodedPass(String user_password) {
		return passEncode.encode(user_password);

	}

	public User addUser(UserRequest userRequest) throws Exception {
		log.info("Add user service method");
		User user = new User();
		Set<Role> roles = new HashSet<>();
	
		Optional<Role> role = roleRepo.findById(3);
		if(role.isPresent()) {
		System.out.println(userRequest.toString());
		user.setUserName(userRequest.getUserName());
		user.setFirstName(userRequest.getFirstName());
		user.setLastName(userRequest.getLastName());
		user.setEmailId(userRequest.getEmailId());
		user.setUserPassword(getEncodedPass(userRequest.getUserPassword()));
		roles.add(role.get());
		}else {
		for (Integer role_id : userRequest.getRole()) {

			Optional<Role> rol = roleRepo.findById(role_id);
			if (!rol.isPresent()) {
				throw new Exception("Role is not present");
			} else {
				roles.add(rol.get());
			}
		}
		}
		user.setRole(roles);
		
		return userRepo.save(user);
	}

	//get all users
	public Page<User> getUserDetails(Pageable page) {
		log.info("Page", page);
		Page<User> user = userRepo.findAll(page);
		log.info("user", user);
		return userRepo.findAll(page);

	}

	// update
	public User updateWallet(UserRequest userRequest, int userId) throws Exception {
		log.info("Upadte User service method");
		User user = new User();
		Optional<User> details = userRepo.findById(userId);
		try {
		if (!details.isPresent()) {
			throw new Exception("Could not find User with id- " + details);
		} else {

			Set<Role> roles = new HashSet<>();
			System.out.println(userRequest.toString());
			user.setUserName(userRequest.getUserName());
			user.setFirstName(userRequest.getFirstName());
			user.setLastName(userRequest.getLastName());
			user.setEmailId(userRequest.getEmailId());
			user.setUserPassword(getEncodedPass(userRequest.getUserPassword()));

			for (Integer role_id : userRequest.getRole()) {

				Optional<Role> role = roleRepo.findById(role_id);
				if (!role.isPresent()) {
					throw new Exception("Role is not present");
				} else {
					roles.add(role.get());
				}

			}
			user.setRole(roles);

			user.setUserId(userId);
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return userRepo.save(user);
	}
	
	

	//Remove user
	public Map<String, Boolean> removeUser(int userId) throws Exception {
		log.info("Remove user service method");
		Optional<User> user = userRepo.findById(userId);
		if (user.isPresent()) {
			userRepo.deleteById(userId);
			Map<String, Boolean> response = new HashMap<>();
	        response.put("deleted", Boolean.TRUE);
	        return response;
		} else {
			throw new Exception("User id not found" + userId);
		}

	}

	public User findById(int userId) {
		return userRepo.findById(userId).get();
	}

}
