package by.gsu.epamlab.entity;

import java.util.Formatter;

import static by.gsu.epamlab.constants.Constants.*;

public class Purchase implements Comparable<Purchase> {

    private String name;
    private Byn price;
    private int number;

    public Purchase() {
    }

    public Purchase(String name, int price, int number) {
        this.name = name;
        this.price = new Byn(price);
        this.number = number;
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
        formatter.format(FORMATTER_PATTERN, getName(), getPrice(), getNumber(), getDiscount(), getCost());
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



