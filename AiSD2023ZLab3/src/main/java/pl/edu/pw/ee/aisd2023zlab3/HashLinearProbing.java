package pl.edu.pw.ee.aisd2023zlab3;

public class HashLinearProbing<T extends Comparable<T>> extends HashOpenAdressing<T> {

    public HashLinearProbing() {
        super();
    }

    public HashLinearProbing(int size) {
        super(size);
    }

    @Override
    int hashFunc(int key, int i) {
        int m = getSize();

        key = key & Integer.MAX_VALUE;

        int hash = (key % m + i) % m;

        return hash;
    }

}
