package com.stocks;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.stocks.enums.StockSymbol;
import com.stocks.enums.TradeOperationFlag;
import com.stocks.model.PreferredStock;
import com.stocks.model.Stock;
import com.stocks.model.Trade;
import com.stocks.services.StockService;
import com.stocks.services.impl.StockServiceImpl;

/**
 * Test class of Stock Services.
 */
public class StockServiceImplTest {

	private StockService stockService = new StockServiceImpl();

	@Test
	public void testGetStockPrice() {
		Stock ale = new Stock(StockSymbol.ALE, 2d, 11d);

		List<Trade> trades = new LinkedList<Trade>();
		trades.add(new Trade(ale, new Date(), 432, TradeOperationFlag.BUY, 111d));
		trades.add(new Trade(ale, new Date(), 234, TradeOperationFlag.SELL, 122d));

		Assert.assertTrue(Math.round(stockService.getStockPrice(trades) * 100) / 100d == 114.86);
	}

	@Test
	public void testGetSharesIndexes() {
		List<Stock> stocks = new ArrayList<Stock>();
		stocks.add(new Stock(StockSymbol.TEA, 0d, 100d, 10.5d));
		stocks.add(new Stock(StockSymbol.POP, 8d, 100d, 11.0d));
		stocks.add(new Stock(StockSymbol.ALE, 23d, 60d, 8.25d));
		stocks.add(new Stock(StockSymbol.GIN, 8d, 100d, 10.0d));
		stocks.add(new Stock(StockSymbol.JOE, 13d, 250d, 11.5d));

		Assert.assertTrue(Math.round(stockService.getSharesIndexes(stocks) * 1000000) / 1000000d == 0.016696);
	}

	@Test
	public void testGetDividendYieldCommon() {
		Assert.assertTrue(stockService.getDividendYield(new Stock(StockSymbol.POP, 8, 100, 0.5d)) == 16.0);
		Assert.assertTrue(stockService.getDividendYield(new Stock(StockSymbol.POP, 0, 100, 0.5d)) == 0);
	}

	@Test
	public void testGetDividendYieldPreferred() {
		Assert.assertTrue(stockService.getDividendYield(new PreferredStock(StockSymbol.GIN, 0, 100, 0.5d, 0.02d)) == 4.0);
		Assert.assertTrue(stockService.getDividendYield(new PreferredStock(StockSymbol.GIN, 0, 100, 0.5d, 0)) == 0);
	}

	@Test
	public void testGetPeRatio() {
		Assert.assertTrue(Math.round(stockService.getPeRatio(33, 0.55d) * 100) / 100d == 60);
		Assert.assertTrue(stockService.getPeRatio(0d, 0.45d) == 0d);
		Assert.assertTrue(stockService.getPeRatio(33d, 0d) == Double.POSITIVE_INFINITY);
	}

	@Test
	public void testAddTrade() {
		Stock tea = new Stock(StockSymbol.TEA, 0d, 100d, 10.5d);
		Stock pop = new Stock(StockSymbol.POP, 8d, 100d, 11.0d);
		Stock ale = new Stock(StockSymbol.ALE, 23d, 60d, 8.25d);
		Stock gin = new Stock(StockSymbol.GIN, 8d, 100d, 10.0d);
		Stock joe = new Stock(StockSymbol.JOE, 13d, 250d, 11.5d);

		Map<Stock, List<Trade>> map = new HashMap<Stock, List<Trade>>();
		map.put(tea, this.getTestTradesList(tea));
		map.put(pop, this.getTestTradesList(pop));
		map.put(ale, this.getTestTradesList(ale));
		map.put(gin, this.getTestTradesList(gin));
		map.put(joe, this.getTestTradesList(joe));

		stockService.addTrade(map, new Trade(ale, new Date(), 123, TradeOperationFlag.BUY, 133d));

		Assert.assertTrue(map.get(ale).size() == 3);
	}

	private List<Trade> getTestTradesList(Stock stock) {
		List<Trade> trades = new LinkedList<Trade>();
		trades.add(new Trade(stock, new Date(), 432, TradeOperationFlag.BUY, 111d));
		trades.add(new Trade(stock, new Date(), 234, TradeOperationFlag.SELL, 122d));
		return trades;
	}

}
