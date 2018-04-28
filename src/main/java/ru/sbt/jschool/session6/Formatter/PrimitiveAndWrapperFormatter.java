package ru.sbt.jschool.session6.Formatter;

import java.util.Map;

/**
 * Created by 1 on 13.04.2018.
 */
public class PrimitiveAndWrapperFormatter implements JSONTypeFormatter<Object>{

    @Override
    public String format(Object object, JSONFormatter formatter, Map<String, Object> ctx) {

        int numberOfIndents = (int)ctx.get("numberOfIndents");
        StringBuilder sb = new StringBuilder("");

        sb.append(Tools.repeatString(" ", numberOfIndents));

        if(object instanceof String)
            sb.append("\"").append(object.toString()).append("\"");
        else
            sb.append(object.toString());

        return sb.append("").toString();
    }
}
