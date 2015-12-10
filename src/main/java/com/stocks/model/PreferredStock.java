package com.stocks.model;

import com.stocks.enums.StockSymbol;

/**
 * Class representing a Preferred Stock.
 */
public class PreferredStock extends Stock {

	private double fixedDividend;

	public PreferredStock(StockSymbol symbol, double lastDividend, int parValue, double fixedDividend) {
		super(symbol, lastDividend, parValue);
		this.fixedDividend = fixedDividend;
	}
	
	public PreferredStock(StockSymbol symbol, double lastDividend, int parValue, double tickerPrice, double fixedDividend) {
		super(symbol, lastDividend, parValue, tickerPrice);
		this.fixedDividend = fixedDividend;
	}

	public double getFixedDividend() {
		return fixedDividend;
	}

	public void setFixedDividend(double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}

}
