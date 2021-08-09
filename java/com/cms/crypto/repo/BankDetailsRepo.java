package com.cms.crypto.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cms.crypto.entity.BankDetails;

public interface BankDetailsRepo extends PagingAndSortingRepository<BankDetails, Long> {

}
