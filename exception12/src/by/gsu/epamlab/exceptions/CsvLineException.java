package by.gsu.epamlab.exceptions;

import by.gsu.epamlab.constants.Constants;

public class CsvLineException extends Exception {
    private String csvLine;
    private String message;

    public CsvLineException() {
        super();
    }

    public CsvLineException( String csvLine, String message) {
        this.csvLine = csvLine;
        this.message = message;
    }

    @Override
    public String toString() {
        return csvLine + Constants.HYPHEN + message;
    }
}
