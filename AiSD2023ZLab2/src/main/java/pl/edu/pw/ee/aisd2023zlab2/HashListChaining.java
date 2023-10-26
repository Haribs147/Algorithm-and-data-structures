package pl.edu.pw.ee.aisd2023zlab2;

import static java.util.Objects.isNull;
import pl.edu.pw.ee.aisd2023zlab2.services.HashTable;

public abstract class HashListChaining<T extends Comparable<T>> implements HashTable<T> {

    private static final int DEFAULT_SIZE = 127;
    private final Elem nil = null;

    private Elem[] hashElems;
    private int nElem;
    final int size;

    private class Elem<T extends Comparable<T>> {

        private T value;
        private Elem next;

        Elem(T value, Elem next) {
            validateParams(value);

            this.value = value;
            this.next = next;
        }

        private void validateParams(T value) {
            if (isNull(value)) {
                throw new IllegalArgumentException("Value cannot be null!");
            }
        }
    }

    public HashListChaining(int size) {
        validateInputHashSize(size);

        this.size = size;
        hashElems = new Elem[size];
    }

    public HashListChaining() {
        this(DEFAULT_SIZE);
    }

    @Override
    public void add(T value) {
        validateInputValue(value);

        int hashId = countHashId(value);

        Elem<T> oldElem = findElem(value, hashId);

        if (oldElem != nil) {
            oldElem.value = value;
        } else {
            hashElems[hashId] = new Elem(value, hashElems[hashId]);
            nElem++;
        }
    }

    @Override
    public T get(T value) {
        validateInputValue(value);

        int hashId = countHashId(value);

        Elem<T> elem = findElem(value, hashId);

        return elem != nil ? elem.value : null;
    }

    @Override
    public void delete(T value) {
        validateInputValue(value);

        int hashId = countHashId(value);

        Elem prevElem = nil;
        Elem elem = hashElems[hashId];

        while (elem != nil) {

            if (elem.value.compareTo(value) == 0) {

                if (prevElem != nil) {
                    prevElem.next = elem.next;

                } else {
                    hashElems[hashId] = elem.next;
                }

                nElem--;
                break;
            }
            prevElem = elem;
            elem = elem.next;
        }
    }

    public double countLoadFactor() {
        return nElem / size;
    }

    abstract int countHashId(T value);

    private void validateInputHashSize(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Hash size cannot be less than \"1\"!");
        }
    }

    private void validateInputValue(T value) {
        if (isNull(value)) {
            throw new IllegalArgumentException("Value of elem in hash table cannot be null!");
        }
    }

    private Elem<T> findElem(T value, int hashId) {
        Elem<T> currentElem = hashElems[hashId];

        while (currentElem != nil && currentElem.value.compareTo(value) != 0) {
            currentElem = currentElem.next;
        }

        return currentElem;
    }

}
