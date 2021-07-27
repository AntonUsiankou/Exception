package by.gsu.epamlab.entity;

import by.gsu.epamlab.exceptions.WrongLineException;
import by.gsu.epamlab.factories.PurchasesFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import static by.gsu.epamlab.constants.PurchasesListConstants.*;

public class PurchasesList {

    private List<Purchase> purchases;

    public PurchasesList() {
        purchases = new ArrayList<>();
    }

    public PurchasesList(String fileName) {
        this();

        try (Scanner scanner = new Scanner(new FileReader(PATH + fileName + EXTENSION))) {
            scanner.useLocale(Locale.ENGLISH);
            while (scanner.hasNext()) {
                Purchase purchase = PurchasesFactory.getPurchaseFromFactory(scanner);
                if (purchase != null) {
                    purchases.add(purchase);
                }
            }
        } catch (WrongLineException | FileNotFoundException e) {
            System.err.println(FILE_READER_ERROR_MESSAGE);
        }
    }

    public int size() {
        return purchases.size();
    }

    public void printList() {

        System.out.printf(FORMATTING_TABLE_PATTERN, NAME_COLUME, PRICE_COLUME, NUMBER_COLUME, DISCOUNT_COLUME, COST_COLUME);
        for (Purchase purchase : purchases) {
            System.out.println(purchase.print());
        }
        System.out.printf(TOTAL_COAST_PRINT_MESSAGE, getTotalCost());
    }

    public Byn getTotalCost() {

        Byn totalCost = new Byn(NUMBER_NULL);

        for (Purchase purchase : purchases) {
            totalCost.addition(purchase.getCost());
        }

        return totalCost;
    }

    public void insert(int index, Purchase purchase) {
        if (isInvalidIndex(index)) {
            index = purchases.size() - NUMBER_ONE;
        }
        purchases.add(index, purchase);
    }

    private boolean isInvalidIndex(int index) {
        return index < NUMBER_NULL || index >= purchases.size();
    }

    public void delete(int index) {
        if (isInvalidIndex(index)) {
            return;
        }
        purchases.remove(index);
    }

    public Purchase getPurchaseByIndex(int index) {
        if (isInvalidIndex(index)) {
            return null;
        }
        return purchases.get(index);
    }

    public void sort(Comparator<Purchase> comparator) {
        purchases.sort(comparator);
    }

    public int search(String productName, Byn price, int numberUnits) {
        return search(productName, price, numberUnits, null);
    }

    public int search(Purchase purchase) {
        return Collections.binarySearch(purchases, purchase, Collections.reverseOrder());
    }

    public int search(String productName, Byn price, int numberUnits, Byn discount) {

        int priceCoins = price.getRubs() * NUMBER_ONE_HUNDRED + price.getCoins();

        Purchase purchase;

        if (discount != null) {
            int discountCoins = discount.getRubs() * NUMBER_ONE_HUNDRED + discount.getCoins();
            purchase = new PriceDiscountPurchase(productName, priceCoins, numberUnits, discountCoins);
        } else {
            purchase = new Purchase(productName, priceCoins, numberUnits);
        }

        return search(purchase);
    }
}