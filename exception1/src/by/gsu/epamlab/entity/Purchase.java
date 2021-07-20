package by.gsu.epamlab.entity;

import java.util.Formatter;

public class Purchase implements Comparable<Purchase> {

    private static final String DELIMITER = ";";
    private static final String HYPHEN = "-";
    private static final String FORMATTER_PATTERN = "%-6s %5s %5s %8s %5s";
    private String name;
    private Byn price;
    private int number;

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
        return HYPHEN;
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



