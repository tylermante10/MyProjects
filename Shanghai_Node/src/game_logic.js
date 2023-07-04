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
        this.suit = [];
        for (let i = 0; i < Master_deck.NUM_SUITES; i++) {
          for (let j = 0; j < Master_deck.SUITES_PER_DECK; j++) {
            let index = (i * Master_deck.SUITES_PER_DECK) + j;
            this.suit.push(index);
          }
        this.suit.push(104);
        this.suit.push(105);
        }
        for(let i = 0; i < this.suit.length; i++){
            if(this.suit[i] >= 0 && this.suit[i] <= 25){
                this.suit[i] = "Spades";
            } else if(this.suit[i] >= 26 && this.suit[i] <= 51){
                this.suit[i] = "Clubs";
            }
            else if(this.suit[i] >= 52 && this.suit[i] <= 77){
                this.suit[i] = "Hearts";
            }
            else if(this.suit[i] >= 78 && this.suit[i] <= 103){
                this.suit[i] = "Diamonds";
            }
            else if(this.suit[i] == 104){
                this.suit[i] = "Joker";
            }
            else if(this.suit[i] == 105){
                this.suit[i] = "Joker";
            }
        }
    }

    define_value(){
        this.card_map = new Map();
        let card_key = 0;
        let suite_ct = 0;
        this.card_map.set(104, "Joker");
        this.card_map.set(105, "Joker");
        while (suite_ct < Master_deck.NUM_SUITES) {
            let suit = this.suit[suite_ct];
            this.card_map.set(card_key, ("A of " + suit));
            card_key++;
            this.card_map.set(card_key, ("A of " + suit));
            card_key++;
            this.card_map.set(card_key, "2 of " + suit);
            card_key++;
            this.card_map.set(card_key, "3 of " + suit);
            card_key++;
            this.card_map.set(card_key, "4 of " + suit);
            card_key++;
            this.card_map.set(card_key, "5 of " + suit);
            card_key++;
            this.card_map.set(card_key, "6 of " + suit);
            card_key++;
            this.card_map.set(card_key, "7 of " + suit);
            card_key++;
            this.card_map.set(card_key, "8 of " + suit);
            card_key++;
            this.card_map.set(card_key, "9 of " + suit);
            card_key++;
            this.card_map.set(card_key, "10 of " + suit);
            card_key++;
            this.card_map.set(card_key, "J of " + suit);
            card_key++;
            this.card_map.set(card_key, "2 of " + suit);
            card_key++;
            this.card_map.set(card_key, "3 of " + suit);
            card_key++;
            this.card_map.set(card_key, "4 of " + suit);
            card_key++;
            this.card_map.set(card_key, "5 of " + suit);
            card_key++;
            this.card_map.set(card_key, "6 of " + suit);
            card_key++;
            this.card_map.set(card_key, "7 of " + suit);
            card_key++;
            this.card_map.set(card_key, "8 of " + suit);
            card_key++;
            this.card_map.set(card_key, "9 of " + suit);
            card_key++;
            this.card_map.set(card_key, "10 of " + suit);
            card_key++;
            this.card_map.set(card_key, "J of " + suit);
            card_key++;
            this.card_map.set(card_key, "Q of " + suit);
            card_key++;
            this.card_map.set(card_key, "Q of " + suit);
            card_key++;
            this.card_map.set(card_key, "K of " + suit);
            card_key++;
            this.card_map.set(card_key, "K of " + suit);
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
