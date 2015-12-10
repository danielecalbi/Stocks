package com.stocks;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.stocks.enums.StockSymbol;
import com.stocks.enums.TradeOperationFlag;
import com.stocks.model.PreferredStock;
import com.stocks.model.Stock;
import com.stocks.model.Trade;
import com.stocks.services.StockService;
import com.stocks.services.impl.StockServiceImpl;

/**
 * Main test class for Super Simple Stocks
 */
public class Main {
	
	static StockService stockService = new StockServiceImpl();

	public static void main(String[] args) {
		Random rand = new Random();
		
		// Get Stock List with fixed values
		List<Stock> stocksList = loadStocks();
		// Initialize the Trades number variable from a random value
		int tradesNumber = rand.nextInt(10000);
		System.out.println("Total trades: " + tradesNumber);
		
		try {
			Map<Stock, List<Trade>> map = new HashMap<Stock, List<Trade>>();
			
			for (int i = 0; i < tradesNumber; i++) {
				Stock stock = stocksList.get(rand.nextInt(stocksList.size()));
				
				// Use a LinkedList to preserve the order of inserting
				LinkedList<Trade> stockTradesList = (LinkedList<Trade>) map.get(stock);
				TradeOperationFlag tradeOperationFlag;
				if (stockTradesList == null) {
					// Initialize List and Buy some stocks
					stockTradesList = new LinkedList<Trade>();
					tradeOperationFlag = TradeOperationFlag.BUY;
				} else {
					// Randomly set the BUY/SELL Flag
					tradeOperationFlag = (rand.nextBoolean() ? TradeOperationFlag.BUY : TradeOperationFlag.SELL);
				}
				
				// Get a price from a Gaussian with mean = 10 and with only two decimals
				double price = Math.abs(Math.round(10 * (rand.nextGaussian() + 1) * 100) / 100d);
				// Check the value of sharesQuantity to not sell more than you have
				int sharesQuantity = getCongruentSharesQuantity(stockTradesList, tradeOperationFlag, rand);
				
				map = stockService.addTrade(map, new Trade(stock, new Date(), sharesQuantity, tradeOperationFlag, price));
			}
			
			avoidNegativeTickerPrice(map);


			for (Stock stock : stocksList) {
				List<Trade> tradeForStock = map.get(stock);
				// Ticker price is the difference between last price and oldest price
				// Trade, in the LinkedList, are stored in chronological order, from oldest to most recent
				double tickerPrice = tradeForStock.get(tradeForStock.size() - 1).getPrice() - tradeForStock.get(0).getPrice();
				String stockSymbol = stock.getSymbol().name();

				stock.setTickerPrice(tickerPrice);
				double dividendYield = stockService.getDividendYield(stock);
				System.out.println("Dividend Yield for " + stockSymbol + ": " + dividendYield);
				System.out.println("P/E Ratio for " + stockSymbol + ": " + stockService.getPeRatio(stock.getTickerPrice(), dividendYield));
				System.out.println("StockPrice for " + stockSymbol + ": " + stockService.getStockPrice(map.get(stock)));
			}
			
			System.out.println("GBCE All Share Index: " + stockService.getSharesIndexes(stocksList));
			
		} catch (Exception e) {
			System.err.println("Error in managing stock");
			e.printStackTrace();
		}
		
	}

	private static List<Stock> loadStocks() {
		List<Stock> stocksList = new ArrayList<Stock>();
		stocksList.add(new Stock(StockSymbol.TEA, 0, 100));
		stocksList.add(new Stock(StockSymbol.POP, 8, 100));
		stocksList.add(new Stock(StockSymbol.ALE, 23, 60));
		stocksList.add(new PreferredStock(StockSymbol.GIN, 8, 100, 0.02d));
		stocksList.add(new Stock(StockSymbol.JOE, 13, 250));
		return stocksList;
	}
	
	private static int getCongruentSharesQuantity(List<Trade> stockTradesList, TradeOperationFlag tradeOperationFlag, Random rand) {
		int result = rand.nextInt(1000);

		int totalShares = 0;

		for (Iterator<Trade> iterator = stockTradesList.iterator(); iterator.hasNext();) {
			Trade trade = iterator.next();
			totalShares += (trade.getSharesQuantity() * ((trade.getTradeOperationFlag().equals(TradeOperationFlag.SELL) ? -1 : 1)));
		}

		if (tradeOperationFlag.equals(TradeOperationFlag.SELL)) {
			while (totalShares - result < 0) {
				result = rand.nextInt(1000);
			}
		}

		return result;
	}
	
	private static void avoidNegativeTickerPrice(Map<Stock, List<Trade>> map) {
		for (Stock stock : map.keySet()) {
		    List<Trade> listTrades = map.get(stock);
		    if(listTrades.get(listTrades.size() - 1).getPrice() < listTrades.get(0).getPrice()){
		    	listTrades.get(listTrades.size() - 1).setPrice(listTrades.get(0).getPrice() + 1d);
		    }
		}
		
	}

}
