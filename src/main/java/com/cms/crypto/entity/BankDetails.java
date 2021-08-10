package com.cms.crypto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "bank")
@Data
public class BankDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "accountid")
	private Long accountId;

	@Column(name = "accountno")
	private String accountNo;

	@Column(name = "ifsc")
	private String ifsc;

	@Column(name = "holdername")
	private String holderName;

	@Column(name = "branchname")
	private String branchName;

	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User user;

}
