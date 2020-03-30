package com.project.goldStockExchange.bo;

import java.time.LocalTime;

public class Stocks implements Cloneable {

    private String sellerName;
    private LocalTime timeStamp;
    private int ratePerKG;
    private int reqOrAvailAmount;

    public Stocks(String sellerName, LocalTime timeStamp, int ratePerKG, int availableAmount) {
        this.sellerName = sellerName;
        this.timeStamp = timeStamp;
        this.ratePerKG = ratePerKG;
        this.reqOrAvailAmount = availableAmount;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public LocalTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getRatePerKG() {
        return ratePerKG;
    }

    public void setRatePerKG(int ratePerKG) {
        this.ratePerKG = ratePerKG;
    }

    public int getReqOrAvailAmount() {
        return reqOrAvailAmount;
    }

    public void setReqOrAvailAmount(int reqOrAvailAmount) {
        this.reqOrAvailAmount = reqOrAvailAmount;
    }

    @Override
    public String toString() {
        return sellerName+"/"+ratePerKG+"/"+reqOrAvailAmount;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
