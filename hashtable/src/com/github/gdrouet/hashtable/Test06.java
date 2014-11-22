package com.github.gdrouet.hashtable;

public class Test06 {



    /**
     * Test.
     *
     * @param args argument
     */
    public static void main(final String[] args) {
        final Hashtable hashTable = new Hashtable();
        final int start = 5;
        final int end = 20;

        System.out.println("#### Ajout de clés avec des longueurs de 3 ou 4 ####");
        System.out.println("add(\"toto\", \"valeur-toto\") à la place de: " + hashTable.add("toto", "valeur-toto"));
        System.out.println("add(\"foo\", \"valeur-foo\") à la place de: " + hashTable.add("foo", "valeur-foo"));
        System.out.println("add(\"bar\", \"valeur-bar\") à la place de: " + hashTable.add("bar", "valeur-bar"));
        System.out.println("add(\"baz\", \"valeur-baz\") à la place de: " + hashTable.add("baz", "valeur-baz"));
        System.out.println("add(\"titi\", \"valeur-titi\") à la place de: " + hashTable.add("titi", "valeur-titi"));
        System.out.println("add(\"tutu\", \"valeur-tutu\") à la place de: " + hashTable.add("tutu", "valeur-tutu"));
        System.out.println("add(\"tutu\", \"valeur-tutu2\") à la place de: " + hashTable.add("tutu", "valeur-tutu2"));
        hashTable.display();

        System.out.println();
        System.out.println("#### Ajout de clés avec des longueurs de 5 à 20 ####");

        for (int i = start; i <= end; i++) {
            String str = "";

            for (int j = 0;j < i; j++) {
                str += "a";
            }

            System.out.println("add(\"" + str + "\", \"v\"), len=" + str.length() + ": " + hashTable.add(str, "v"));
        }

        hashTable.display();
        System.out.println("get(\"baz\"):" + hashTable.get("baz"));
        System.out.println("remove(\"foo\")" + hashTable.remove("foo"));
        hashTable.display();
        System.out.println("remove(\"baz\")" + hashTable.remove("baz"));
        hashTable.display();
        System.out.println("remove(\"bar\")" + hashTable.remove("bar"));
        hashTable.display();

        System.out.println();
        System.out.println("#### Suppression de clés avec des longueurs de 5 à 20 ####");

        for (int i = start; i <= end; i++) {
            String str = "";

            for (int j = 0;j < i; j++) {
                str += "a";
            }

            System.out.println("remove(\"" + str + "\"), len=\"" + str.length() + "\": " + hashTable.remove(str));
        }

        hashTable.display();
    }
}
