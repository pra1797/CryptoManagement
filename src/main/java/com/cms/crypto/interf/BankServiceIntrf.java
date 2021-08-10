package com.cms.crypto.interf;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.cms.crypto.entity.BankDetails;
import com.cms.crypto.request.BankDetailsRequest;

public interface BankServiceIntrf {

	public BankDetails addDetails(BankDetailsRequest bankDetailsReq) throws Exception;
	
	public Map<String, Boolean> removeDetails(long accountId) throws Exception;

	public BankDetails updateBankDetails(BankDetailsRequest bankRequest, long accountId) throws Exception;
}
