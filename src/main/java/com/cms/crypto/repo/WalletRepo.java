package com.cms.crypto.repo;

import java.util.List;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cms.crypto.entity.Wallet;

@Repository
public interface WalletRepo extends JpaRepository<Wallet, Integer> {

	//@Query("SELECT w FROM wallet w WHERE w.user_id=?1")
	//public List<Wallet> getByUserId(int userId);
}
