package com.cms.crypto.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cms.crypto.entity.BankDetails;
import com.cms.crypto.entity.User;
import com.cms.crypto.interf.BankServiceIntrf;
import com.cms.crypto.repo.BankDetailsRepo;
import com.cms.crypto.repo.UserRepo;
import com.cms.crypto.request.BankDetailsRequest;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BankDetailService implements BankServiceIntrf {

	@Autowired
	private BankDetailsRepo bankDetailRepo;

	@Autowired
	private UserRepo userRepo;

	// Add(Post) Bank details
	public BankDetails addDetails(BankDetailsRequest bankDetailsReq) throws Exception {
		BankDetails bank = new BankDetails();
		log.info("Add bank details Service method");
		bank.setAccountNo(bankDetailsReq.getAccountNo());
		bank.setIfsc(bankDetailsReq.getIfsc());
		bank.setHolderName(bankDetailsReq.getHolderName());
		bank.setBranchName(bankDetailsReq.getBranchName());
		Optional<User> user = userRepo.findById(bankDetailsReq.getUserId());
		if (!user.isPresent()) {
			throw new Exception("User ID not found");
		} else {
			bank.setUser(user.get());
		}

		return bankDetailRepo.save(bank);
	}

	//remove
	public Map<String, Boolean> removeDetails(long accountId) throws Exception {

		Optional<BankDetails> details = bankDetailRepo.findById(accountId);
		if (details.isPresent()) {
			bankDetailRepo.deleteById(accountId);
			Map<String, Boolean> response = new HashMap<>();
	        response.put("deleted", Boolean.TRUE);
	        return response;
		} else {
			throw new Exception("Account id not found" + accountId);
		}

	}

	//update
	public BankDetails updateBankDetails(BankDetailsRequest bankRequest, long accountId) throws Exception {

		log.info("Upadte coin service method");
		BankDetails bank = new BankDetails();
		Optional<BankDetails> account = bankDetailRepo.findById(accountId);
		try {
		if (!account.isPresent()) {
			throw new Exception("Could not find Acoount with id- " + accountId);
		} else {

			bank.setAccountNo(bankRequest.getAccountNo());
			bank.setBranchName(bankRequest.getBranchName());
			bank.setHolderName(bankRequest.getHolderName());
			bank.setIfsc(bankRequest.getIfsc());

			Optional<User> user = userRepo.findById(bankRequest.getUserId());
			if (!user.isPresent()) {
				throw new Exception("User id not found");
			} else {
				bank.setUser(user.get());
			}

			bank.setAccountId(accountId);
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return  bankDetailRepo.save(bank);
 
	}

	public BankDetails findById(long accountId) {
		return bankDetailRepo.findById(accountId).get();
	}

}
