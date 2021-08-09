import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.entity.PurchasesList;
import by.gsu.epamlab.factories.ComparatorFactory;

import java.util.List;

import static by.gsu.epamlab.constants.Constants.*;


public class Runner {

    public static void main(String[] args) {

        if (args.length == NUMBER_THREE) {
            ComparatorFactory.factoryPurchaseComparator(args[COMPARATOR_VERSION]);

            PurchasesList listIn = new PurchasesList(args[IN_FILE]);

            printPurchaseList(listIn, AFTER_CREATION_MESSAGE);

            PurchasesList listAddon = new PurchasesList(args[ADDON_FILE]);

            listIn.insert(INDEX_NULL, listAddon.getPurchaseByIndex(listAddon.size() - NUMBER_ONE));

            listIn.insert(INDEX_ONE_THOUSAND, listAddon.getPurchaseByIndex(INDEX_NULL));

            listIn.insert(INDEX_TWO, listAddon.getPurchaseByIndex(INDEX_TWO));

            listIn.delete(INDEX_THREE);
            listIn.delete(INDEX_TEN);
            listIn.delete(INDEX_MINUS_FIVE);

            printPurchaseList(listIn, BEFORE_SORTING_MESSAGE);

            listIn.sort();

            printPurchaseList(listIn, AFTER_SORTING_MESSAGE);

            System.out.println(SEARCH_RESULT_MESSAGE);
            printBinarySearch(listIn, listAddon, INDEX_ONE);
            printBinarySearch(listIn, listAddon, INDEX_THREE);
        }else{
            System.out.println(FALSE_NUMBER_OF_ARGUMENTS);
        }
    }

    private static void printPurchaseList(PurchasesList purchasesList, String message) {
        System.out.println(message);
        System.out.println(purchasesList.toTable());
        System.out.println();
    }

    private static void printBinarySearch(PurchasesList inPurchasesList, PurchasesList addonPurchasesList, int index) {
        int indexAfterSearch = inPurchasesList.search(addonPurchasesList.getPurchaseByIndex(index));
        if (indexAfterSearch < 0) {
            System.out.println(IS_NOT_FOUND_MESSAGE);
        } else {
            System.out.println(IS_FOUND_IN_POS_MESSAGE + indexAfterSearch + SPACE +  addonPurchasesList.getPurchaseByIndex(index));
        }
    }
}