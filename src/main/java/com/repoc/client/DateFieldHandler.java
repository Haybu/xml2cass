package com.repoc.client;

import lombok.extern.slf4j.Slf4j;
import org.exolab.castor.mapping.GeneralizedFieldHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hmohamed on 3/6/16.
 */
@Slf4j
public class DateFieldHandler extends GeneralizedFieldHandler {

    // example: 2014-05-07 18:24:23
    private static final String FORMAT = "yyy-MM-dd HH:mm:ss";

    private SimpleDateFormat formatter = new SimpleDateFormat(FORMAT);

    @Override
    public Object convertUponGet(Object value) {
        if (value == null) {
            return "01-01-1970 10:00:00";    // default value if null date
        }
        Date date = (Date) value;
        return formatter.format(date);
    }

    @Override
    public Object convertUponSet(Object value) {
        Date date = null;
        try {
            date = formatter.parse((String) value);
        }
        catch (ParseException px) {
            log.error("Parse Exception (bad date format) : " + (String) value);
        }
        return date;
    }

    @Override
    public Class getFieldType() {
        return Date.class;
    }

    public Object newInstance(Object parent) throws IllegalStateException {
        return null;
    }
}
