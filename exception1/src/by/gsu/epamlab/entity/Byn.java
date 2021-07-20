package by.gsu.epamlab.entity;

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

    @Override
    public String toString() {
        return getRubs() + "." + value / 10 % 10 + value % 10;
    }

    public Byn addition(Byn byn) {
        this.value += byn.value;
        return this;
    }

    public Byn subtraction(Byn byn) {
        this.value -= byn.value;
        return this;
    }

    public Byn multiply(int k) {
        this.value *= k;
        return this;
    }

    public Byn multiply(double k, RoundMethod roundMethod, int digits) {
        this.value = roundMethod.rounding(this.value * k, digits);
        return this;
    }

    public Byn multiply(double k, int digits) {
        multiply(k, RoundMethod.ROUND, digits);
        return this;
    }

    public Byn multiply(double k) {
        multiply(k, RoundMethod.ROUND, 0);
        return this;
    }

    public Byn multiply(double k, RoundMethod round) {
        multiply(k, round, 0);
        return this;
    }

    public Byn round(RoundMethod round) {
        round(round,0);
        return this;
    }

    public Byn round(int digits) {
        round(RoundMethod.ROUND,digits);
        return this;
    }

    public Byn round(RoundMethod roundMethod, int roundDigits) {
        this.value = roundMethod.rounding(this.value, roundDigits);
        return this;
    }

}