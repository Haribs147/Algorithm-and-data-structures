package pl.edu.pw.ee.aisd2023zlab3;

import pl.edu.pw.ee.aisd2023zlab3.exceptions.NotImplementedException;

public class HashQuadraticProbing<T extends Comparable<T>> extends HashOpenAdressing<T> {

    public HashQuadraticProbing() {
        super();
    }

    public HashQuadraticProbing(int size) {
        super(size);
    }

    @Override
    public int hashFunc(int key, int i) {
        int m = getSize();
        key = key & Integer.MAX_VALUE;
        int c1 = 6;
        int c2 = 9;
        return (key % m + c1 * i + c2 * i * i) % m;

    }

}
