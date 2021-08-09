package com.cms.crypto.interf;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cms.crypto.entity.User;
import com.cms.crypto.request.UserRequest;

public interface UserServIntr {
	public User addUser(UserRequest userRequest) throws Exception;
	public Page<User> getUserDetails(Pageable page);
	public User updateWallet(UserRequest userRequest, int userId) throws Exception;
	public Map<String, Boolean> removeUser(int userId) throws Exception;

}
