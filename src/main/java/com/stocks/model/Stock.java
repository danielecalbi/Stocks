package com.stocks.model;

import com.stocks.enums.StockSymbol;

/**
 * Class representing a Stock.
 */
public class Stock {

	private StockSymbol symbol;
	private double lastDividend;
	private double parValue;
	private double tickerPrice;

	public Stock(StockSymbol symbol, double lastDividend, double parValue) {
		this.symbol = symbol;
		this.lastDividend = lastDividend;
		this.parValue = parValue;
	}

	public Stock(StockSymbol symbol, double lastDividend, double parValue, double tickerPrice) {
		this.symbol = symbol;
		this.lastDividend = lastDividend;
		this.parValue = parValue;
		this.tickerPrice = tickerPrice;
	}
	
	public StockSymbol getSymbol() {
		return symbol;
	}

	public void setSymbol(StockSymbol symbol) {
		this.symbol = symbol;
	}

	public double getLastDividend() {
		return lastDividend;
	}

	public void setLastDividend(double lastDividend) {
		this.lastDividend = lastDividend;
	}

	public double getParValue() {
		return parValue;
	}

	public void setParValue(double parValue) {
		this.parValue = parValue;
	}

	public double getTickerPrice() {
		return tickerPrice;
	}

	public void setTickerPrice(double tickerPrice) {
		this.tickerPrice = tickerPrice;
	}
	
	@Override
	public int hashCode() {
		return 17 * 31 * ((this.symbol).hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj instanceof Stock) {
			Stock stock = (Stock) obj;
			result = this.symbol.equals(stock.getSymbol());
		}
		return result;
	}

}
