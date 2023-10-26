package pl.edu.pw.ee.aisd2023zlab2.services;

public interface HashTable<T extends Comparable<T>> {

    void add(T value);

    T get(T value);

    void delete(T value);
}
