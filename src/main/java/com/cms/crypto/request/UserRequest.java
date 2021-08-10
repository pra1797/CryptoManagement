package com.cms.crypto.request;

import java.util.Set;
import lombok.Data;

@Data
public class UserRequest {

	private String userName;

	private String firstName;

	private String lastName;

	private String userPassword;

	private String emailId;

	private Set<Integer> role;

}
