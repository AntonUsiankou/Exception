package by.gsu.epamlab.entity;

import java.util.Formatter;

public class Purchase {
    private static final String HYPHEN = "-";
    private static final String FORMATTER_PATTERN = "%-6s %5s %5s %8s %5s";
    private String name;
    private Byn price;
    private int number;


    public Purchase() {
        this.price = new Byn();
    }

    public Purchase(String name, int price, int number) {
        this.name = name;
        this.price = new Byn(price);
        this.number = number;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byn getPrice() {
        return price;
    }

    public void setPrice(Byn price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }



    public Byn getCost() {
        return new Byn(price).multiply(number);
    }

    public String print(){
        Formatter formatter = new Formatter();
        formatter.format(FORMATTER_PATTERN, getName(), getPrice(), getNumber(),getDiscount(), getCost());
        return formatter.toString();
    }

    protected String fieldsToString() {
        return name + ";" + price + ";" + number;
    }

    protected String getDiscount(){
        return HYPHEN;
    }


    @Override
    public String toString() {
        return fieldsToString() + ";" + getCost();
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