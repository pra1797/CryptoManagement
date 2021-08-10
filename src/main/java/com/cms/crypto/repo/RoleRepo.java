package com.cms.crypto.repo;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.cms.crypto.entity.Role;

@Repository
public interface RoleRepo extends CrudRepository<Role, Integer> {

}
