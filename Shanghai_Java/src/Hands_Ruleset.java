import java.util.Random;

import components.map.Map;
import components.map.Map1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * The family friendly card game of Shanghai brought to you by.
 *
 * @author Tyler Mante
 *
 */
public final class Hands_Ruleset {
    /**
     * DECK_SIZE: A shanghai deck uses 2 decks (2 * 52 = 104), including two
     * jokers (2000 as an INT)
     *
     */
    private static int DECK_SIZE = 106;

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Hands_Ruleset() {
    }

    /**
     * Creates a deck of 104 cards for Shanghai definition of cards
     *
     * @return create_deck - the array filled by RNG
     */
    private static int[][] create_definition_deck() {
        /* Fill the deck */
        int[][] suites_container = new int[4][26];
        int[] suite = new int[26];
        int container_num = 0;
        int idx = -1;
        boolean suits_filled = false;

        while (!suits_filled) {
            idx++;
            suite = suites_container[container_num];
            if (idx == 26) {
                container_num++;
                idx = 0;
            } else {
                suite[idx] = (container_num * 26) + idx;
            }
            if (container_num == 4) {
                suits_filled = true;
            }
        }
        return suites_container;
    }

    /**
     * Creates a deck of 104 cards for Shanghai to be played!
     *
     * @return create_deck - the array filled by RNG
     */
    private static int[] create_shuffled_shanghai_deck() {
        int[] deck = new int[DECK_SIZE];
        int[] seen = new int[DECK_SIZE];
        boolean empty_card = false;
        Random random = new Random();
        /* Fill the deck */
        for (int i = 0; i < DECK_SIZE; i++) {
            int rng = random.nextInt(DECK_SIZE);
            if (seen[rng] == 0) {
                if (rng == 1 || rng == 106) {
                    deck[i] = 2000;
                } else {
                    deck[i] = rng;
                    seen[rng] = 1;
                }
            } else {
                empty_card = true;
                while (empty_card) {
                    rng = random.nextInt(DECK_SIZE);
                    if (seen[rng] == 0) {
                        deck[i] = rng;
                        seen[rng] = 1;
                        empty_card = false;
                    }
                }
            }
        }
        return deck;
    }

    /**
     * SUIT DEFINITION: 0, 106 = Joker
     *
     * Spades: 1-26 Clubs: 27-54 Hearts: 55-78 Diamonds: 79-105
     *
     *
     *
     */
    /**
     * Checks if the cards submitted during hand 6 are truly a set, or not
     *
     * @param array-
     *            the 6 cards attempting to be "putDown"
     * @return
     */
    public static boolean hand6(int[] array) {
        return false;
    }

    /**
     * Goal: Use Map->Key value data structure Key-> hand number Value ->
     * ruleset
     */
    private static void ruleSet_Map_Definition(Map ruleMap) {

    }

//    /**
//     * Put a short phrase describing the static method myMethod here.
//     */
//    private static void myMethod(int a) {
//        /*
//         * Put your code for myMethod here
//         */
//        int[] array = new int[a];
//        for (int idx = 0; idx < a; idx++) {
//            array[idx] = a + 6;
//            System.out.printf("a is %d\n", array[idx]);
//        }
//    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Put your main program code here; it may call myMethod as shown
         */
//        int a = 3;
//        int i = -6;
//        while (i < a) {
//            out.println("Hello World I am Tyler");
//            i++;
//        }
//        myMethod(a);
        // TODO HERE: Make a map of rulesets
        Map<Integer, String> ruleMap = new Map1L<Integer, String>();
        ruleSet_Map_Definition(ruleMap);

        // Adding 2 decks for Shanghai
        int[] master_deck = create_shuffled_shanghai_deck();
        for (int i = 0; i < master_deck.length; i++) {
            System.out.printf("Card #%d: %d%n", i, master_deck[i]);
        }

        // Suites Container Double array with suites
        int[][] suitesContainer = create_definition_deck();
        int[] spades = suitesContainer[0];
        int[] clubs = suitesContainer[1];
        int[] hearts = suitesContainer[2];
        int[] diamonds = suitesContainer[3];

        // Debug to see if these are correct
        // ^- not correct- assigning proper first values for last 3
        clubs[0] = 26;
        hearts[0] = 52;
        diamonds[0] = 78;
        for (int j = 0; j < 26; j++) {
            System.out.printf("Spade: %d%n", spades[j]);
            System.out.printf("Club: %d%n", clubs[j]);
            System.out.printf("Heart: %d%n", hearts[j]);
            System.out.printf("Diamond: %d%n", diamonds[j]);
        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
