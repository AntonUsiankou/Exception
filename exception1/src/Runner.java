import by.gsu.epamlab.entity.PurchasesList;
import by.gsu.epamlab.factories.ComparatorFactory;

import static by.gsu.epamlab.constants.Constants.*;


public class Runner {

    public static void main(String[] args) {

        ComparatorFactory.factoryPurchaseComparator(args[COMPARATOR_VERSION]);

        PurchasesList listIn = new PurchasesList(args[IN_FILE]);

        System.out.println();
        System.out.println(AFTER_CREATION_MESSAGE);
        System.out.println();
        listIn.printList();

        PurchasesList listAddon = new PurchasesList(args[ADDON_FILE]);

        listIn.insert(INDEX_NULL, listAddon.getPurchaseByIndex(listAddon.size() - NUMBER_ONE));

        listIn.insert(INDEX_ONE_THOUSAND, listAddon.getPurchaseByIndex(INDEX_NULL));

        listIn.insert(INDEX_TWO, listAddon.getPurchaseByIndex(INDEX_TWO));

        listIn.delete(INDEX_THREE);
        listIn.delete(INDEX_TEN);
        listIn.delete(INDEX_MINUS_FIVE);

        System.out.println();
        System.out.println(BEFORE_SORTING_MESSAGE);
        System.out.println();
        listIn.printList();

        listIn.sort();

        System.out.println();
        System.out.println(AFTER_SORTING_MESSAGE);
        System.out.println();
        listIn.printList();

        System.out.println();
        System.out.println(SEARCH_RESULT_MESSAGE);
        listIn.search(listAddon.getPurchaseByIndex(INDEX_ONE));
        listIn.search(listAddon.getPurchaseByIndex(INDEX_THREE));

    }

}