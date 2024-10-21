package co.wedevx.digitalbank.automation.ui.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;
    // we build a logic that reads config file(properties files)

    //static initializer
    //run the block only once for the whole project

    //instance initializers (w/o "static" just {})
    //run the block once for every object creation from the class
    static {
        // filepath - the directory of your properties file
        String filePath = "src/test/resources/properties/digitalbank.properties";

        // this is a class that enables to read files
        // it throws a checked exception
        FileInputStream input = null;
        try {
            input = new FileInputStream(filePath);
            properties = new Properties();
            properties.load(input);


        } catch (IOException e) {
            System.out.println("File not found");
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}