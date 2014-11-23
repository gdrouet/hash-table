package com.github.gdrouet.hashtable;

public class Test07 {

    public static void main(final String[] args) {
        Hashtable hashTable = new Hashtable();

        System.out.println("add(\"foo\", \"valeur-foo\") à la place de: " + hashTable.add("foo", "valeur-foo"));
        System.out.println("add(\"bar\", \"valeur-bar\") à la place de: " + hashTable.add("bar", "valeur-bar"));
        System.out.println("add(\"baz\", \"valeur-baz\") à la place de: " + hashTable.add("baz", "valeur-baz"));
        hashTable.display();

        System.out.println("remove(\"foo\")" + hashTable.remove("foo"));
        System.out.println("remove(\"baz\")" + hashTable.remove("baz"));
        System.out.println("remove(\"bar\")" + hashTable.remove("bar"));
        System.out.println("remove(\"unkwn\")" + hashTable.remove("unkwn"));
        hashTable.display();
    }
}
