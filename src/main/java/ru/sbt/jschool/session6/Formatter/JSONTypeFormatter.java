package ru.sbt.jschool.session6.Formatter;

import java.util.Map;

/**
 */
@FunctionalInterface
public interface JSONTypeFormatter<T> {
    String format(T t, JSONFormatter formatter, Map<String, Object> ctx);
}
