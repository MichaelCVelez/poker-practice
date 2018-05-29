import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Set;
/**
 * This class represents the hand used by a player for a single round of the game.
 * 
 * @author Michael Velez
 * Created on May 29, 2018
 *
 */
public class Hand {
	
    ArrayList<Card> cards = new ArrayList<Card>();
    //hashtable used for keeping track of card frequency
	Hashtable<Integer, Integer> cardFreq = new Hashtable<Integer, Integer>();
	Set<Integer> keys = cardFreq.keySet();
	//final values
	final int ROYAL_FLUSH_RANK = 23;
	final int STRAIGHT_FLUSH_RANK = 22;
	final int FOUR_OF_A_KIND_RANK = 21;
	final int FULL_HOUSE_RANK = 20;
	final int FLUSH_RANK = 19;
	final int STRAIGHT_RANK = 18;
	final int THREE_OF_A_KIND_RANK = 17;
	final int TWO_PAIRS_RANK = 16;
	final int ONE_PAIR_RANK = 15;
	final int ACE_RANK = 15;
	//handRank is compared between players to determine winner
	private int handRank = 0;
	//value below is unfinished in an attempt at fixing test case 5 which shows incorrect winner
	//private int tieBreakerCard = 0;

	public Hand(ArrayList<Card> cards){
		this.cards = cards;
	}
/**
 * This function sorts the cards to make it easier to determine hands	
 */
	public void sortCards() {
        Collections.sort(cards, new Sortbycard());
	}
	
/**
 * Helper function used while debugging for visualization	
 */
	public void printCards() {
		for(Card c : cards) {
			System.out.print(c.getRank()+c.getSuit() +" ");
		}
		System.out.println();
	}
/**
 * This function is used to get the value of the highest ranked card in the hand.	
 * @return the highest value found as an integer
 */
	public int getHighestCardValue() {
		int highestValue = 0;
		for(Card c : cards) {
			if(c.getRankValue()>highestValue) {
				highestValue = c.getRankValue();
			}
		}
		return highestValue;
	}
/**
 * This function is used to determine if the hand is a Royal Flush	
 * @return answer in boolean form
 */
	public boolean isRoyalFlush() {
		boolean answer = false;
		String suit = cards.get(0).getSuit();
		int highestValue = getHighestCardValue();
		if(highestValue!=ACE_RANK) {
			return answer;	//Ace is not the highest card so return false
		}
		for (int i = 0; i < cards.size(); i++) {
			//check for same suit
			if(cards.get(i).getSuit()!=suit) {
				return false;
			}
			//check for consecutive values
			if(!((cards.get(i).getRankValue())==(cards.get(i+1).getRankValue()+1))) {
				return false;
			}
		}
		answer = true;
		return answer;
	}
/**
 * This function is used to determine if the hand is a Straight Flush	
 * @return answer in boolean form
 */			
	public boolean isStraightFlush() {
		boolean answer = false;
		if(isStraight() && isFlush()) {
			answer = true;
		}
		return answer;
	}
/**
 * This function is used to intialize the hash table used for determining card frequency.	
 */
	public void initializeHashTable() {		
		for (int i = 0; i < cards.size(); i++) {
			int currentCardRank = cards.get(i).getRankValue();
			if(cardFreq.containsKey(currentCardRank)){
				int count = cardFreq.get(currentCardRank);
				cardFreq.put(currentCardRank, ++count); //duplicate found so increase count in hashtable
			}
			else {
				cardFreq.put(currentCardRank, 1);	//found for first time so add to hashtable with count of 1
			}
		}
	}
	
//Unfinished function used in getting tie breaker.	
/*	public int getNewTieBreakerCard(int newRank) {
		if(tieBreakerCard == 0) {
			tieBreakerCard = newRank;
		}
		else {
			tieBreakerCard = getHighestCardValue();
		}	
		return tieBreakerCard;
	}*/
/**
 * This function is used to find the number of matches of any card found in the hand.	
 * @param isPair boolean value is used to differentiate if function should find just a single pair or more (3 or 4 of a kind).
 * @return the number of matches found
 */
	public int getNumMatches(boolean isPair) {
		int numMatches = 0;
		if(isPair) {
			for(Integer key: keys) {
	        	if(cardFreq.get(key)==2) {
	        		numMatches ++;	//found a pair
	        		//unfinished variable used for determining tie breaker
	        		//tieBreakerCard = key;
	        	}
			}
		}
		else {
			for(Integer key: keys) {
	        	if(cardFreq.get(key)>2) {
	        		numMatches = cardFreq.get(key);	//found more than a pair (3 or 4 of a kind)
	        		//unfinished variable used for determining tie breaker
	        		//tieBreakerCard = key;
	        	}
			}
        }
        return numMatches;
	}
/**
 * This function is used to determine if the hand is a Full House	
 * @return answer in boolean form
 */
	public boolean isFullHouse() {
		boolean answer = false;
		//if there is one pair and 3 of a kind in hand then it is a full house
		if(getNumMatches(true)==1 && getNumMatches(false)==3) {
			answer = true;
		}
		return answer;
	}
/**
 * This function is used to determine if the hand is a Flush	
 * @return answer in boolean form
 */
	public boolean isFlush() {
		boolean answer = false;
		String suit = cards.get(0).getSuit();
		for (int i = 0; i < cards.size(); i++) {
			//check for same suit
			if(!cards.get(i).getSuit().equals(suit)) {
				return false;
			}
		}
		answer = true;
		return answer;
	}
/**
 * This function is used to determine if the hand is a Straight	
 * @return answer in boolean form
 */
	public boolean isStraight() {
		boolean answer = false;
		for (int i = 0; i < cards.size(); i++) {
			//check for conesecutive rank values
			if(!((cards.get(i).getRankValue())==(cards.get(i+1).getRankValue()-1))) {
				return false;
			}
		}
		answer = true;
		return answer;
	}
/**
 * This function is called if there is a tie between players and a winner needs to be determined.
 * The function will return the a new ranking for the players hand.	
 * @return the new hand rank as an integer value.
 */
	public int getTieBreaker() {
		int newHandRank = 0;
		if(handRank == ROYAL_FLUSH_RANK || handRank == STRAIGHT_FLUSH_RANK
				|| handRank == FLUSH_RANK || handRank == STRAIGHT_RANK ) {
			newHandRank = getHighestCardValue();
		}
		else {
			//unfinished logic in this else statement. Last test case has incorrect winner. For some reason it is not
			//grabbing the correct highest rank.
			int currentHighest = 0;	
			for(Integer key: keys) {
	        	if(cardFreq.get(key)>currentHighest) {
	        		currentHighest = key;
	        	}
			}
			newHandRank = currentHighest;
			//unfinished variable logic below
			//newHandRank = tieBreakerCard;
		}
		return newHandRank;
	}
/**
 * This function contains the logic for determining the highest possible rank of the hand.	
 * @return the rank of the hand as an integer
 */
	public int rankHand() {
		//setup hashtable and sort cards
		initializeHashTable();
		sortCards();
		boolean rankFound = false;
		while(!rankFound) {
			if(isRoyalFlush()) {
				handRank = ROYAL_FLUSH_RANK;
				rankFound = true;
			}
			else if(isStraightFlush()) {
				handRank = STRAIGHT_FLUSH_RANK;
				rankFound = true;
			}
			else if(getNumMatches(false)==4) {
				handRank = FOUR_OF_A_KIND_RANK;
				rankFound = true;
			}
			else if(isFullHouse()) {
				handRank = FULL_HOUSE_RANK;
				rankFound = true;
			}
			else if(isFlush()) {
				handRank = FLUSH_RANK;
				rankFound = true;
			}
			else if(isStraight()) {
				handRank = STRAIGHT_RANK;
				rankFound = true;
			}
			else if(getNumMatches(false)==3) {
				handRank = THREE_OF_A_KIND_RANK;
				rankFound = true;
			}
			else if(getNumMatches(true)==2) {
				handRank = TWO_PAIRS_RANK;
				rankFound = true;
			}
			else if(getNumMatches(true)==1) {
				handRank = ONE_PAIR_RANK;
				rankFound = true;
			}
			else{
				handRank = getHighestCardValue();
				rankFound = true;
			}
		}	
		return handRank;
	}
	
}
