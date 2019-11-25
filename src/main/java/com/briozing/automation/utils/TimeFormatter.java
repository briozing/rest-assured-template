package com.briozing.automation.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * @author KohitiDas
 */
public class TimeFormatter {

    public static SimpleDateFormat javaDateFormatter;
    public static SimpleDateFormat javaDatetimeFormatter;
    public static SimpleDateFormat dateTimeOnlyFormatter;
    public static DateFormat javaDateFormat;

    static {
        javaDateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        javaDatetimeFormatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        dateTimeOnlyFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        javaDateFormat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss Z", java.util.Locale.ENGLISH);
    }

}