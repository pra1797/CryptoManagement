package com.cms.crypto.controller;




import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.cms.crypto.repo.ApiResponse;
import com.cms.crypto.entity.BankDetails;
import com.cms.crypto.request.BankDetailsRequest;
import com.cms.crypto.service.BankDetailService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/bankdetails")
@Slf4j
public class BankDetailsController {
	
	@Autowired
	private BankDetailService bankDetailServ;
	
	//Add Bank Details
	@PostMapping(value = "/add")
	@PreAuthorize("hasRole('Holder')")
	@ApiOperation(value = "Add the Bank Details")
	public BankDetails addDetails(@RequestBody BankDetailsRequest bankDetailsReq) throws Exception {
		log.info("Add bank detais controller");
	   return bankDetailServ.addDetails(bankDetailsReq);
		
	}
	
	//Update Bank Detailsb request
	@PutMapping(value = "/update/{accountId}")
	@PreAuthorize("hasRole('Holder')")
	@ApiOperation(value = "Update bank details details")
	public ResponseEntity<ApiResponse> updatBankDetails(@Validated @RequestBody BankDetailsRequest bankRequest, @PathVariable long accountId) throws Exception {
		try {
			BankDetails details = bankDetailServ.findById(accountId);
		bankDetailServ.updateBankDetails(bankRequest, accountId);
		return new ResponseEntity<>(new ApiResponse(true, "bank details is updated"), HttpStatus.OK);
		}catch(NoSuchElementException e) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Acccount id does not exist"), HttpStatus.CONFLICT);
		}
		}
	
	//Remove Bank Details
	@DeleteMapping(value = "/delete/{accountId}")
	@PreAuthorize("hasRole('Holder')")
	@ApiOperation(value = "Delete Bank details" , notes = "Insert the correst accountID")
	public Map<String, Boolean> removeDetails(@PathVariable("accountId") long accountId) throws Exception {
		return bankDetailServ.removeDetails(accountId);
	}
	


}
