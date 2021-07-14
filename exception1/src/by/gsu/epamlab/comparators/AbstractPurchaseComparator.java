package by.gsu.epamlab.comparators;

import by.gsu.epamlab.entity.Purchase;

import java.util.Comparator;

public abstract class AbstractPurchaseComparator implements Comparator<Purchase> {

    protected final int PURCHASE_ID = 1;
    protected final int PRICE_DISCOUNT_PURCHASE_ID = 2;

    @Override
    public int compare(Purchase o1, Purchase o2) {
        int result;
        if (o2.getName().equals(o1.getName())) {
            result = getId(o2) - getId(o1);
        } else {
            result = o1.getName().length() - o2.getName().length();
        }
        if (result == 0){
            result = o1.getCost().compareTo(o2.getCost());
        }
        return result;
    }

    protected abstract int getId(Purchase purchase);
}