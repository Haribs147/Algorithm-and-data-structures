package pl.edu.pw.ee.aisd2023zlab3;

public class HashDoubleHashing<T extends Comparable<T>> extends HashOpenAdressing<T> {

    public HashDoubleHashing() { super(); }

    public HashDoubleHashing(int size) {
        super(size);
    }

    @Override
    protected int hashFunc(int key, int i) {
        int m = getSize();

        key = key & Integer.MAX_VALUE;
        int hash1 = key % m;
        if (m-2 < 1)
            m = 3;
        int hash2 = 1 + ( key % (m-2) );
        return (hash1 + i * hash2) % m;
    }
}
