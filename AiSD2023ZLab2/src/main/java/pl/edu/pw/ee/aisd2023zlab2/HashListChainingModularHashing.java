package pl.edu.pw.ee.aisd2023zlab2;

public class HashListChainingModularHashing<T extends Comparable<T>> extends HashListChaining<T> {

    public HashListChainingModularHashing() {
        super();
    }

    public HashListChainingModularHashing(int size) {
        super(size);
    }

    @Override
    int countHashId(T value) {
        int hashCode = value.hashCode();

        return (hashCode & Integer.MAX_VALUE) % size; // Dlaczego tak to robimy a nie hascode & size
    }

}
