package ru.sbt.jschool.session6.Formatter;

import java.util.Map;

/**
 * Created by 1 on 13.04.2018.
 */
public class ArrayFormatter implements JSONTypeFormatter<Object[]> {
    @Override
    public String format(Object[] object, JSONFormatter formatter, Map ctx) {

        int numberOfIndents = (int)ctx.get("numberOfIndents");
        StringBuilder sb = new StringBuilder(Tools.repeatString(" ", numberOfIndents)).append("[\n");

        String[] objToStr = new String[object.length];

        // преобразуем массив в массив строк. Если исходные массив уже
        // состоял из строк, то добавляем кавычки
        for(int i = 0; i < object.length; i++)
            objToStr[i] = formatter.marshall(object[i]);

        String indent = Tools.repeatString(" ", numberOfIndents + 1);
        String delimeter = ",\n" + Tools.repeatString(" ", numberOfIndents + 1);

        sb.append(indent).append(String.join(delimeter, objToStr));
        sb.append("\n").append(Tools.repeatString(" ", numberOfIndents)).append("]").toString();

        return sb.toString();
    }

}
