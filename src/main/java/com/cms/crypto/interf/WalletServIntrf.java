package com.cms.crypto.interf;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.cms.crypto.entity.Wallet;
import com.cms.crypto.request.WalletRequest;

public interface WalletServIntrf {

	public Wallet addCoins(WalletRequest walletRequest) throws Exception;

	public Map<String, Boolean> removeCoins(int coin_id) throws Exception;

	public Wallet getBycoinId(int coin_id) throws Exception;

	public Page<Wallet> getWallet(Pageable page);

	public Wallet updateWallet(WalletRequest waletRequest, int coinId) throws Exception;
}
