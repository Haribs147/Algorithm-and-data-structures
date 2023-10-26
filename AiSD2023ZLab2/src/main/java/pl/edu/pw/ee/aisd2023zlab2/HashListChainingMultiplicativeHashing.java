package pl.edu.pw.ee.aisd2023zlab2;

import static java.lang.Math.sqrt;

public class HashListChainingMultiplicativeHashing<T extends Comparable<T>> extends HashListChaining<T> {
    static double A = ( (sqrt(5) - 1) /2 );
    public HashListChainingMultiplicativeHashing() {
        super();
    }

    public HashListChainingMultiplicativeHashing(int size) {
        super(size);
    }

    @Override
    int countHashId(T value) {
        int hashCode = value.hashCode();


        return (int)(size*(((hashCode & Integer.MAX_VALUE)*A)%1));
    }
}