/**
 * This class represents a single card in the game.
 * 
 * @author Michael Velez
 * Created on May 29, 2018
 *
 */

public class Card {
	
	private String suit = null;
	private String rank = null;
	private int rankValue = 0;
/**
 * Constructor for intializing a card object.
 * @param rank of the card as a String
 * @param suit of the card as a String
 */
	public Card(String rank, String suit) {
		this.rank = rank;
		this.suit = suit;
		rankValue = 0;
	}
	
	public String getSuit() {
		return suit;
	}
	
	public String getRank() {
		return rank;
	}
/**
 * This function is used for translating the rank value of a card based on if it is a Ten, Jack, Queen, King or Ace.	
 * @return
 */
	public int getRankValue() {
		if(!rankIsNum(rank)) {
			switch (rank) {
	        case "T":  rankValue = 10;
	 			 break;	
	        case "J":  rankValue = 11;
   		 			 break;	
	        case "Q":  rankValue = 12;
	                 break;
	        case "K":  rankValue = 13;
	                 break;
	        case "A":  rankValue = 14;
	                 break;	 
				}			
			}
			else {
				rankValue = Integer.parseInt(rank);
			}
		return rankValue;
	}
/**
 * This helper function is used for translating the suit of a card for visualization/debugging purposes.	
 * @param suit to be translated as a single char String
 * @return the translated suit as a String
 */
	public String translateSuit(String suit) {
		String translatedSuit = "";
		switch (suit) {
        case "D":  translatedSuit = "Diamonds";
                 break;
        case "H":  translatedSuit = "Hearts";
                 break;
        case "C":  translatedSuit = "Clubs";
                 break;
        case "S":  translatedSuit = "Spades";
        		 break;
        default: translatedSuit = "Invalid Suit";
                 break;
		}
		return translatedSuit;
	}
/**
* This helper function is used for translating the rank of a card for visualization/debugging purposes.	
* @param rank to be translated as a single char String
* @return the translated rank as a String
* */	
	public String translateRank(String rank) {
		String translatedRank = "";
		switch (rank) {
        case "T":  translatedRank = "10";
 			 break;	
        case "Q":  translatedRank = "Queen";
                 break;
        case "K":  translatedRank = "King";
                 break;
        case "A":  translatedRank = "Ace";
                 break;
        case "J":  translatedRank = "Jack";
        		 break;		 
        default: translatedRank = "Invalid Rank";
                 break;
		}
		return translatedRank;
	}
/**
 * This function is used to determine if a rank is already numbered.	
 * @param rank the rank in string form to be determined
 * @return boolean answer
 */
	public boolean rankIsNum(String rank) {
		try {
		    Integer.parseInt(rank);
		} catch (NumberFormatException e) {
		    return false;
		}
		return true;
	}
/**
 * This helper function is used for printing the full card rank and suit. Mainly used for debugging/visualization.	
 */
	public void printName() {
		if(!rankIsNum(rank)) {
		System.out.println( translateRank(rank) + " of " + translateSuit(suit));
		}
		else {
			System.out.println( rank + " of " + translateSuit(suit));
		}
	}

}
