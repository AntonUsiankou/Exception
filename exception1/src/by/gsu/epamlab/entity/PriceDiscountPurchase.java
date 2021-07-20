package by.gsu.epamlab.entity;

import java.util.Formatter;

public class PriceDiscountPurchase extends Purchase {

    private Byn priceDiscount;

    private static final int NULL_NUMBER = 0;

    private static final String NON_POSITIVE_VALUE_MES = "non positive value ";
    private static final String IN_DISCOUNT_MESSAGE = " in discount";
    private static final String DELEMITER = ";";
    public PriceDiscountPurchase(String productName, int price, int numberUnits, int priceDiscount) {
        super(productName, price, numberUnits);
        setPriceDiscount(priceDiscount);
    }

    private void setPriceDiscount(int priceDiscount) {
        if (priceDiscount <= NULL_NUMBER) {
            throw new IllegalArgumentException(NON_POSITIVE_VALUE_MES + priceDiscount + IN_DISCOUNT_MESSAGE);
        }
        this.priceDiscount = new Byn(priceDiscount);
    }

    public Byn getPriceDiscount() {
        return priceDiscount;
    }

    @Override
    protected String fieldsToString() {
        return super.fieldsToString() + DELEMITER + priceDiscount;
    }

    @Override
    protected String getDiscount() {
        return getPriceDiscount().toString();
    }

    @Override
    public Byn getCost() {
        Byn cost = new Byn(getPrice());
        return cost.subtraction(priceDiscount).multiply(getNumber());
    }
}