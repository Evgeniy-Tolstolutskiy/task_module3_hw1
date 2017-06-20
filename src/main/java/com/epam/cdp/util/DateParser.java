package com.epam.cdp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Yevhenii_Tolstolutsk on 1/4/2017.
 */
public class DateParser {
    private static String dateFormat = "MM/dd/yyyy";

    /**
     * Returns Date object from string date representation in format MM/dd/yyyy.
     *
     * @param date string date
     * @return Date object
     * @throws ParseException when cannot parse date
     */
    public static Date parse(String date) throws ParseException {
        return new SimpleDateFormat(dateFormat).parse(date);
    }
}
