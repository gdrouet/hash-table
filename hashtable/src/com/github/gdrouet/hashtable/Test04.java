package com.github.gdrouet.hashtable;

public class Test04 {

    public static void main(final String[] args) {
        Hashtable hashTable = new Hashtable();
        final int start = 1;
        final int end = 20;

        System.out.println();
        System.out.println("#### Ajout de clés avec des longueurs de " + start + " à " + end + " ####");

        for (int i = start; i <= end; i++) {
            String str = "";

            for (int j = 0;j < i; j++) {
                str += "a";
            }

            System.out.println("add(\"" + str + "\", \"v\"), len=" + str.length() + ": " + hashTable.add(str, "v"));
        }

        hashTable.display();
    }
}
