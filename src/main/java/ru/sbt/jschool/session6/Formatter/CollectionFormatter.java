package ru.sbt.jschool.session6.Formatter;

import java.util.Collection;
import java.util.Map;

/**
 * Created by 1 on 13.04.2018.
 */
public class CollectionFormatter implements JSONTypeFormatter<Collection<Object>> {
    @Override
    public String format(Collection<Object> collection, JSONFormatter formatter, Map<String, Object> ctx) {

        int numberOfIndents = (int)ctx.get("numberOfIndents");
        StringBuilder sb = new StringBuilder();

        Object[] objects = collection.toArray();
        sb.append(formatter.marshall(objects));

        return sb.toString();
    }
}
