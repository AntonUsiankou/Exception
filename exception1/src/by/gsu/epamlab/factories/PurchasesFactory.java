package by.gsu.epamlab.factories;

import by.gsu.epamlab.entity.PriceDiscountPurchase;
import by.gsu.epamlab.entity.Purchase;
import by.gsu.epamlab.exceptions.WrongLineException;

import java.util.Scanner;

import static by.gsu.epamlab.constants.Constants.*;

public class PurchasesFactory {

    public static Purchase getPurchaseFromFactory(String line) throws  WrongLineException {



        String[] elements = line.split(DELIMITER);
        Purchase purchase = new Purchase();

        try {

            if (elements.length > NUMBER_FOUR || elements.length < NUMBER_THREE) {
                throw new WrongLineException(WRONG_NUM_ARG_MESSAGE);
            }

            String product=elements[FIRST_POSITION];;
            int price = Integer.parseInt(elements[SECOND_POSITION]);
            int number=Integer.parseInt(elements[THIRD_POSITION]);


            if (elements[FIRST_POSITION].isEmpty()) {
                throw new WrongLineException(EMPTY_NAME_MESSAGE);
            }

            if (elements[SECOND_POSITION].isEmpty()) {
                throw new WrongLineException(EMPTY_PRICE_MESSAGE);
            }
            if (price <= NUMBER_NULL) {
                throw new WrongLineException(NON_POSITIVE_VALUE_HEAD + price + TAIL_FOR_PRICE);
            }

            if (elements[THIRD_POSITION].isEmpty()) {
                throw new WrongLineException(EMPTY_NUMBER_MESSAGE);
            }

            if (number <= NUMBER_NULL) {
                throw new WrongLineException(NON_POSITIVE_VALUE_HEAD + number + TAIL_FOR_NUMBER);
            }

            if(elements.length == NUMBER_THREE) {
                purchase = new Purchase(product, price, number);
            }else if (elements.length == NUMBER_FOUR) {
               
                int discount = Integer.parseInt(elements[FOURTH_POSITION]);
                if (discount <= NUMBER_NULL) {
                    throw new WrongLineException(NON_POSITIVE_VALUE_HEAD + discount + TAIL_FOR_DISCOUNT);
                }
                if (price <= discount) {
                    throw new WrongLineException(DISCOUNT_ERROR_MESSAGE);
                }
          

                purchase = new PriceDiscountPurchase(product, price, number, discount);
            }

        } catch (RuntimeException e) {
            throw new WrongLineException(line + HYPHEN + e.getMessage());
        }

        return purchase;
    }

}
