package by.gsu.epamlab.entity;

import by.gsu.epamlab.constants.NumField;
import by.gsu.epamlab.exceptions.NonpositiveArgumentException;

import static by.gsu.epamlab.constants.Constants.*;

public class PriceDiscountPurchase extends Purchase {

    private Byn priceDiscount;

    public PriceDiscountPurchase() {
    }

    public PriceDiscountPurchase(String productName, int price, int numberUnits, int priceDiscount) {
        super(productName, price, numberUnits);
        setPriceDiscount(priceDiscount);
    }

    private void setPriceDiscount(int priceDiscount) {
        if (priceDiscount <= 0 ){
            throw new NonpositiveArgumentException(priceDiscount, NumField.DISCOUNT);
        }
        this.priceDiscount = new Byn(priceDiscount);
    }

    public Byn getPriceDiscount() {
        return priceDiscount;
    }

    @Override
    protected String fieldsToString() {
        return super.fieldsToString() + DELIMITER + priceDiscount;
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