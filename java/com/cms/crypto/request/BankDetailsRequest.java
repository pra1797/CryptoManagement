package com.cms.crypto.request;

import lombok.Data;

@Data
public class BankDetailsRequest {

	private String accountNo;

	private String ifsc;

	private String holderName;

	private String branchName;

	private int userId;

}
