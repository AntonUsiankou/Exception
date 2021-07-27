package by.gsu.epamlab.entity;

import static by.gsu.epamlab.constants.PriceDiscountPurchaseConstants.*;

public class PriceDiscountPurchase extends Purchase {

    private Byn priceDiscount;

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