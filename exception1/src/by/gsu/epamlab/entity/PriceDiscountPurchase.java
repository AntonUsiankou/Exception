package by.gsu.epamlab.entity;

import java.util.Scanner;

public class PriceDiscountPurchase extends Purchase {

    private Byn priceDiscount;
    private static final int NULL_NUMBER = 0;

    private static final String NON_POSITIVE_VALUE_MES = "non positive value ";
    private static final String IN_DISCOUNT_MESSAGE = "in discount";

    public PriceDiscountPurchase() {
    }

    public PriceDiscountPurchase(String name, int price, int number, int priceDiscount) {
        super(name, price, number);
        this.priceDiscount = new Byn(priceDiscount);
    }


    public Byn getPriceDiscount() {
        return priceDiscount;
    }

    public void setPriceDiscount(int kopeckDiscount) {
        if (kopeckDiscount <= NULL_NUMBER) {
           throw new IllegalArgumentException(NON_POSITIVE_VALUE_MES + priceDiscount + IN_DISCOUNT_MESSAGE);
        }
            this.priceDiscount = new Byn(kopeckDiscount);
    }
    @Override
    protected String getDiscount(){
        return getPriceDiscount().toString();
    }

    @Override
    protected String fieldsToString() {
        return super.fieldsToString() + ";" + priceDiscount;
    }

    @Override
    public Byn getCost() {
        return new Byn(getPrice()).subtraction(priceDiscount).multiply(getNumber());
    }
}
