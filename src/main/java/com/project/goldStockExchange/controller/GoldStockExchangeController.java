package com.project.goldStockExchange.controller;

import com.project.goldStockExchange.bo.Stocks;
import com.project.goldStockExchange.service.StockExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("goldStockExchange")
public class GoldStockExchangeController {

    @Autowired
    StockExchangeService stockService;

    /**
     * Function for Buyer to buy Gold stock
     * @param buyerRequest
     * @return
     */
    @RequestMapping("getCurrentStocksStatusAfterBuying")
    public  String getCurrentStocksStatusAfterBuying(@RequestBody String buyerRequest) {
        List<Stocks> resturnStocksStatus  = null;
        String response = "";
        if(null!=buyerRequest) {
            try {
                resturnStocksStatus = stockService.getCurrentStocksStatusAfterBuying(buyerRequest);
                for(Stocks obj : resturnStocksStatus) {
                    response = response +obj.toString()+"\n";
                }
            } catch (Exception e) {
                response = e.getMessage();
            }

        }
        return response;
    }

    /**
     * Function to initialize the gold stocks
     * @param stocksList
     * @return
     */
    @RequestMapping("initializeStocks")
    public String initializeStocks(@RequestBody List<Stocks> stocksList) {
        if(null!=stocksList && stocksList.size()!=0) {
            stockService.initializeStocksList(stocksList);
        }
        return "Done";
    }

    /**
     * Function to get the current stocks status
     * @return
     */
    @RequestMapping("getCurrentStocksList")
    public  List<Stocks> getCurrentStocksList() {
        return stockService.getStocksList();
    }
}
