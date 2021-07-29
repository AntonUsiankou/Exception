package by.gsu.epamlab.entity;

import by.gsu.epamlab.exceptions.WrongLineException;
import by.gsu.epamlab.factories.ComparatorFactory;
import by.gsu.epamlab.factories.PurchasesFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import static by.gsu.epamlab.constants.PurchaseConstants.HYPHEN;
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
                try {
                    purchases.add(PurchasesFactory.getPurchaseFromFactory(scanner.nextLine()));
                } catch (WrongLineException e) {
                    System.err.println(scanner.nextLine() + HYPHEN + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
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

    public void sort() {
        Collections.sort(purchases, ComparatorFactory.getPurchaseComparator());
    }

    public void search(Purchase purchase) {

        int index = Collections.binarySearch(purchases, purchase, ComparatorFactory.getPurchaseComparator());
        if (index < 0) {
            System.out.println("Не найдено");
        } else {
            System.out.println(" Покупка со спецификации найдена на " + index);
        }
    }
}