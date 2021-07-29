import by.gsu.epamlab.entity.PurchasesList;
import by.gsu.epamlab.factories.ComparatorFactory;

public class Runner {
    private static final int NUMBER_NULL = 0;
    private static final String IS_FOUND_IN_POS_MESSAGE = " is found at position ";
    private static final String IS_NOT_FOUND_MESSAGE = " isn't found ";

    public static void main(String[] args) {

        final String IN_FILE = args[0];
        final String ADDON_FILE = args[1];
        final String COMPARATOR_VERSION = args[2];

        final String AFTER_CREATION_MESSAGE = "after creation";
        final String BEFORE_SORTING_MESSAGE = "before sorting";
        final String AFTER_SORTING_MESSAGE = "after sorting";
        final String SEARCH_RESULT_MESSAGE = "search results:";

        final int INDEX_ONE = 1;
        final int INDEX_TWO = 2;
        final int INDEX_THREE = 3;
        final int INDEX_MINUS_FIVE = -5;
        final int INDEX_ONE_THOUSAND = 1000;
        final int INDEX_TEN = 10;
        final int INDEX_NULL = 0;

        final int NUMBER_ONE = 1;

        ComparatorFactory.factoryPurchaseComparator(COMPARATOR_VERSION);

        PurchasesList listIn = new PurchasesList(IN_FILE);

        System.out.println();
        System.out.println(AFTER_CREATION_MESSAGE);
        System.out.println();
        listIn.printList();

        PurchasesList listAddon = new PurchasesList(ADDON_FILE);

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