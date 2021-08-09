package by.gsu.epamlab.entity;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.NumField;
import by.gsu.epamlab.exceptions.CsvLineException;
import by.gsu.epamlab.factories.ComparatorFactory;
import by.gsu.epamlab.factories.PurchasesFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import static by.gsu.epamlab.constants.Constants.*;

public class PurchasesList {

    private List<Purchase> purchases;
    private static final Comparator<Purchase> COMPARATOR = ComparatorFactory.getPurchaseComparator();
    private boolean checkSortedList;

    public PurchasesList() {
        purchases = new ArrayList<>();
    }

    public PurchasesList(String fileName) {

        try (Scanner scanner = new Scanner(new FileReader(PATH + fileName + EXTENSION))) {
            purchases = new ArrayList<>();
            scanner.useLocale(Locale.ENGLISH);
            while (scanner.hasNextLine()) {
                String csvLine = scanner.nextLine();
                try {
                    purchases.add(PurchasesFactory.getPurchaseFromFactory(csvLine));
                } catch (CsvLineException e) {
                    System.err.println(e);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println(FILE_READER_ERROR_MESSAGE);
            purchases = new ArrayList<>();
        }
    }

    public List<Purchase> getPurchases() {
        List<Purchase> clonePurchases = new ArrayList<>();
        clonePurchases.addAll(purchases);
        return clonePurchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        if (purchases == null) {
            throw new NullPointerException();
        }
        this.purchases = purchases;
        checkSortedList = false;
    }

    public int size() {
        return purchases.size();
    }

    public String toTable() {
        StringBuilder builder = new StringBuilder();
        Formatter formatter = new Formatter();

        formatter.format(FORMATTING_TABLE_PATTERN_HEADER, NumField.NAME, NumField.PRICE, NumField.NUMBER,
                NumField.DISCOUNT, NumField.COST);
        builder.append(formatter.toString());

        for (Purchase purchase : purchases) {
            if (purchase instanceof Purchase) {
                builder.append(purchase.print());
            } else {
                builder.append(purchase.print());
            }
        }
        formatter = new Formatter();
        formatter.format(TOTAL_COAST_PRINT_MESSAGE, getTotalCost());
        builder.append(formatter.toString());

        return builder.toString();
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
        checkSortedList = false;
    }

    private boolean isInvalidIndex(int index) {
        return index < NUMBER_NULL || index >= purchases.size();
    }

    public void delete(int index) {
        if (isInvalidIndex(index)) {
            return;
        }
        purchases.remove(index);
        checkSortedList = false;
    }

    public Purchase getPurchaseByIndex(int index) {
        if (isInvalidIndex(index)) {
            return null;
        }
        return purchases.get(index);
    }

    public void sort() {
        Collections.sort(purchases, COMPARATOR);
        checkSortedList = true;
    }

    public int search(Purchase purchase) {
        if (!checkSortedList) {
            sort();
        }
        return Collections.binarySearch(purchases, purchase, COMPARATOR);
    }
}