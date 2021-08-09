package com.cms.crypto.request;

import lombok.Data;

@Data
public class WalletRequest {

	private String coinName;

	private double buyPrice;

	private double currentPrice;

	private double sellPrice;

	private double currentHoldings;

	private int userId;

}
