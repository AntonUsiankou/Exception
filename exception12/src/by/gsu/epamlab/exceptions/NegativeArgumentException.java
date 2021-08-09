package by.gsu.epamlab.exceptions;

import by.gsu.epamlab.constants.NumField;

public class NegativeArgumentException extends NonpositiveArgumentException{

    public NegativeArgumentException() {
    }

    public NegativeArgumentException(int number, NumField field) {
        super(number, field);
    }

    public String getHead(){
        return "Line doesn't have positive argument";
    }

}
