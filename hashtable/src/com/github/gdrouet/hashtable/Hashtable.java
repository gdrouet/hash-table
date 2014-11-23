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

import java.util.function.Function;

/**
 * Cette table de hachage permet de stocker une collection d'objets associés à une clé unique.
 */
public class Hashtable {

    /**
     * Taille initiale de la table de hachage.
     */
    private static final int CAPACITY = 16;

    /**
     * Un maillon d'une liste chaînée.
     */
    static final class Entry {

        /**
         * La clé du maillon.
         */
        String key;

        /**
         * La valeur.
         */
        Object value;

        /**
         * Le maillon suivant.
         */
        Entry next;
    }

    /**
     * La table.
     */
    Entry[] table;

    /**
     * Nombre d'entrées dans la table.
     */
    int count;

    /**
     * Hash collisions count.
     */
    int collisions;

    /**
     * Hash function.
     */
    Function<String, Integer> hashFunction;

    /**
     * Construit une nouvelle instance.
     *
     * @param function fonction de hachage
     */
    public Hashtable(final Function<String, Integer> function) {
        table = new Entry[CAPACITY];
        hashFunction = function;
    }

    /**
     * Construit une nouvelle instance.
     */
    public Hashtable() {
        this((s) -> {
            int retval = 0;

            for (int i = 0; i < s.length(); i++) {
                retval += s.charAt(i);
            }

            return retval;
        });
    }

    /**
     * Calcul l'index dans la table pour une clé donnée sur laquelle une fonction de hachage est appliquée.
     *
     * @param key la clé
     * @return le résultat du hachage
     */
    private int findIndex(final String key) {
        int hashCode = hashFunction.apply(key);
        return hashCode & (table.length - 1);
    }

    /**
     * Redimentionne la table de hachage et recalcul l'index de chaque entrée racine.
     */
    private void resize() {
        final Entry[] tmp = new Entry[table.length];
        System.arraycopy(table, 0, tmp, 0, table.length);
        table = new Entry[table.length + CAPACITY];
        count = 0;

        for (Entry e : tmp) {
            while (e != null) {
                e = addEntry(e);
            }
        }
    }

    /**
     * <p>
     * Ajoute l'entrée en paramètre dans la table et écrase l'éventuel maillon suivant.
     * </p>
     *
     * @param e l'entrée à ajouter
     * @return le maillon écrasé
     */
    private Entry addEntry(final Entry e) {
        final Entry retval = e.next;
        final int idx = findIndex(e.key);
        Entry root = table[idx];

        if (root != null) {
            e.next = table[idx];
        } else {
            count++;
            e.next = null;
        }

        table[idx] = e;

        return retval;
    }

    /**
     * Ajoute un nouvel élément associé à la clé donnée.
     *
     * @param key la clé
     * @param element l'élément
     * @return la valeur associée à la clé avant ajout
     */
    public Object add(final String key, final Object element) {
        // Toutes les entrées de la table sont renseignées, on augmente la taille
        if (count == table.length) {
            resize();
        }

        int idx = findIndex(key);
        Entry entry = table[idx];
        Entry link = null;

        // Entrée existante
        if (entry != null) {
            collisions++;

            // Mise à jour de la racine
            if (entry.key.equals(key)) {
                link = entry;
            } else {
                while (entry.next != null && link == null) {
                    // Mise à jour au sein de la liste chainée
                    if (entry.next.key.equals(key)) {
                        link = entry.next;
                    }

                    entry = entry.next;
                }

                // Ajout en bout de chaine
                if (link == null) {
                    entry.next = new Entry();
                    link = entry.next;
                }
            }
        } else {
            // Nouvelle entrée racine dans la table
            link = new Entry();
            table[idx] = link;
            count++;
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
        Entry e = table[findIndex(key)];

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
        System.out.println("display(), (" + count + "/" + table.length + ") entries in the table:");
        int size = 0;

        for (int i = 0; i < table.length; i++) {
            Entry e = table[i];

            while (e != null) {
                System.out.println(" " + e.key + "(len=" + e.key.length() + ", index=" + i + ") = " + e.value);
                e = e.next;
                size++;
            }
        }

        System.out.println();
        System.out.println(size  + " elements displayed.");
    }

    /**
     * Supprime un élément de la table de hachage.
     *
     * @param key la cle de l'élément à supprimé
     * @return la valeur associé à l'entrée supprimée
     */
    public Object remove(final String key) {
        final int idx = findIndex(key);
        Entry e = table[idx];
        Entry prev = null;

        while (e != null) {
            // Entrée trouvée
            if (e.key.equals(key)) {
                final Object retval = e.value;

                // Le prochain devient la racine
                if (prev == null) {
                    table[idx] = e.next;

                    if (e.next == null) {
                        count--;
                    }
                } else {
                    // Le suivant du précédent est changé
                    prev.next = e.next;
                }

                return retval;
            }

            prev = e;
            e = e.next;
        }

        return null;
    }
}