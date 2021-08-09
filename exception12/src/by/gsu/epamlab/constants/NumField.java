package by.gsu.epamlab.constants;

public enum NumField {
    PRICE("Price"),
    NUMBER("Number"),
    DISCOUNT("Discount"),
    COST("Cost"),
    NAME("Name");

    private final String name;

    NumField(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  name;
    }
}
