package ru.sbt.jschool.session6.Formatter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author NIzhikov
 */
public class DateFormatter implements JSONTypeFormatter<Date> {
    @Override public String format(Date date, JSONFormatter formatter, Map<String, Object> ctx) {

        int numberOfIndents = (int)ctx.get("numberOfIndents");
        StringBuilder sb = new StringBuilder(Tools.repeatString(" ", numberOfIndents));

        SimpleDateFormat dataFormat = new SimpleDateFormat("dd.MM.yyyy");
        sb.append(dataFormat.format(date));

        return sb.toString();

    }
}
