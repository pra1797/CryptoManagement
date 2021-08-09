package com.cms.crypto.service;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.cms.crypto.entity.Role;
import com.cms.crypto.interf.RoleServInter;
import com.cms.crypto.repo.RoleRepo;
import com.cms.crypto.request.RoleRequest;


import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RoleService implements RoleServInter {

	@Autowired
	private RoleRepo roleRepo;

	public Role addRole(RoleRequest roleRequest) {
		log.info("Add role service class");

		Role role = new Role();
		System.out.println(role.toString());
		role.setRoleName(roleRequest.getRoleName());
		role.setRoleDescription(roleRequest.getRoleDescription());

		return roleRepo.save(role);
	}

	//get all roles
	public List<Role> getRoles() {
		List<Role> data = new ArrayList<>();
		roleRepo.findAll().forEach(data::add);
		System.out.println(data.toString());
		return data;
	}

}
