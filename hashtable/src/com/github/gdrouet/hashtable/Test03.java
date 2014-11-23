package com.github.gdrouet.hashtable;

import java.util.Arrays;

public class Test03 {

    public static void main(String[] args) {
        final Hashtable hashTable = new Hashtable();
        System.out.print(Arrays.toString(
                Arrays.asList(hashTable.hashFunction.apply("ABC"),
                hashTable.hashFunction.apply("CBA"),
                hashTable.hashFunction.apply("DEF"),
                hashTable.hashFunction.apply("FED")).toArray()));
    }
}
