/**
 * The family friendly card game of Shanghai brought to you by.
 *
 * @author Tyler Mante
 *
 */
class Master_deck {
    /**
     * DECK_SIZE: A Shanghai Rummy deck uses 2 decks (2 * 52 = 104), including
     * two jokers (0, 2000 as an INT)
     *
     */
    static DECK_SIZE = 106;
    static NUM_SUITES = 4;
    static DECK_IDX = 0;
    static CARDS_PER_DEAL = 11;
    static MAX_PLAYERS = 4; // use on last hand when 4 buys are used!
    static MAX_BUYS = 3;
    static SUITES_PER_DECK = 26;

   /**
     * Creates a deck of 104 cards to be matched for suites
     *
     * @return suites_container- the array of sorted cards by suite
     */
    define_suit() {
        /* Suit array 
            * 0-25 = Spades 
            * 26-51 = Clubs
            * 52-77 = Hearts
            * 78-103 = Diamonds
            * 104 = Joker
            * 105 = Joker
            * */
        let suit = [];
        for (let i = 0; i < Master_deck.NUM_SUITES; i++) {
          for (let j = 0; j < Master_deck.SUITES_PER_DECK; j++) {
            let index = (i * Master_deck.SUITES_PER_DECK) + j;
            suit.push(index);
          }
        suit.push(104);
        suit.push(105);
        }
        for(let i = 0; i < suit.length; i++){
            if(suit[i] >= 0 && suit[i] <= 25){
                suit[i] = "Spades";
            } else if(suit[i] >= 26 && suit[i] <= 51){
                suit[i] = "Clubs";
            }
            else if(suit[i] >= 52 && suit[i] <= 77){
                suit[i] = "Hearts";
            }
            else if(suit[i] >= 78 && suit[i] <= 103){
                suit[i] = "Diamonds";
            }
            else if(suit[i] == 104){
                suit[i] = "Joker";
            }
            else if(suit[i] == 105){
                suit[i] = "Joker";
            }
        }
        return suit;
    }

    define_value(){
        this.card_map = new Map();
        let suit = this.define_suit();
        let element = 0;
        let card_key = 0;
        let suite_ct = 0;
        this.card_map.set(104, "Joker");
        this.card_map.set(105, "Joker");
        while (suite_ct < Master_deck.NUM_SUITES) {
            element = suit[suite_ct];
            this.card_map.set(card_key, ("A of " + element));
            card_key++;
            this.card_map.set(card_key, ("A of " + element));
            card_key++;
            this.card_map.set(card_key, "2 of " + element);
            card_key++;
            this.card_map.set(card_key, "3 of " + element);
            card_key++;
            this.card_map.set(card_key, "4 of " + element);
            card_key++;
            this.card_map.set(card_key, "5 of " + element);
            card_key++;
            this.card_map.set(card_key, "6 of " + element);
            card_key++;
            this.card_map.set(card_key, "7 of " + element);
            card_key++;
            this.card_map.set(card_key, "8 of " + element);
            card_key++;
            this.card_map.set(card_key, "9 of " + element);
            card_key++;
            this.card_map.set(card_key, "10 of " + element);
            card_key++;
            this.card_map.set(card_key, "J of " + element);
            card_key++;
            this.card_map.set(card_key, "2 of " + element);
            card_key++;
            this.card_map.set(card_key, "3 of " + element);
            card_key++;
            this.card_map.set(card_key, "4 of " + element);
            card_key++;
            this.card_map.set(card_key, "5 of " + element);
            card_key++;
            this.card_map.set(card_key, "6 of " + element);
            card_key++;
            this.card_map.set(card_key, "7 of " + element);
            card_key++;
            this.card_map.set(card_key, "8 of " + element);
            card_key++;
            this.card_map.set(card_key, "9 of " + element);
            card_key++;
            this.card_map.set(card_key, "10 of " + element);
            card_key++;
            this.card_map.set(card_key, "J of " + element);
            card_key++;
            this.card_map.set(card_key, "Q of " + element);
            card_key++;
            this.card_map.set(card_key, "Q of " + element);
            card_key++;
            this.card_map.set(card_key, "K of " + element);
            card_key++;
            this.card_map.set(card_key, "K of " + element);
            card_key++;
            suite_ct++;
            }
        }
        print_card_map(){
            for(const element of this.card_map){
                console.log(element);
            }
        }
    }
Master_deck = new Master_deck();
Master_deck.define_suit();
Master_deck.define_value();
Master_deck.print_card_map();
