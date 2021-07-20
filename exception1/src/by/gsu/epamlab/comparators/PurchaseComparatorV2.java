package by.gsu.epamlab.comparators;

import by.gsu.epamlab.entity.PriceDiscountPurchase;
import by.gsu.epamlab.entity.Purchase;

public class PurchaseComparatorV2 extends AbstractPurchaseComparator{

    @Override
    protected int getId (Purchase purchase){
        if (purchase.getClass() == PriceDiscountPurchase.class) {
            return PRICE_DISCOUNT_PURCHASE_ID;
        } else{
            return PURCHASE_ID;
        }
    }
}
