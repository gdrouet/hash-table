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
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */


/**
 * Un étudiant enregistré en annuaire.
 */
public class Etudiant {

    /**
     * Nom.
     */
    private String nom;

    /**
     * Prénom.
     */
    private String prenom;

    /**
     * Année d'étude.
     */
    private String annee;

    /**
     * Construit une nouvelle instance.
     *
     * @param nom le nom
     * @param prenom le prénom
     * @param annee l'année
     */
    public Etudiant(final String nom, final String prenom, final String annee) {
        this.nom = nom;
        this.annee = annee;
        this.prenom = prenom;
    }

    /**
     * Récupère le nom.
     *
     * @return le nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Récupère le prénom.
     *
     * @return le prénom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Récupère l'année
     *
     * @return l'année
     */
    public String getAnnee() {
        return annee;
    }

    /**
     * Récupère le nom.
     *
     * @return le nom
     */
    public String getLogin() {
        return (getPrenom().charAt(0) + getNom()).toLowerCase();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Etudiant {" +
                "login='" + getLogin() +
                "'nom='" + getNom() +
                "', prenom='" + getPrenom() +
                "', annee='" + getAnnee() +
                "'}";
    }
}
