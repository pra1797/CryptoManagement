package com.cms.crypto.controller;

import java.util.List;
import java.util.Map;


import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cms.crypto.entity.Wallet;
import com.cms.crypto.repo.ApiResponse;
import com.cms.crypto.request.WalletRequest;
import com.cms.crypto.service.WalletServ;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/wallet")
@Slf4j
@CrossOrigin
public class WalletController {

	@Autowired
	private WalletServ walletServ;
	
	//get by user id
	/*@GetMapping("/{userId}")
	public ResponseEntity<List<Wallet>> showCoins(@PathVariable int userId){
		return new ResponseEntity<>(walletServ.showCoins(userId), HttpStatus.OK);
	}*/

	// Add Coins
	@PostMapping(value = "/addcoins")
	@PreAuthorize("hasRole('Holder')")
	@ApiOperation(value = "Add new coin", notes = "Enter correct userID")
	public Wallet addCoins(@RequestBody WalletRequest walletRequest) throws Exception {
		log.info("Add coin call");
		return walletServ.addCoins(walletRequest);

	}

	// Show all
	@GetMapping("/show")
	@PreAuthorize("hasRole('Holder')")
	@ApiOperation(value = "Show all coins")
	Page<Wallet> getWallet(@PageableDefault(sort = { "coinId" }) final Pageable page) {
		log.info("Show all coins call");
		return walletServ.getWallet(page);
		// SELECT * FROM wallet ORDER BY coinId OFFSET 5 ROWS FETCH NEXT 5 ROWS ONLY;
	}
	

	// Find coin by coin id
	@GetMapping(value = "/{coinId}")
	@PreAuthorize("hasRole('Holder')")
	@ApiOperation(value = "Get coin by Id", notes = "Enter correct coinID")
	public Wallet getBycoinId(@PathVariable("coinId") int coinId) throws Exception {
		return walletServ.getBycoinId(coinId);
	}

	//Update
	@PutMapping(value = "/update/{coinId}")
	@PreAuthorize("hasRole('Holder')")
	@ApiOperation(value = "Update coin")
	public ResponseEntity<ApiResponse> updateWallet(@Validated @RequestBody WalletRequest walletRequest,
			@PathVariable int coinId) throws Exception {
		log.info("update coin details");
		try {
			Wallet wallet = walletServ.findById(coinId);
			walletServ.updateWallet(walletRequest, coinId);
			return new ResponseEntity<>(new ApiResponse(true, "Wallte is updated"), HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Coin does not exist"), HttpStatus.CONFLICT);
		}
	}

	// remove coins
	@DeleteMapping(value = "/removecoins/{coinId}")
	@PreAuthorize("hasRole('Holder')")
	@ApiOperation(value = "Remove coin", notes = "Enter Correct coinID")
	public Map<String, Boolean> removeCoins(@PathVariable("coinId") int coinId) throws Exception {
		log.info("Remove coin call");
		return walletServ.removeCoins(coinId);
	}

}
