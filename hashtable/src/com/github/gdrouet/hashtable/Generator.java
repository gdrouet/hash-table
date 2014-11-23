package com.github.gdrouet.hashtable;

import java.util.Random;

/**
 * Génère un nombre aléatoire de mots.
 */
public enum Generator {

    /**
     * Singleton.
     */
    INSTANCE;

    /**
     * Random value generator.
     */
    private Random rand = new Random();

    /**
     * Number of generated of WORDS.
     */
    private static final int NB_WORDS = 10000;

    /**
     * Minimal length.
     */
    private static final int MIN_LEN = 5;

    /**
     * Maximal length.
     */
    private static final int MAX_LEN = 15;

    /**
     * 'A' index in ASCII table (minimal character).
     */
    private static final int A_ASCII = 65;

    /**
     * 'Z' index in ASCII table (maximal character).
     */
    private static final int Z_ASCII = 90;

    /**
     * <p>
     * Generates words.
     * </p>
     *
     * @return the words.
     */
    public String[] generate() {
        final String[] retval = new String[NB_WORDS];

        for (int i = 0; i < NB_WORDS; i++) {
            final int len = rand.nextInt((MAX_LEN - MIN_LEN) + 1) + MIN_LEN;
            String str = "";

            for (int j = 0; j < len; j++) {
                str += (char) (rand.nextInt((Z_ASCII - A_ASCII) + 1) + A_ASCII);
            }

            retval[i] = str;
        }

        return retval;
    }
}
