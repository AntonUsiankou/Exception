package by.gsu.epamlab.entity;

import by.gsu.epamlab.constants.NumField;
import by.gsu.epamlab.exceptions.NegativeArgumentException;

public class Byn implements Comparable<Byn> {

    private int value;

    public Byn(int value) {
        this.value = value;
    }

    public Byn(int rubs, int coins) {
        this(rubs * 100 + coins);
    }

    public Byn(Byn byn) {
        this(byn.value);
    }

    public int getRubs() {
        return this.value / 100;
    }

    public int getCoins() {
        return this.value % 100;
    }

    public Byn addition(Byn byn) {
        return new Byn(this.value += byn.value);
    }

    public Byn subtraction(Byn byn) {
        if(value < 0){
            throw new NegativeArgumentException(value, NumField.PRICE);
        }
        return new Byn(this.value -= byn.value);
    }

    public Byn multiply(int k) {
        if(value <= 0){
            throw new NegativeArgumentException(value, NumField.PRICE);
        }
        return new Byn(this.value *= k);
    }

    public Byn multiply(double k, RoundMethod roundMethod, int digits) {
        if (value <= 0){
            throw new NegativeArgumentException(value, NumField.PRICE);
        }
        return new Byn(this.value = roundMethod.rounding(this.value * k, digits));
    }

    public Byn round(int digits) {
        return new Byn(round(RoundMethod.ROUND, digits));
    }

    public Byn round(RoundMethod roundMethod, int roundDigits) {
        return new Byn(this.value = roundMethod.rounding(this.value, roundDigits));
    }

    @Override
    public String toString() {
        return String.format(getRubs() + "." + value / 10 % 10 + value % 10);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Byn byn = (Byn) o;
        return this.value == byn.value;
    }

    @Override
    public int compareTo(Byn byn) {
        return this.value - byn.value;
    }


}