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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Un annuaire d'étudiants qui associe chaque nom à un ensemble de propriétés qui les caractérise.
 */
public enum  Annuaire {

    /**
     * Singleton.
     */
    INSTANCE;

    /**
     * Table de hachage.
     */
    private HashTable table = new HashTable();

    /**
     * <p>
     * Main method.
     * </p>
     *
     * @param args the command line arguments
     * @throws IOException if any I/O error occurs
     */
    public static void main(final String[] args) throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Boucle tant qu'on ne sort pas
        menu: for (;;) {
            // Afficher le menu
            System.out.println("1. Ajouter un étudiant");
            System.out.println("2. Modifier un étudiant");
            System.out.println("3. Supprimer un étudiant");
            System.out.println("4. Rechercher un étudiant");
            System.out.println("5. Sortir");

            // Lire le choix
            System.out.print("Choix :");
            final String choice = reader.readLine();

            // Evaluer the choice
            switch (choice) {
                case "1":
                    // TODO
                    break;
                case "2":
                    // TODO
                    break;
                case "3":
                    // TODO
                    break;
                case "4":
                    // TODO
                    break;
                case "5":
                    System.out.println("Bye.");
                    break menu;
                default:
                    System.out.println("Mauvais choix.");
            }
        }
    }
}
