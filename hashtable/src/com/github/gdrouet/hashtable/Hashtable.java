/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Guillaume DROUET
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */


package com.github.gdrouet.hashtable;

/**
 * Cette table de hachage permet de stocker une collection d'objets associés à une clé unique.
 */
public class Hashtable {

    /**
     * Taille initiale de la table de hachage.
     */
    private static final int CAPACITY = 5;

    /**
     * Un 'hash' associe le résultat d'une fonction de hachage à une liste de chaînée.
     */
    private static final class Hash {

        /**
         * Résultat du hash.
         */
        private int code;

        /**
         * La liste chaînée.
         */
        private Entry root;
    }

    /**
     * Un maillon d'une liste chaînée.
     */
    private static final class Entry {

        /**
         * La clé du maillon.
         */
        private String key;

        /**
         * La valeur.
         */
        private Object value;

        /**
         * Le maillon suivant.
         */
        private Entry next;
    }

    /**
     * La table.
     */
    private Hash[] table;

    /**
     * Taille de la table.
     */
    private int size;

    /**
     * Construit une nouvelle instance.
     */
    public Hashtable() {
        table = new Hash[CAPACITY];
    }

    /**
     * Ajoute un nouvel élément associé à la clé donnée.
     *
     * @param key la clé
     * @param element l'élément
     * @return la valeur associée à la clé avant ajout
     */
    public Object add(final String key, final Object element) {
        final Hash hash = findHash(key);
        Entry entry = hash.root;
        Entry link = null;

        if (entry != null) {
            if (entry.key.equals(key)) {
                link = entry;
            } else {
                while (entry.next != null && link == null) {
                    if (entry.next.key.equals(key)) {
                        link = entry.next;
                    }

                    entry = entry.next;
                }

                if (link == null) {
                    entry.next = new Entry();
                    link = entry.next;
                }
            }
        } else {
            hash.root = new Entry();
            link = hash.root;
        }

        final Object retval = link.value;
        link.key = key;
        link.value = element;
        return retval;
    }

    /**
     * Récupère la valeur associée à la clé.
     *
     * @param key la clé
     * @return la valeur
     */
    public Object get(final String key) {
        Entry e = findHash(key).root;

        while (e != null) {
            if (e.key.equals(key)) {
                return e.value;
            }

            e = e.next;
        }

        return null;
    }

    /**
     * Affichage l'ensemble des valeurs.
     */
    public void display() {
        System.out.print("display():");

        for (int i = 0; i < size; i++) {
            Entry e = table[i].root;

            while (e != null) {
                System.out.print(" " + e.key + "=" + e.value);
                e = e.next;

                if (i == size - 1 && e == null) {
                    System.out.println();
                }
            }
        }
    }

    /**
     * Calcul un 'Hash' pour une clé donnée.
     *
     * @param key la clé
     * @return le résultat du hachage
     */
    private Hash findHash(final String key) {
        final int code = hash(key) & table.length - 1;

        for (int i = 0; i < size; i++) {
            if (table[i].code == code) {
                return table[i];
            }
        }

        // Taille max atteinte
        if (size == table.length) {
            final Hash[] tmp = new Hash[table.length + CAPACITY];
            System.arraycopy(table, 0, tmp, 0, table.length);
            table = tmp;
        }

        final Hash hash = new Hash();
        hash.code = code;
        table[size++] = hash;
        return hash;
    }

    /**
     * Fonction de hachage.
     *
     * @param key la clé sur laquelle réaliser le calcul
     * @return le résultat
     */
    public int hash(final String key) {
        return key.length();
    }

    /**
     * Test.
     *
     * @param args argument
     */
    public static void main(final String[] args) {
        final Hashtable hashTable = new Hashtable();
        System.out.println("add(\"toto\", \"valeur-toto\") à la place de: " + hashTable.add("toto", "valeur-toto"));
        System.out.println("add(\"foo\", \"valeur-foo\") à la place de: " + hashTable.add("foo", "valeur-foo"));
        System.out.println("add(\"bar\", \"valeur-bar\") à la place de: " + hashTable.add("bar", "valeur-bar"));
        System.out.println("add(\"baz\", \"valeur-baz\") à la place de: " + hashTable.add("baz", "valeur-baz"));
        System.out.println("add(\"titi\", \"valeur-titi\") à la place de: " + hashTable.add("titi", "valeur-titi"));
        System.out.println("add(\"tutu\", \"valeur-tutu\") à la place de: " + hashTable.add("tutu", "valeur-tutu"));
        System.out.println("add(\"tutu\", \"valeur-tutu2\") à la place de: " + hashTable.add("tutu", "valeur-tutu2"));
        hashTable.display();

        System.out.println("get(\"baz\"):" + hashTable.get("baz"));
    }
}