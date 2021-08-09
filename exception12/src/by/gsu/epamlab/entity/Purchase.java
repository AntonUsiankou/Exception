package by.gsu.epamlab.entity;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.NumField;
import by.gsu.epamlab.exceptions.NonpositiveArgumentException;

import java.util.Formatter;

import static by.gsu.epamlab.constants.Constants.*;

public class Purchase implements Comparable<Purchase> {

    private String name;
    private Byn price;
    private int number;

    public Purchase() {
    }

    public Purchase(String name, int price, int number) {
        setName(name);
        setPrice(price);
        setNumber(number);
    }

    public void setName(String name) {
        if(name == null){
            throw new IllegalArgumentException(EMPTY_NAME_MESSAGE);
        }if(name.isEmpty()){
            throw new IllegalArgumentException(EMPTY_NAME_MESSAGE);
        }
        this.name = name;
    }

    public final void setPrice(int price) {
        if (price <= 0 ){
            throw new NonpositiveArgumentException(price, NumField.PRICE);
        }
        this.price = new Byn(price);
    }

    public final void setNumber(int number) {
        checkNullNumber(number,NumField.NUMBER);
        this.number = number;
    }

    public final void checkNullNumber(int number, NumField field){
        if (number == 0 ){
            throw new NonpositiveArgumentException(number, NumField.NUMBER);
        }
    }

    public String getName() {
        return name;
    }

    public Byn getPrice() {
        return price;
    }

    public int getNumber() {
        return number;
    }

    public Byn getCost() {
        Byn cost = new Byn(price);
        return cost.multiply(number);
    }

    protected String fieldsToString() {
        return name + DELIMITER + price + DELIMITER + number;
    }

    protected String getDiscount() {
        return DASH;
    }

    public String print() {
        Formatter formatter = new Formatter();
        formatter.format(FORMATTING_TABLE_PATTERN_TAIL, getName(), getPrice(), getNumber(),getDiscount(), getCost());
        return formatter.toString();
    }

    public int compareTo(Purchase purchase) {
        return purchase.getCost().compareTo(getCost());
    }

    @Override
    public String toString() {
        return fieldsToString() + DELIMITER + getCost();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Purchase)) return false;
        Purchase purchase = (Purchase) o;

        return name.equals(purchase.name) &&
                price.equals(purchase.price);
    }


}


