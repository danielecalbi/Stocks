package com.stocks.model;

import com.stocks.enums.TradeOperationFlag;

import java.util.Date;

/**
 * Class representing a Trade.
 */
public class Trade {

	private Stock stock;
	private Date timestamp;
	private int sharesQuantity;
	private TradeOperationFlag tradeOperationFlag;
	private double price;

	public Trade(Stock stock, Date timestamp, int sharesQuantity, TradeOperationFlag tradeOperationFlag, double price) {
		this.stock = stock;
		this.timestamp = timestamp;
		this.sharesQuantity = sharesQuantity;
		this.tradeOperationFlag = tradeOperationFlag;
		this.price = price;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public int getSharesQuantity() {
		return sharesQuantity;
	}

	public void setSharesQuantity(int sharesQuantity) {
		this.sharesQuantity = sharesQuantity;
	}

	public TradeOperationFlag getTradeOperationFlag() {
		return tradeOperationFlag;
	}

	public void setTradeOperationFlag(TradeOperationFlag tradeOperationFlag) {
		this.tradeOperationFlag = tradeOperationFlag;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
