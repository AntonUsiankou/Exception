import by.gsu.epamlab.comparators.ComparatorFactory;
import by.gsu.epamlab.entity.PurchasesList;

public class Runner {

    private static final int NUMBER_NULL = 0;
    private static final String IS_FOUND_IN_POS_MESSAGE_ = " is found at position ";
    private static final String IS_NOT_FOUND_MESSAGE = " isn't found ";

    public static void main(String[] args) {

        final int IN_FILE = 0;
        final int ADDON_FILE = 1;
        final int COMPARATOR_VERSION = 2;

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

        if (args.length < 3) {
            System.out.println("command line: java Runner main additional comparator");
        } else {

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

            listIn.sort(ComparatorFactory.getComparator(args[COMPARATOR_VERSION]));

            System.out.println();
            System.out.println(AFTER_SORTING_MESSAGE);
            System.out.println();
            listIn.printList();

            System.out.println();
            System.out.println(SEARCH_RESULT_MESSAGE);
            System.out.println(search(INDEX_ONE, listIn, listAddon));
            System.out.println(search(INDEX_THREE, listIn, listAddon));
        }
    }

    private static String search(int index, PurchasesList in, PurchasesList addon) {

        int id = in.search(addon.getPurchaseByIndex(index));

        return addon.getPurchaseByIndex(index) + (id >= NUMBER_NULL ? IS_FOUND_IN_POS_MESSAGE_ + id : IS_NOT_FOUND_MESSAGE);
    }
}