package com.cms.crypto.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cms.crypto.entity.BankDetails;
import com.cms.crypto.entity.User;
import com.cms.crypto.entity.Wallet;
import com.cms.crypto.interf.WalletServIntrf;
import com.cms.crypto.repo.ApiResponse;
import com.cms.crypto.repo.UserRepo;
import com.cms.crypto.repo.WalletRepo;
import com.cms.crypto.request.WalletRequest;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class WalletServ implements WalletServIntrf {

	@Autowired
	private WalletRepo walletRepo;

	@Autowired
	private UserRepo userRepo;

	// Add new coins
	public Wallet addCoins(WalletRequest walletRequest) throws Exception {
		log.info("Add coins Service method");
		Wallet wallet = new Wallet();
		wallet.setCoinName(walletRequest.getCoinName());
		wallet.setBuyPrice(walletRequest.getBuyPrice());
		wallet.setCurrentHoldings(walletRequest.getCurrentHoldings());
		wallet.setCurrentPrice(walletRequest.getCurrentPrice());
		wallet.setSellPrice(walletRequest.getSellPrice());
		Optional<User> user = userRepo.findById(walletRequest.getUserId());
		if (!user.isPresent()) {
			throw new Exception("User id not found");
		} else {
			wallet.setUser(user.get());
		}
		return walletRepo.save(wallet);
	}

	// remove coins
	public Map<String, Boolean> removeCoins(int coinId) throws Exception {
		log.info("Remove coins service method");
		Optional<Wallet> wallet = walletRepo.findById(coinId);
		if (wallet.isPresent()) {
			walletRepo.deleteById(coinId);
			Map<String, Boolean> response = new HashMap<>();
		        response.put("deleted", Boolean.TRUE);
		        return response;
		} else {
			throw new Exception("Coin id not found" + coinId);
		}
	}

	// get by id
	public Wallet getBycoinId(int coinId) throws Exception {
		log.info("Coin get by id service method");
		Optional<Wallet> wallet = walletRepo.findById(coinId);
		if (wallet.isPresent()) {
			return wallet.get();
		} else
			throw new Exception("Record not Found" + coinId);
	}

	// Show all coins of wallet
	public Page<Wallet> getWallet(Pageable page) {
		log.info("Page", page);
		Page<Wallet> wallets = walletRepo.findAll(page);
		log.info("Wallet", wallets);
		return walletRepo.findAll(page);

		
	}

	// Update coins
	public Wallet updateWallet(WalletRequest walletRequest, int coinId) throws Exception {
		log.info("Upadte coin service method");
		Wallet wallet = new Wallet();
		Optional<Wallet> coin = walletRepo.findById(coinId);
		try {
		if (!coin.isPresent()) {
			throw new Exception("Could not find Student with id- " + coin);
		} else {
			wallet.setCoinName(walletRequest.getCoinName());
			wallet.setBuyPrice(walletRequest.getBuyPrice());
			wallet.setCurrentHoldings(walletRequest.getCurrentHoldings());
			wallet.setCurrentPrice(walletRequest.getCurrentPrice());
			wallet.setSellPrice(walletRequest.getSellPrice());
			Optional<User> user = userRepo.findById(walletRequest.getUserId());
			if (!user.isPresent()) {
				throw new Exception("User id not found");
			} else {
				wallet.setUser(user.get());
			}
			wallet.setCoinId(coinId);
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return walletRepo.save(wallet);
	}

	public Wallet findById(int coinId) {
		return walletRepo.findById(coinId).get();
	}

	//get by id
	/*public List<Wallet> showCoins(int userId) {
		return walletRepo.getByUserId(userId);
	}*/

}
