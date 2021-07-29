package by.gsu.epamlab.comparators;

import by.gsu.epamlab.entity.PriceDiscountPurchase;
import by.gsu.epamlab.entity.Purchase;

import java.util.Comparator;

public class PurchaseComparatorOne implements Comparator<Purchase> {

    @Override
    public int compare(Purchase purchase, Purchase purchaseCurrent) {

        if (purchase.getName().compareTo(purchaseCurrent.getName()) != 0) {
            return purchase.getName().compareTo(purchaseCurrent.getName());
        } else {
            boolean purOne = purchase instanceof PriceDiscountPurchase;
            boolean purTwo = purchase instanceof PriceDiscountPurchase;
            if (purOne & purTwo | !purOne & !purTwo) {
                return purchase.getCost().compareTo(purchaseCurrent.getCost());
            }
            if (purOne) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}
