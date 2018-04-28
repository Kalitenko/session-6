package ru.sbt.jschool.session6.Formatter;

/**
 * Created by 1 on 22.04.2018.
 */
public class Tools {
    // повторение строки
    static String repeatString(String str, int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++)
            sb.append(str);

        return sb.toString();
    }
}
