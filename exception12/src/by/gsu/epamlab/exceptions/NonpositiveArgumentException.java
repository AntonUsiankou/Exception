package by.gsu.epamlab.exceptions;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.NumField;

public class NonpositiveArgumentException extends IllegalArgumentException{

    private int number;
    private NumField field;

    public NonpositiveArgumentException(){
    }

    public NonpositiveArgumentException(int number, NumField field){
        this.field = field;
        this.number = number;
    }

    @Override
    public String toString() {
        return  number + Constants.DELIMITER + field;
    }
}