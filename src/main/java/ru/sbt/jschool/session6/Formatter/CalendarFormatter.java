package ru.sbt.jschool.session6.Formatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

/**
 * Created by 1 on 13.04.2018.
 */
public class CalendarFormatter implements JSONTypeFormatter<Calendar> {
    @Override
    public String format(Calendar calendar, JSONFormatter formatter, Map<String, Object> ctx) {

        int numberOfIndents = (int)ctx.get("numberOfIndents");
        StringBuilder sb = new StringBuilder(Tools.repeatString(" ", numberOfIndents));

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        sb.append(dateFormat.format(calendar.getTime()));

        return sb.toString();
    }
}
