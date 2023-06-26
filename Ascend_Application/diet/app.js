/* Start page logic --------------------------------------------------------- */

/* Show the start page. */
document.querySelector('.start-page').classList.remove('hidden');

/* Hide start page & show game page when the "Play" button is clicked. */
document.querySelector('.start-page .play-button').addEventListener('click', () => {
  document.querySelector('.start-page').classList.add('hidden');
  document.querySelector('.game-page').classList.remove('hidden');
}, false);

/* Main game logic ---------------------------------------------------------- */

/*
 * Maintain the following invariant initially and after all user interactions:
 * 1. For each entry card in table, card.elt is a child of tableElt
 * 2. sets = the Set of all SETs in table
 * 3. score = the inner text of scoreElt
 * 4. table.length = the inner text of deckSizeElt
 * 5. checkButtonElt is enabled iff selectedCards.length = 3
 * 6. hintButtonElt is enabled iff hintedCards.length = 0
 * 7. endGameButtonElt is enabled iff deck.length = 0 or user selects "End Game"
 * 8. For each entry card in selectedCards, card.elt has class 'selected'
 * 9. For each entry card in hintedCards, card.elt has class 'hint'
 */

/**
 * Represents a single SET card.
 */
class Card {
  static NUMBERS = [1, 2, 3];
  static SHAPES = ["oval", "diamond", "squiggle"];
  static SHADINGS = ["solid", "striped", "open"];
  static COLORS = ["red", "green", "blue"];

  number;
  shape;
  shading;
  color;
  elt;

  /**
   * Create a new card with the specified number/shape/shading/color of shapes.
   * 
   * Arguments number must be in NUMBERS, shape must be in SHAPES, shading
   * must be in SHADINGS, and color must be in COLORS. 
   * 
   * @param {number} number number of shapes
   * @param {string} shape type of shapes
   * @param {string} shading shading of shapes
   * @param {string} color color of shapes
   */
  constructor(number, shape, shading, color) {
    this.number = number;
    this.shape = shape;
    this.shading = shading;
    this.color = color;
  }

  /**
   * Add "selected" styling.
   */
  styleSelected() {
    this.elt.classList.add('selected');
  }

  /**
   * Remove "selected" styling.
   */
  unstyleSelected() {
    this.elt.classList.remove('selected');
  }

  /**
   * Add "hint" styling.
   */
  styleHinted() {
    this.elt.classList.add('hint');
  }

  /**
   * Remove "hint" styling.
   */
  unstyleHinted() {
    this.elt.classList.remove('hint');
  }

  /**
   * Click event handler
   */
  handleClick() {
    if (selectedCards.indexOf(this) != -1) {
      if (selectedCards.length == 3) {
        checkButtonElt.disabled = true;
      }
      this.unstyleSelected();
      selectedCards.splice(selectedCards.indexOf(this), 1);
    } else if (selectedCards.length < 3) {
      selectedCards.push(this);
      this.styleSelected();
      if (selectedCards.length == 3) {
        checkButtonElt.disabled = false;
      }
    }
  }

  /**
   * Get the HTML element associated with this card.
   */
  getElement() {
    if (!this.elt) {
      this.elt = document.createElement('button');
      this.elt.classList.add('game-card');
      this.elt.addEventListener('click', this.handleClick.bind(this), false);
      let imageElt = document.createElement('img');
      imageElt.src = `assets/${this.number}-${this.color}-${this.shading}-${this.shape}.svg`;
      imageElt.alt = `${this.number} ${this.color} ${this.shading} ${this.shape}`;
      imageElt.width = 125;
      imageElt.height = 175;
      this.elt.appendChild(imageElt);
    }
    return this.elt;
  }
}

/**
 * Represent a deck of SET cards.
 */
class Deck {
  static NUMBERS = [1, 2, 3];
  static SHAPES = ['oval', 'diamond', 'squiggle'];
  static SHADINGS = ['solid', 'striped', 'open'];
  static COLORS = ['red', 'green', 'blue'];

  rep;

  /**
   * Creates a new deck containing all cards in a SET deck. Each card is an
   * object with number, shape, shading, and color attributes set to values from
   * NUMBERS, SHAPES, SHADINGS, and COLORS respectively.
   */
  constructor() {
    this.rep = [];
    for (let number of Deck.NUMBERS) {
      for (let shape of Deck.SHAPES) {
        for (let shading of Deck.SHADINGS) {
          for (let color of Deck.COLORS) {
            this.rep.push(new Card(number, shape, shading, color));
          }
        }
      }
    }
  }

  /**
   * Shuffles the deck.
   * 
   * Uses the Fisher-Yates algorithm:
   * {@link https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle}
   */
  shuffle() {
    for (let i = this.rep.length - 1; i > 0; i--) {
      let j = Math.floor(Math.random() * (i + 1)); // ensures 0 <= j <= i
      swap(this.rep, i, j);
    }
  }

  /**
   * Draws one card from the deck.
   * 
   * @return {Object} a card
   */
  draw() {
    return this.rep.pop();
  }

  /**
   * Gets the size of the deck (number of cards remaining in the deck).
   * 
   * @returns {number} the size of the deck.
   */
  size() {
    return this.rep.length;
  }
}

/**
 * The set of cards on the table.
 */
const table = [];

/**
 * The deck.
 */
const deck = new Deck();

/**
 * A Set of SETs in table;
 */
let sets;

/**
 * Current score.
 */
let score = 0;

/**
 * Number of SETs identified.
 */
let numIdentSets = 0;

/**
 * Number of non-SETs identified.
 */
let numIdentNonSets = 0;

/**
 * Cards selected by the user.
 */
const selectedCards = [];

/**
 * Cards highlighted as a hint.
 */
const hintedCards = [];

/**
 * The DOM node of the table.
 */
const tableElt = document.querySelector('.game-table');

/**
 * The DOM node of the check selection button.
 */
const checkButtonElt = document.querySelector('.game-check-button');

/**
 * The DOM node of the hint button.
 */
const hintButtonElt = document.querySelector('.game-hint-button');
/**
 *  The DOM node of the end game button.
 */
const endButtonElt = document.querySelector('.game-end-button');

/**
 * The DOM node of the score span.
 */
const scoreElt = document.querySelector('.game-score');

/**
 * The DOM node of the deck size span.
 */
const deckSizeElt = document.querySelector('.game-deck-size');

/**
 * The DOM node of the visual deck element.
 */
const deckElt = document.querySelector('.game-deck');

/**
 * Swaps the elements at indices i and j of array.
 * 
 * @param {Array} array the array
 * @param {number} i index of first element
 * @param {number} j index of second element
 */
function swap(array, i, j) {
  let temp = array[i];
  array[i] = array[j];
  array[j] = temp;
}

/**
 * Check if three card property values satisfy the SET property; that is, these
 * property values are all the same or all different.
 * 
 * @param {*} x value of first card's property
 * @param {*} y value of second card's property
 * @param {*} z value of third card's property
 */
function satisfiesSetProperty(x, y, z) {
  return (x == y && y == z) || (x != y && y != z && z != x);
}

/**
 * Checks if three cards comprise a SET.
 * 
 * @param {Card} card1 first card
 * @param {Card} card2 second card
 * @param {Card} card3 third card
 * @returns {boolean} if the card arguments comprise a SET
 */
function isSet(card1, card2, card3) {
  return satisfiesSetProperty(card1.number, card2.number, card3.number) &&
    satisfiesSetProperty(card1.shape, card2.shape, card3.shape) &&
    satisfiesSetProperty(card1.shading, card2.shading, card3.shading) &&
    satisfiesSetProperty(card1.color, card2.color, card3.color)
}

/**
 * Get a set of all SETs in an array of cards.
 * The return value is a Set of Sets, where the cards in each element comprise a
 * SET.
 * 
 * @param {Array} array the cards to check for SETs
 * @returns {Set} set of SETs in array
 */
function getSets(array) {
  let result = new Set();
  for (let i = 2; i < array.length; i++) {
    let card1 = array[i];
    for (let j = 1; j < i; j++) {
      let card2 = array[j];
      for (let k = 0; k < j; k++) {
        let card3 = array[k];
        if (isSet(card1, card2, card3)) {
          result.add(new Set([card1, card2, card3]));
        }
      }
    }
  }
  return result;
}

/**
 * Add n cards to the table from the deck.
 * 
 * @param {number} n number of cards to deal
 */
function deal(n) {
  for (let i = 0; i < n; i++) {
    let card = deck.draw();
    table.push(card);
    tableElt.appendChild(card.getElement());
  }
}
/**
 * Handler for click events on the check set button.
 */
function checkHandler() {
  if (isSet(...selectedCards)) {
    numIdentSets++;
    score++;
    scoreElt.innerText = score;

    /* Clear hint */
    for (let card of hintedCards) {
      card.unstyleHinted();
    }
    hintedCards.length = 0;
    hintButtonElt.disabled = false;

    /* Clear selected cards */
    for (let card of selectedCards) {
      table.splice(table.indexOf(card), 1);
      tableElt.removeChild(card.getElement());
    }
    selectedCards.length = 0;
    checkButtonElt.disabled = true;
    sets = getSets(table);

    /* Deal more cards, if necessary and possible */
    while (deck.size() > 0 && (table.length < 12 || sets.size == 0)) {
      deal(3);
      sets = getSets(table);
    }

    /* When no more SETs are left, go to the end page */
    if (deck.size() == 0 && sets.size == 0)  {
      showEndPage();
    } else {
      deckElt.classList.add('glow');
      // Wait for the animation to finish
      setTimeout(() => {
      // Remove the glow class from the deck element
      deckElt.classList.remove('glow');
      }, 100);
    }

    deckSizeElt.innerText = deck.size();
  } else {
    numIdentNonSets++;
    score--;
    scoreElt.innerText = score;
  }
}

/**
 * Handler for click events on the hint button.
 */
function hintHandler() {
  hintedCards.push(...Array.from(Array.from(sets.values())[0]).slice(0, 2));
  for (let card of hintedCards) {
    card.styleHinted();
  }
  hintButtonElt.disabled = true;
}

/*
 * Register event listeners.
 */
checkButtonElt.addEventListener('click', checkHandler, false);
hintButtonElt.addEventListener('click', hintHandler, false);
endButtonElt.addEventListener('click', showEndPage, false);

/*
 * Initial deal.
 */

deck.shuffle();

do {
  deal(3);
  sets = getSets(table);
} while (table.length < 12 || sets.size == 0);

deckSizeElt.innerText = deck.size();

/* End page logic ----------------------------------------------------------- */

/**
 * Shows the end page.
 */
function showEndPage() {
  document.querySelector('.start-page').classList.add('hidden');
  document.querySelector('.game-page').classList.add('hidden');
  document.querySelector('.end-page').classList.remove('hidden');
  endScoreElt.innerText = score;
  endSetsElt.innerText = numIdentSets;
  endNonSetsElt.innerText = numIdentNonSets;
}

/**
 * The DOM node of the end page score span.
 */
const endScoreElt = document.querySelector('.end-score');

/**
 * The DOM node of the end page SET count span.
 */
const endSetsElt = document.querySelector('.end-num-sets');

/**
 * The DOM node of the end page non-SET count span.
 */
const endNonSetsElt = document.querySelector('.end-num-non-sets');