package br.com.endereco.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {
    
    private static final String DATE_PATTERN = "dd/MM/yyyy";

    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern(DATE_PATTERN);
    
    public static String format(LocalDate data) {
        if (data == null) {
            return null;
        }
        return DATE_FORMATTER.format(data);
    }
    
    public static LocalDate parse(String dataString) {
        try {
            return DATE_FORMATTER.parse(dataString, LocalDate::from);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
    
    public static boolean validDate(String dataString) {
        return DateUtil.parse(dataString) != null;
    }
}
