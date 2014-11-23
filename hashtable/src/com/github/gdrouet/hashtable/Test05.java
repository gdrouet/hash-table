package com.github.gdrouet.hashtable;

public class Test05 {

    public static void main(final String[] args) {
        Hashtable hashTable = new Hashtable();
        System.out.println("#### Ajout de clés avec des longueurs de 3 ou 4 ####");
        System.out.println("add(\"titi\", \"valeur-titi\") à la place de: " + hashTable.add("titi", "valeur-titi"));
        System.out.println("add(\"tutu\", \"valeur-tutu\") à la place de: " + hashTable.add("tutu", "valeur-tutu"));
        System.out.println("add(\"tutu\", \"valeur-tutu2\") à la place de: " + hashTable.add("tutu", "valeur-tutu2"));
        hashTable.display();
    }
}
