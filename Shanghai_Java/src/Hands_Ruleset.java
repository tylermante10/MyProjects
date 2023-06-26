import java.util.Arrays;
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
     * DECK_SIZE: A Shanghai Rummy deck uses 2 decks (2 * 52 = 104), including
     * two jokers (0, 2000 as an INT)
     *
     */
    private static int DECK_SIZE = 106;
    public static int DECK_IDX = 0;
    private static int CARDS_PER_HAND = 11;
    public static int SUITES_PER_DECK = 26;

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Hands_Ruleset() {
    }

    /**
     * Creates a deck of 104 cards to be matched for suites
     *
     * @return suites_container- the array of sorted cards by suite
     */
    private static int[][] create_definition_deck() {
        /* Fill the deck */
        int[][] suites_container = new int[4][SUITES_PER_DECK];
        int[] suite = new int[SUITES_PER_DECK];
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
                if (rng == 1 || rng == 105) {
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
     * Function for dealing cards to the players. Return an array the size of
     * players, each with 11 cards (i.e. the number of cards to begin play
     * with).
     *
     * @param master_deck-
     *            the deck to deal cards from
     * @param num_players-
     *            the number of players to deal cards to
     * @return
     */

    public static int[][] deal_cards(int[] master_deck, int num_players) {
        int[][] dealt_array = new int[num_players][CARDS_PER_HAND];
        int ct = 0;
        int card_index = 0;
        int i = 0;
        int dealers_amt = CARDS_PER_HAND * num_players;
//        System.out.printf("Dealers amount %d", dealers_amt);
        while (i < dealers_amt) {
            if (ct < num_players) {
                dealt_array[ct][card_index] = master_deck[i];
                DECK_IDX++;
                i++;
//                System.out.printf("HERE %d%n", card_index);
                ct++;
            } else {
                card_index++;
                ct = 0;
            }

        }
        return dealt_array;
    }

    /**
     * Function is responsible for identifying a given card number, 0-106, and
     * it's suit according to this definition:
     *
     * SUIT DEFINITION: 0, 106 = Joker Spades: 1-26 Clubs: 27-54 Hearts: 55-78
     * Diamonds: 79-105
     *
     * @param check-
     *            the integer card to be checked
     * @return suit- the string description of suit
     */
    public static String check_suit(int check) {
        String suit = "";
        if (check == 2000 || check == 0) {
            suit = "Joker";
        } else if (check >= 0 && check <= 25) {
            suit = "Spade";
        } else if (check >= 26 && check <= 51) {
            suit = "Club";
        } else if (check >= 52 && check <= 77) {
            suit = "Heart";
        } else {
            suit = "Diamond";
        }
        return suit;
    }

    /**
     * Function determines the proper name of a card. I.e 26 = A of clubs
     *
     * @param card_num
     * @return String card_type- the card is a number/type AND a suit
     */
    public static Map<Integer, String> card_map() {
        String[] suites = new String[4];
        suites[0] = "Spade";
        suites[1] = "Club";
        suites[2] = "Hearts";
        suites[3] = "Diamonds";

        Map<Integer, String> card_map = new Map1L<Integer, String>();
        int suite_ct = 0;
        int i = 0;
        int card_key = (suite_ct * SUITES_PER_DECK) + i;
        String suit = suites[suite_ct];
        while (suite_ct < suites.length) {
            suit = suites[suite_ct];
            card_map.add(card_key, ("A of " + suit));
            card_key++;
            card_map.add(card_key, ("A of " + suit));
            card_key++;
            card_map.add(card_key, "2 of " + suit);
            card_key++;
            card_map.add(card_key, "3 of " + suit);
            card_key++;
            card_map.add(card_key, "4 of " + suit);
            card_key++;
            card_map.add(card_key, "5 of " + suit);
            card_key++;
            card_map.add(card_key, "6 of " + suit);
            card_key++;
            card_map.add(card_key, "7 of " + suit);
            card_key++;
            card_map.add(card_key, "8 of " + suit);
            card_key++;
            card_map.add(card_key, "9 of " + suit);
            card_key++;
            card_map.add(card_key, "10 of " + suit);
            card_key++;
            card_map.add(card_key, "J of " + suit);
            card_key++;
            card_map.add(card_key, "2 of " + suit);
            card_key++;
            card_map.add(card_key, "3 of " + suit);
            card_key++;
            card_map.add(card_key, "4 of " + suit);
            card_key++;
            card_map.add(card_key, "5 of " + suit);
            card_key++;
            card_map.add(card_key, "6 of " + suit);
            card_key++;
            card_map.add(card_key, "7 of " + suit);
            card_key++;
            card_map.add(card_key, "8 of " + suit);
            card_key++;
            card_map.add(card_key, "9 of " + suit);
            card_key++;
            card_map.add(card_key, "10 of " + suit);
            card_key++;
            card_map.add(card_key, "J of " + suit);
            card_key++;
            card_map.add(card_key, "Q of " + suit);
            card_key++;
            card_map.add(card_key, "Q of " + suit);
            card_key++;
            card_map.add(card_key, "K of " + suit);
            card_key++;
            card_map.add(card_key, "K of " + suit);
            card_key++;
            i++;
//                System.out.printf("HERE %d%n", card_index);
            suite_ct++;
        }
        return card_map;
    }

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

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        // TODO HERE: Make a map of rulesets
        Map<Integer, String> ruleMap = new Map1L<Integer, String>();
        ruleSet_Map_Definition(ruleMap);

        // Adding 2 decks for Shanghai
        int[] master_deck = create_shuffled_shanghai_deck();

        // Suites Container Double array with suites
        int[][] suitesContainer = create_definition_deck();
        int[] spades = suitesContainer[0];
        int[] clubs = suitesContainer[1];
        int[] hearts = suitesContainer[2];
        int[] diamonds = suitesContainer[3];
        clubs[0] = 26;
        hearts[0] = 52;
        diamonds[0] = 78;
        for (int i = 0; i < suitesContainer[0].length; i++) {
            System.out.printf("Spades[%d] of %d%n", i, spades[i]);
            System.out.printf("Clubs[%d] of %d%n", i, clubs[i]);
            System.out.printf("Hearts[%d] of %d%n", i, hearts[i]);
            System.out.printf("Diamond[%d] of %d%n", i, diamonds[i]);

        }

        // Debug to see if these are correct
        // ^- not correct- assigning proper first values for last 3
        clubs[0] = 26;
        hearts[0] = 52;
        diamonds[0] = 78;
        // HUGE statements here... Making the array of cards for the table by...
        // && Creating the defintion of numbers -> card
        int[][] players = deal_cards(master_deck, 3);
        Map<Integer, String> card_map = card_map();

        int player_me = 0;
        int check = 0;
        // Make a function for this one line - as an option for users to sort their cards
        Arrays.sort(players[player_me]);
        // Loops to show all players hands that the cards are shuffled but sorted for each array
        for (int j = 0; j < players.length; j++) {
            for (int k = 0; k < players[player_me].length; k++) {
                check = players[player_me][k];
                System.out.println("" + check);
                String card = card_map.value(check);
                System.out.printf("Player %d sorted hand, card:" + card + " \n",
                        j + 1);
            }
        }

// QA TEST BELOW
//        for (int j = 0; j < 26; j++) {
//            System.out.printf("Spade: %d%n", spades[j]);
//            System.out.printf("Club: %d%n", clubs[j]);
//            System.out.printf("Heart: %d%n", hearts[j]);
//            System.out.printf("Diamond: %d%n", diamonds[j]);
//        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
