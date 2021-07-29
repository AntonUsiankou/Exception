package by.gsu.epamlab.comparators;

import by.gsu.epamlab.entity.PriceDiscountPurchase;
import by.gsu.epamlab.entity.Purchase;

import java.util.Comparator;

public class PurchaseComparatorTwo implements Comparator<Purchase> {

    @Override
    public int compare(Purchase purchase, Purchase purchaseCurrent) {
        if (purchase.getName().compareTo(purchaseCurrent.getName()) != 0) {
            return purchase.getName().compareTo(purchaseCurrent.getName());
        } else {
            if (purchase.getClass() == purchaseCurrent.getClass()) {
                return purchase.getCost().compareTo(purchaseCurrent.getCost());
            }
            PriceDiscountPurchase discountPurchase = new PriceDiscountPurchase();
            if (purchase.getClass() == discountPurchase.getClass()) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}
