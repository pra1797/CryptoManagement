package com.cms.crypto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cms.crypto.entity.Role;
import com.cms.crypto.request.RoleRequest;
import com.cms.crypto.service.RoleService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/role")
@Slf4j
public class RoleController {

	@Autowired
	private RoleService roleService;

	// Add new Role
	@PostMapping(value = "/addrole")
	@PreAuthorize("hasRole('Admin')")
	@ApiOperation(value = "Add new role")
	public Role addRole(@RequestBody RoleRequest roleRequest) {
		log.info("Add role Controller call");
		return roleService.addRole(roleRequest);
	}
	
	@GetMapping("/getall")
	public List<Role> geRoles(){
		return roleService.getRoles();
	}

}
