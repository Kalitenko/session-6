package ru.sbt.jschool.session6.Formatter;

import java.util.*;

import static java.util.Arrays.asList;

/**
 * @author NIzhikov
 */
public class JSONFormatterImpl implements JSONFormatter {
    private Map<Class, JSONTypeFormatter> types = new HashMap<>();

    public JSONFormatterImpl() {
        // даты
        types.put(Date.class, new DateFormatter());
        types.put(GregorianCalendar.class, new CalendarFormatter());

        // классы-обертки
        types.put(Number.class, new PrimitiveAndWrapperFormatter());
        types.put(Character.class, new PrimitiveAndWrapperFormatter());
        types.put(Boolean.class, new PrimitiveAndWrapperFormatter());

        // строки
        types.put(String.class, new PrimitiveAndWrapperFormatter());

        // массивы
        types.put(Number[].class, new ArrayFormatter());
        types.put(Character[].class, new ArrayFormatter());
        types.put(Boolean[].class, new ArrayFormatter());
        types.put(Object[].class, new ArrayFormatter());

        // коллекции
        types.put(Collection.class, new CollectionFormatter());

        // объекты
        types.put(Object.class, new ObjectFormatter());
    }

    @Override public String marshall(Object obj) {
        if (obj == null)
            return "";

        StringBuilder sb = new StringBuilder("");

        Map<String, Object> ctx = new HashMap<>();

        ctx.put("indent", " ");

        if(ctx.get("numberOfIndents") == null)
            ctx.put("numberOfIndents", 1);
        else{
            int tmp = (int) ctx.get("numberOfIndents") + 1;
            ctx.put("numberOfIndents", tmp);
        }

        return marshall(obj, ctx);
    }

    public String marshall(Object obj, Map<String, Object> ctx) {
        if (types.containsKey(obj.getClass()))
            return types.get(obj.getClass()).format(obj, this, ctx);

        if (obj instanceof Collection) {
            return types.get(Collection.class).format(obj, this, ctx);
        } else if (obj instanceof Map) {
            return types.get(Map.class).format(obj, this, ctx);
        } else if (obj instanceof Number) {
            return types.get(Number.class).format(obj, this, ctx);
        }else if (obj instanceof Number[]) {
            return types.get(Number[].class).format(obj, this, ctx);
        }

        return types.get(Object.class).format(obj, this, ctx);
    }


    @Override public <T> boolean addType(Class<T> clazz, JSONTypeFormatter<T> format) {

        boolean result = false;

        if (!types.containsKey(clazz))
            result = types.put(clazz, format) != null;

        return result;
    }
    // повторение строки
    static public String repeatString(String str, int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++)
            sb.append(str);

        return sb.toString();
    }

}

