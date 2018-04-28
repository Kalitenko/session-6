package ru.sbt.jschool.session6;


import java.io.IOException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


/**
 * Created by 1 on 27.04.2018.
 */
public class Property {

    private Properties properties;
    private FileInputStream fis;

    Property(String propertyPath) {

        properties = new Properties();
        try {
            fis = new FileInputStream(propertyPath);
            try {
                properties.load(fis);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int getPort() {
        return Integer.parseInt(properties.getProperty("port"));
    }

}

