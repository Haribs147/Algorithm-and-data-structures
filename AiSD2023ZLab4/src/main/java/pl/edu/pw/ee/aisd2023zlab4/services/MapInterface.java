package pl.edu.pw.ee.aisd2023zlab4.services;

import pl.edu.pw.ee.aisd2023zlab4.Node;

public interface MapInterface<K extends Comparable<K>, V> {

    public void setValue(K key, V value);

    public V getValue(K key);

    public Node<K, V> getRoot();
}
