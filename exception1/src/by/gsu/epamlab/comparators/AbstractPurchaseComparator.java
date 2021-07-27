package by.gsu.epamlab.comparators;

import by.gsu.epamlab.entity.Purchase;

import java.util.Comparator;

import static by.gsu.epamlab.constants.ComparatorsConstants.NUMBER_NULL;


public abstract class AbstractPurchaseComparator implements Comparator<Purchase> {

    protected final int PURCHASE_ID = 1;
    protected final int PRICE_DISCOUNT_PURCHASE_ID = 2;

    @Override
    public int compare(Purchase o1, Purchase o2) {
        int result;
        if (o1.getName().equals(o2.getName())) {
            result = getId(o2) - getId(o1);
        } else {
            result = o1.getName().length() - o2.getName().length();
        }
        if (result == NUMBER_NULL){
            result = o1.getCost().compareTo(o2.getCost());
        }
        return result;
    }

    protected abstract int getId(Purchase purchase);
}
