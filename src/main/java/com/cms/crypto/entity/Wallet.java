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

@Entity(name = "wallet")
@Table(name = "wallet")
@Data
public class Wallet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "coin_id")
	private int coinId;

	@Column(name = "coin_name")
	private String coinName;

	@Column(name = "buy_price")
	private double buyPrice;

	@Column(name = "current_price")
	private double currentPrice;

	@Column(name = "sell_price")
	private double sellPrice;

	@Column(name = "current_holdings")
	private double currentHoldings;

	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User user;

}
