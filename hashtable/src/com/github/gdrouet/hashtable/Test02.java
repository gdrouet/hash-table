package com.github.gdrouet.hashtable;

public class Test02 {

    public static void main(final String[] args) {
        Hashtable hashTable = new Hashtable();
        System.out.println("#### Ajout de clés avec des longueurs de 3 ou 4 ####");
        System.out.println("add(\"toto\", \"valeur-toto\") à la place de: " + hashTable.add("toto", "valeur-toto"));
        System.out.println("add(\"foo\", \"valeur-foo\") à la place de: " + hashTable.add("foo", "valeur-foo"));
        System.out.println("add(\"bar\", \"valeur-bar\") à la place de: " + hashTable.add("bar", "valeur-bar"));
        System.out.println("add(\"baz\", \"valeur-baz\") à la place de: " + hashTable.add("baz", "valeur-baz"));
        System.out.println("add(\"titi\", \"valeur-titi\") à la place de: " + hashTable.add("titi", "valeur-titi"));
        System.out.println("add(\"tutu\", \"valeur-tutu\") à la place de: " + hashTable.add("tutu", "valeur-tutu"));
        hashTable.display();
    }
}
