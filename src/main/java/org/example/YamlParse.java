package org.example;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

public class YamlParse {

    public static void main(String[] args){

        Object object = null;
        try {
            YamlReader reader = new YamlReader(new FileReader("./src/main/resources/test.yaml"));
            object = reader.read();
        } catch (YamlException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println(object);
        Map map = (Map)object;
//        System.out.println(map.get("phone numbers"));
    }
}
