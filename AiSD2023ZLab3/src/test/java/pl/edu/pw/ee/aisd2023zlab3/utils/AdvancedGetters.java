package pl.edu.pw.ee.aisd2023zlab3.utils;

import java.lang.reflect.Field;
import pl.edu.pw.ee.aisd2023zlab3.services.HashTable;

public class AdvancedGetters {

    public static int getNumOfElems(HashTable<?> hash) {
        String fieldNumOfElems = "nElems";
        
        try {
            Field field = hash.getClass().getSuperclass().getDeclaredField(fieldNumOfElems);
            field.setAccessible(true);

            int numOfElems = field.getInt(hash);

            return numOfElems;

        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
