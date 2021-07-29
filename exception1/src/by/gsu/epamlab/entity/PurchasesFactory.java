package by.gsu.epamlab.entity;

import java.util.Scanner;

public class PurchasesFactory {

    private static final String DELIMITER = ";";
    private static final String HYPHEN = "\t->\t";
    private static final String WRONG_NUM_ARG_MESSAGE = "Wrong number of arguments";
    private static final String EMPTY_NAME_MESSAGE = "empty name";
    private static final String EMPTY_PRICE_MESSAGE = "empty price";
    private static final String EMPTY_NUMBER_MESSAGE = "empty number";
    private static final String EMPTY_DISCOUNT_MESSAGE = "empty discount";
    private static final String NON_POSITIVE_VALUE_HEAD = "non positive value ";
    private static final String TAIL_FOR_PRICE = " in price";
    private static final String TAIL_FOR_NUMBER = " in number";
    private static final String TAIL_FOR_DISCOUNT = " in discount";
    private static final String DISCOUNT_ERROR_MESSAGE = " discount more or equal price";

    private static final int NUMBER_THREE = 3;
    private static final int NUMBER_FOUR = 4;
    private static final int NUMBER_NULL = 0;

    private static final int FIRST_POSITION = 0;
    private static final int SECOND_POSITION = 1;
    private static final int THIRD_POSITION = 2;
    private static final int FOURTH_POSITION = 3;
    public static Purchase getPurchaseFromFactory(Scanner scanner) {

        String line = "";

        Purchase purchase = null;

        try {
            line = scanner.next();
            String[] elements = line.split(DELIMITER);

            if (elements.length > NUMBER_FOUR || elements.length < NUMBER_THREE) {
                throw new IllegalArgumentException(WRONG_NUM_ARG_MESSAGE);
            }

            String product;
            int price, number, discount;

            if (elements[FIRST_POSITION].isEmpty()) {
                throw new IllegalArgumentException(EMPTY_NAME_MESSAGE);
            } else {
                product = elements[FIRST_POSITION];
            }

            if (elements[SECOND_POSITION].isEmpty()) {
                throw new IllegalArgumentException(EMPTY_PRICE_MESSAGE);
            } else {
                price = Integer.parseInt(elements[SECOND_POSITION]);
                if (price <= NUMBER_NULL) {
                    throw new IllegalArgumentException(NON_POSITIVE_VALUE_HEAD + price + TAIL_FOR_PRICE);
                }
            }

            if (elements[THIRD_POSITION].isEmpty()) {
                throw new IllegalArgumentException(EMPTY_NUMBER_MESSAGE);
            } else {
                number = Integer.parseInt(elements[THIRD_POSITION]);
                if (number <= NUMBER_NULL) {
                    throw new IllegalArgumentException(NON_POSITIVE_VALUE_HEAD + number + TAIL_FOR_NUMBER);
                }
            }

            if (elements.length == NUMBER_FOUR) {
                if (elements[FOURTH_POSITION].isEmpty()) {
                    throw new IllegalArgumentException(EMPTY_DISCOUNT_MESSAGE);
                } else {
                    discount = Integer.parseInt(elements[FOURTH_POSITION]);
                    if (discount <= NUMBER_NULL) {
                        throw new IllegalArgumentException(NON_POSITIVE_VALUE_HEAD + discount + TAIL_FOR_DISCOUNT);
                    }
                }
                if(price <= discount){
                    throw new IllegalArgumentException(DISCOUNT_ERROR_MESSAGE);
                }
                purchase = new PriceDiscountPurchase(product, price, number, discount);
            } else {
                purchase = new Purchase(product, price, number);
            }

        } catch (IllegalArgumentException e) {
            System.err.println(line + HYPHEN + e.getMessage());
        }

        return purchase;
    }

}