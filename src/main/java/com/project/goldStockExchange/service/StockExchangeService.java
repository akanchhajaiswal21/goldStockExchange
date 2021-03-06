package com.project.goldStockExchange.service;

import com.project.goldStockExchange.bo.Stocks;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.*;

@Service
public class StockExchangeService {

    public static ArrayList<Stocks> stocksList;

    static {
        initializeStocksList();
    }

    public static void initializeStocksList(){

        Stocks stock1 =new Stocks("Seller-1",LocalTime.of(8,01,03,00),500,5);
        Stocks stock2 =new Stocks("Seller-2",LocalTime.of(8,03,02,00),700,2);
        Stocks stock3 =new Stocks("Seller-3",LocalTime.of(8,04,04,00),700,6);
        Stocks stock4 =new Stocks("Seller-4",LocalTime.of(8,04,05,00),600,10);

        stocksList = new ArrayList<>();
        stocksList.add(stock1);
        stocksList.add(stock2);
        stocksList.add(stock3);
        stocksList.add(stock4);

        sortStocksList(stocksList);
   }

    /**
     *
     * @param buyerRequest
     * @return
     * @throws CloneNotSupportedException
     */
    public List<Stocks> getCurrentStocksStatusAfterBuying(String buyerRequest) throws Exception {

        String[] buyerReqArr = buyerRequest.split("/");
        int stockCount = 0;
        int buyerRate = 0;
        try {
            stockCount = Integer.parseInt(buyerReqArr[1]);
            buyerRate = Integer.parseInt(buyerReqArr[2]);
        } catch (Exception e) {
            throw new Exception("Stock count or rate is not valid or not present");
        }

        if (stockCount <= 0 || buyerRate <= 0) {
            throw new Exception("Stock count or rate have negative values");
        }


       try {
           LinkedHashMap<String, Stocks> map = new LinkedHashMap<>();
            for (Stocks stock : stocksList) {
                map.put(stock.getSellerName(), (Stocks) stock.clone());
            }

            for (String name : map.keySet()) {
                Stocks stock = map.get(name);
                if (buyerRate >= stock.getRatePerKG()) {
                    if (stockCount > stock.getReqOrAvailAmount()) {
                        stockCount = stockCount - stock.getReqOrAvailAmount();
                        stock.setReqOrAvailAmount(0);
                        map.put(name, stock);
                    } else {
                        int temp = stock.getReqOrAvailAmount() - stockCount;
                        stock.setReqOrAvailAmount(temp);
                        map.put(name, stock);
                        stockCount = 0;
                        break;
                    }
                }
            }

            if (stockCount == 0) {
                stocksList = new ArrayList<>();
                for (String name : map.keySet()) {
                    if (map.get(name).getReqOrAvailAmount() != 0) {
                        stocksList.add(map.get(name));
                    }
                }
            }
        } catch(Exception e){
           throw new Exception("Some isseu in proccessing informatio.. Please try again later");
       }

        return stocksList;
    }

    /**
     * Method to reinitialize the stocks with the provided values
     * @param stocksListReq
     */
    public void initializeStocksList(List<Stocks> stocksListReq){

        //Sorting the list of stocks and then storing them in the cache
        sortStocksList(stocksListReq);
        stocksList = new ArrayList<>();
        stocksList = (ArrayList<Stocks>)stocksListReq;
    }

    /**
     * Method to get current status of stocks
     * @return
     */
    public List<Stocks> getStocksList() {
        return stocksList;
    }

    /**
     * Sorting the list
     * @param list
     */
    private static void sortStocksList(List<Stocks> list){
        Collections.sort(list, new Comparator<Stocks>() {
            public int compare(Stocks s1, Stocks s2) {
                if(s1.getRatePerKG()>s2.getRatePerKG()){
                    return 1;
                } else if(s1.getRatePerKG()<s2.getRatePerKG()) {
                    return -1;
                } else {
                    if (s1.getTimeStamp().isAfter(s2.getTimeStamp())) {
                        return 1;
                    } else if (s2.getTimeStamp().isAfter(s1.getTimeStamp())) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            }
        });
    }

}
