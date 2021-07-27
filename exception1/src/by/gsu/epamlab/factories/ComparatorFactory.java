package by.gsu.epamlab.factories;

import by.gsu.epamlab.entity.Purchase;

import java.lang.reflect.Constructor;
import java.util.Comparator;

import static by.gsu.epamlab.constants.ComparatorFactoryConstants.*;

public class ComparatorFactory {

    public static Comparator<Purchase> getComparator(String comparatorVersion){

        Comparator<Purchase> comparator = null;
        try {
            Class cls = Class.forName(PACKAGE + POINT + comparatorVersion);
            Constructor constructor = cls.getConstructor();
            comparator = (Comparator<Purchase>) constructor.newInstance();
        } catch (Exception e) {
            System.err.println(ERROR_CREATING_COMPARATOR_MESSAGE);
        }
        return comparator;
    }
}