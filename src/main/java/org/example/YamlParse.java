package org.example;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

public class YamlParse {

    public static void main(String[] args) {
        /*
        By creating a simple YAML file with just key/pair values,
        we can store it in an Object and then cast to a map.
         */
        Object object = null;
        try {
            YamlReader reader = new YamlReader(new FileReader("./src/main/resources/test.yaml"));
            object = reader.read();
        } catch (YamlException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println(object);
        Map map = (Map) object;
        System.out.println(map.get("header_text"));

        // convert hex string to color
        String hexCode = (String) map.get("footer_color");

        Color footerColor = hexStringToColor(hexCode);
    }

    private static Color hexStringToColor(String s) {
        int red = Integer.valueOf(s.substring(0, 2), 16);
        int green = Integer.valueOf(s.substring(2, 4), 16);
        int blue = Integer.valueOf(s.substring(4, 6), 16);

        return new Color(red, green, blue);
    }
}
