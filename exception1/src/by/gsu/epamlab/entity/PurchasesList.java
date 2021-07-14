package by.gsu.epamlab.entity;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class PurchasesList {

    private final static String FILE_PATH = "src/";
    private final static String FILE_EXTENSION = ".csv";

    private final static String FILE_READER_ERROR_MESSAGE = "The file you are looking for does not exist.";
    private final static String DELETING_INDEX_ERROR_MESSAGE = "Error get Purchase: invalid index";
    private final static String TOTAL_COAST_PRINT_MESSAGE = "Total cost %26s\n";
    private final static String FORMATTING_TABLE_PATTERN = "%-6s %5s %5s %5s %5s\n";
    private final static String NAME_COLUME = "Name";
    private final static String PRICE_COLUME = "Price";
    private final static String NUMBER_COLUME = "Number";
    private final static String DISCOUNT_COLUME = "Discount";
    private final static String COST_COLUME = "Cost";

    private static final int NUMBER_NULL = 0;
    private static final int NUMBER_ONE = 1;
    private static final int NUMBER_ONE_HUNDRED = 100;

    private List<Purchase> purchases;

    public PurchasesList() {
        purchases = new ArrayList<Purchase>();
    }

    public PurchasesList(String fileName) {
        this();

        try (Scanner scanner = new Scanner(new FileReader(FILE_PATH + fileName + FILE_EXTENSION))) {
            scanner.useLocale(Locale.ENGLISH);
            while(scanner.hasNext()){
                Purchase purchase = PurchasesFactory.getPurchaseFromFactory(scanner);
                if(purchase != null){
                    purchases.add(purchase);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println(FILE_READER_ERROR_MESSAGE);
        }
    }

    public int size() {
        return purchases.size();
    }

    public boolean isInvalidIndex(int index){
        return index < NUMBER_NULL || index >= purchases.size();
    }

    public void insert (int index, Purchase purchase){
        if(isInvalidIndex(index)){
            index = purchases.size() - NUMBER_ONE;
        }
        purchases.add(index, purchase);
    }

    public void delete(int index){
        if(isInvalidIndex(index)){
            System.out.println(DELETING_INDEX_ERROR_MESSAGE);
            //return;
        }
        purchases.remove(index);
    }
    public Purchase getPurchaseByIndex(int index) {

        if (isInvalidIndex(index)) {
            System.err.println("Error get Purchase: invalid index");
            return null;
        }
        return purchases.get(index);
    }

    public void printList() {
        System.out.printf(FORMATTING_TABLE_PATTERN, NAME_COLUME, PRICE_COLUME, NUMBER_COLUME, DISCOUNT_COLUME, COST_COLUME);
        for(Purchase purchase : purchases){
            System.out.println(purchase.print());
        }
        System.out.printf(TOTAL_COAST_PRINT_MESSAGE, getTotalCost());
    }

    public Byn getTotalCost (){
        Byn totalCost = new Byn();

        for (Purchase purchase : purchases){
            totalCost.addition(purchase.getCost());
        }
        return totalCost;
    }

    public void sort(Comparator<Purchase> comparator){
        purchases.sort(comparator);
    }

    public int search (String productName, Byn price, int numberUnits){
        return search(productName,price, numberUnits, null);
    }

    public int search (Purchase purchase){
        return Collections.binarySearch(purchases, purchase, Collections.reverseOrder());
    }

    public int search(String productName, Byn price, int numberUnits, Byn discount){

        int priceCoins = price.getRubs() * NUMBER_ONE_HUNDRED + price.getCoins();

        Purchase purchase;

        if(discount != null){
            int discountCoins = discount.getRubs() * NUMBER_ONE_HUNDRED + discount.getCoins();
            purchase = new PriceDiscountPurchase(productName, priceCoins,numberUnits, discountCoins);
        }else{
            purchase = new Purchase(productName, priceCoins, numberUnits);
        }

        return search(purchase);
    }
}
