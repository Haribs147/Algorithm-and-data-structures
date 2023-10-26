package pl.edu.pw.ee.aisd2023zlab2.utils;

import java.lang.reflect.Field;
import pl.edu.pw.ee.aisd2023zlab2.services.HashTable;

public class AdvancedGetters {

    public static int getNumOfElems(HashTable hash) {
        String fieldNumOfElems = "nElem";
        try {
            Field field = hash.getClass().getSuperclass().getDeclaredField(fieldNumOfElems);
            field.setAccessible(true);

            int numOfElems = field.getInt(hash);

            return numOfElems;

        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T getHashElemById(HashTable hash, int index) {
        String pathToElemClass = hash.getClass().getSuperclass().getName() + "$Elem";
        String fieldHashElems = "hashElems";
        String fieldValue = "value";
        String fieldNext = "next";

        try {
            Field hashElemsField = hash.getClass().getSuperclass().getDeclaredField(fieldHashElems);
            hashElemsField.setAccessible(true);

            Object[] objects = (Object[]) hashElemsField.get(hash);
            Object elemObject = objects[0];

            Class<?> elemClazz = Class.forName(pathToElemClass);
            Field nextField;

            int i = 0;
            while (i < index) {
                nextField = (elemClazz.cast(elemObject)).getClass().getDeclaredField(fieldNext);
                nextField.setAccessible(true);

                elemObject = nextField.get(elemObject);
                i++;
            }

            Field valueField = (elemClazz.cast(elemObject)).getClass().getDeclaredField(fieldValue);
            valueField.setAccessible(true);

            T value = (T) valueField.get(elemObject);

            return value;

        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException | SecurityException
                | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
