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
import java.util.function.Function;

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
    private Hashtable table = new Hashtable();

    /**
     * Ajoute un étudiant dans la table de hachage.
     *
     * @param input fonction de lecture sur l'entrée standard
     */
    public void ajouter(final Function<String, String> input) {
        final Etudiant e = new Etudiant(input.apply("Nom: "), input.apply("Prénom: "), input.apply("Année: "));
        table.add(e.getLogin(), e);
        input.apply("Etudiant ajouté. Appuyer sur entrer pour continuer...");
    }

    /**
     * Modifie un étudiant dans la table de hachage.
     *
     * @param input fonction de lecture sur l'entrée standard
     */
    public void modifier(final Function<String, String> input) {
        final Etudiant e = Etudiant.class.cast(table.get(input.apply("Login: ")));

        if (e == null) {
            input.apply("Aucun étudiant dans l'annuaire avec ce login. Appuyer sur entrer pour continuer...");
        } else {
            final Object old = table.add(e.getLogin(), new Etudiant(e.getNom(), e.getPrenom(), input.apply("Nouvelle année: ")));
            input.apply("Le nouvel état vient en remplacement de: " + old + ". Appuyer sur entrer pour continuer...");
        }
    }

    /**
     * Spprime un étudiant  dans la table de hachage.
     *
     * @param input fonction de lecture sur l'entrée standard
     */
    public void supprimer(final Function<String, String> input) {
        final Etudiant e = Etudiant.class.cast(table.get(input.apply("Login: ")));

        if (table.remove(e.getLogin()) == null) {
            input.apply("Aucun étudiant dans l'annuaire avec ce login. Appuyer sur entrer pour continuer...");
        } else {
            input.apply("Etudiant supprimé. Appuyer sur entrer pour continuer...");
        }
    }


    /**
     * Recherche un étudiant et affiche le résultat.
     *
     * @param input fonction de lecture sur l'entrée standard
     */
    public void rechercher(final Function<String, String> input) {
        final Etudiant e = Etudiant.class.cast(table.get(input.apply("Login: ")));
        input.apply((e == null ? "Aucun étudiant dans l'annuaire avec ce login" : e) + "\nAppuyer sur entrer pour continuer...");
    }

    /**
     * <p>
     * Main method.
     * </p>
     *
     * @param args the command line arguments
     * @throws IOException if any I/O error occurs
     */
    public static void main(final String[] args) throws IOException {

        // Lecture sur l'entrée standard
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        final Function<String, String> input = (lbl) -> {
            try {
                System.out.print(lbl);
                return reader.readLine();
            } catch (IOException ioe) {
                ioe.printStackTrace(System.out);
                return null;
            }
        };

        // Boucle tant qu'on ne sort pas
        menu: for (;;) {
            // Afficher le menu
            System.out.println("1. Ajouter un étudiant");
            System.out.println("2. Modifier un étudiant");
            System.out.println("3. Supprimer un étudiant");
            System.out.println("4. Rechercher un étudiant");
            System.out.println("5. Sortir");

            // Lire le choix
            final String choice = input.apply("Choix: ");

            // Evaluer the choice
            switch (choice) {
                case "1":
                    INSTANCE.ajouter(input);
                    break;
                case "2":
                    INSTANCE.modifier(input);
                    break;
                case "3":
                    INSTANCE.supprimer(input);
                    break;
                case "4":
                    INSTANCE.rechercher(input);
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
