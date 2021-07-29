package by.gsu.epamlab.factories;

import by.gsu.epamlab.comparators.PurchaseComparatorOne;
import by.gsu.epamlab.entity.Purchase;

import java.lang.reflect.Constructor;
import java.util.Comparator;

import static by.gsu.epamlab.constants.Constants.*;

public class ComparatorFactory {

    private static Comparator<Purchase> purchaseComparator;

    private ComparatorFactory() {
    }

    public static Comparator<Purchase> getPurchaseComparator() {
        return purchaseComparator;
    }

    public static void factoryPurchaseComparator(String comparatorVersion){
        if (purchaseComparator != null) {
            return;
        }

        try {
            Class cls = Class.forName(PACKAGE+POINT+comparatorVersion);
            Constructor constructor = cls.getConstructor();
            purchaseComparator = (Comparator<Purchase>) constructor.newInstance();
        } catch (Exception e) {
            purchaseComparator = new PurchaseComparatorOne();
        }
    }
}