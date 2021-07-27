package by.gsu.epamlab.factories;

import by.gsu.epamlab.entity.PriceDiscountPurchase;
import by.gsu.epamlab.entity.Purchase;
import by.gsu.epamlab.exceptions.WrongLineException;

import java.util.Scanner;

import static by.gsu.epamlab.constants.PurchaseConstants.DELIMITER;
import static by.gsu.epamlab.constants.PurchasesFactoryConstants.*;

public class PurchasesFactory {

    public static Purchase getPurchaseFromFactory(Scanner scanner) throws  WrongLineException {

        String line = "";

        Purchase purchase = null;

        try {
            line = scanner.next();
            String[] elements = line.split(DELIMITER);

            if (elements.length > NUMBER_FOUR || elements.length < NUMBER_THREE) {
                throw new WrongLineException(WRONG_NUM_ARG_MESSAGE);
            }

            String product;
            int price, number, discount;

            if (elements[FIRST_POSITION].isEmpty()) {
                throw new WrongLineException(EMPTY_NAME_MESSAGE);
            } else {
                product = elements[FIRST_POSITION];
            }

            if (elements[SECOND_POSITION].isEmpty()) {
                throw new WrongLineException(EMPTY_PRICE_MESSAGE);
            } else {
                price = Integer.parseInt(elements[SECOND_POSITION]);
                if (price <= NUMBER_NULL) {
                    throw new WrongLineException(NON_POSITIVE_VALUE_HEAD + price + TAIL_FOR_PRICE);
                }
            }

            if (elements[THIRD_POSITION].isEmpty()) {
                throw new WrongLineException(EMPTY_NUMBER_MESSAGE);
            } else {
                number = Integer.parseInt(elements[THIRD_POSITION]);
                if (number <= NUMBER_NULL) {
                    throw new WrongLineException(NON_POSITIVE_VALUE_HEAD + number + TAIL_FOR_NUMBER);
                }
            }

            if (elements.length == NUMBER_FOUR) {
                if (elements[FOURTH_POSITION].isEmpty()) {
                    throw new WrongLineException(EMPTY_DISCOUNT_MESSAGE);
                } else {
                    discount = Integer.parseInt(elements[FOURTH_POSITION]);
                    if (discount <= NUMBER_NULL) {
                        throw new WrongLineException(NON_POSITIVE_VALUE_HEAD + discount + TAIL_FOR_DISCOUNT);
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