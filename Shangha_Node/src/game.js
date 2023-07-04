/**
 * @fileoverview This file contains the game logic for the Shanghai game.
 * 
 * @author Tyler Mante
 */

/**
 * Create_defintion_deck
 * Create_shuffled_shanghai_deck
 * Deal_cards
 * Check_suit
 * Card_map
 * Hand_6
 * Ruleset_map_definition
 * IsValidSet
 * isValidRun
 * 
 * Classes: Card, Deck, Hand, Player, GameState
 * Constants: SUITES, NUMBERS, 
 * 
*/
class Create_defintion_deck_deck{
    static SUITES = ['Spades', 'Clubs', 'Hearts', 'Diamonds'];
    static Values = ['A', '2', '3', '4', '5', '6', '7', '8', '9', '10', 'J' , 'Q', 'K'];


    constructor(){
        this.deck = [];
        let suites_filled = false;
        let j = 0;
        for (let i = 0; i < 4; i++){
            while(j < 13){
                if(j == 12 && ! suites_filled){
                    j = 0;
                    suites_filled = true;
                };
                let card = `${this.Values[j]} of ${this.SUITES[i]}`
                this.deck.push(card);
            }
        }
        }
    }



let imageElt = document.createElement('img');
      imageElt.src = `svg_playing_cards/fronts/-${this.suite}-${this.shading}-${this.shape}.svg`;
      imageElt.alt = `${this.number} ${this.color} ${this.shading} ${this.shape}`;
      imageElt.width = 125;
      imageElt.height = 175;