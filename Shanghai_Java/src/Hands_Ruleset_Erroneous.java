import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import components.map.Map;
import components.map.Map1L;

/**
 * The family friendly card game of Shanghai brought to you by.
 *
 * @author Tyler Mante
 *
 */
public final class Hands_Ruleset_Erroneous {
    /**
     * DECK_SIZE: A Shanghai Rummy deck uses 2 decks (2 * 52 = 104), including
     * two jokers (0, 2000 as an INT)
     *
     */
    private static int DECK_SIZE = 106;
    public static int DECK_IDX = 0;
    private static int CARDS_PER_HAND = 17;
    public static int CARDS_PER_DEAL = 11;
    public static int SUITES_PER_DECK = 26;

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Hands_Ruleset_Erroneous() {
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
        int dealers_amt = CARDS_PER_DEAL * num_players;
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
     *
     * @param potential_set-
     *            the 3 cards to be checked
     * @return- true or false depending on if set criteria is met
     */
    public static boolean isSet(int[] potential_set) {
        return false;
    }

    /**
     *
     *
     * @param players_total
     * @param player_turn
     * @param player_me
     * @param master_deck
     * @param my_hand_idx
     * @param players
     * @param input
     * @return
     */
    public static int hand_one(int players_total, int player_turn,
            int player_me, int[] master_deck, int my_hand_idx, int[][] players,
            Scanner input, Map<Integer, String> card_map) {
        System.out.println("THIS HAND: 2 sets of 3");
        int numGroups = 2;
        int numQual = 3;
        int[][] put_down = new int[players_total][numGroups];
        int[] set1 = new int[numQual];
        int[] set2 = new int[numQual];
        if (player_turn == player_me) {
            int draw = master_deck[DECK_IDX];
            my_hand_idx++;
            players[player_turn][my_hand_idx] = draw;
            DECK_IDX++;
            System.out.println("Select your action: [1] Go down [2] Discard");
            int player_action = input.nextInt();
            if (player_action < 1 || player_action > 2) {
                System.out.println("Assuming Discard.");
            } else {
                if (player_action == 1) {
                    System.out.println(
                            "Select your sets: Set 1(only 3 numbers):");
                    for (int idx = 0; idx < my_hand_idx; idx++) {
                        int card = players[player_turn][idx];
                        System.out.println(card_map.value(card));
                    }
                    String three_digits = input.nextLine();
                    int digit_three = Integer
                            .parseInt(three_digits.substring(0, 0));
                    int digit_two = Integer
                            .parseInt(three_digits.substring(1, 1));
                    int digit_one = Integer
                            .parseInt(three_digits.substring(1, 1));
                    System.out.println("Select your set 2(only 3 numbers):");
                    for (int idx = 0; idx < my_hand_idx; idx++) {
                        System.out.println(players[player_turn][idx]);
                    }
                    String three_last = input.nextLine();
                    int digit_four = Integer
                            .parseInt(three_last.substring(0, 0));
                    int digit_five = Integer
                            .parseInt(three_last.substring(1, 1));
                    int digit_six = Integer
                            .parseInt(three_last.substring(1, 1));

                }
            }
        }
        return 2;
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
        // Make a map of rulesets for a given int hand
        Map<Integer, String> ruleMap = new Map1L<Integer, String>();
        ruleSet_Map_Definition(ruleMap);
        int hand = 1;
        int my_hand_idx = 10;
        // Defines who's turn it is to play!
        int player_turn = 0;

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

        // HUGE statements here... Making the array of cards for the table by...
        // && Creating the defintion of numbers -> card
        Map<Integer, String> card_map = card_map();
        Scanner input = new Scanner(System.in);

        // player_me is player 1, total players by user entering in 1-4
        int player_me = 0;
        int players_total = 0;
        System.out.println("Enter number of players [1] [2] [3] [4]");
        players_total = input.nextInt();
        if (players_total < 0 || players_total > 4) {
            System.out.println(
                    "Enter one of these numbers players [1] [2] [3] [4] and [0] to quit");
        } else if (players_total == 0) {
            return;
        }
        // Deal the cards out
        int[][] players = deal_cards(master_deck, players_total);

        // Sort the hands for each player
        for (int j = 0; j < players.length; j++) {
            Arrays.sort(players[j]);
        }
        int game_state = hand;
        // Main game loop
        boolean game_in_session = true;
        while (game_in_session) {
            if (hand == 1) {
                game_state = hand_one(players_total, player_turn, player_me,
                        master_deck, my_hand_idx, players, input, card_map);
            } else if (hand == 2) {
                hand++;
//                game_state = hand_two(hand);
            } // continue with this flow
            else {
                game_in_session = false;
            }
        }

//        // Make a function for this one line - as an option for users to sort their cards
        // Arrays.sort(players[player_me]);
//        // Loops to show all players hands that the cards are shuffled but sorted for each array
//        for (int j = 0; j < players.length; j++) {
//            for (int k = 0; k < players[player_me].length; k++) {
//                check = players[player_me][k];
//                String card = card_map.value(check);
//                System.out.printf("Player %d sorted hand, card:" + card + " \n",
//                        j + 1);
//            }
//        }

        /*
         * Close input and output streams
         */
    }

}
