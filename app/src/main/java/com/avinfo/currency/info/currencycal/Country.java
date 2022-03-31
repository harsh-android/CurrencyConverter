package com.avinfo.currency.info.currencycal;

public class Country {
    private String CurrencyName;
    private String countryName;
    private int flag;
    private Double rate;

    public Country(String countryName, String currencyName, int flag, Double rate) {
        this.countryName = countryName;
        this.CurrencyName = currencyName;
        this.flag = flag;
        this.rate = rate;
    }

    public String getCountryName() {
        return this.countryName;
    }

    public String getCurrencyName() {
        return this.CurrencyName;
    }

    public int getFlag() {
        return this.flag;
    }

    public Double getRate() {
        return this.rate;
    }
}
