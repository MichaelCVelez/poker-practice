import java.util.*;
/**
 * This project was created as fundamental Java practice in coding a game of poker. 
 * The full problem details can be found at https://www.hackerrank.com/contests/projecteuler/challenges/euler054/problem
 * 
 * @author Michael Velez
 * Created on May 29, 2018
 *
 */
public class Solution {
	
	private static int numTestCases = 0;

    public static void main(String[] args) {
    	
    	String input = "5\r\n" + 
    			"5H 5C 6S 7S KD 2C 3S 8S 8D TD\r\n" + 
    			"5D 8C 9S JS AC 2C 5C 7D 8S QH\r\n" + 
    			"2D 9C AS AH AC 3D 6D 7D TD QD\r\n" + 
    			"4D 6S 9H QH QC 3D 6D 7H QD QS\r\n" + 
    			"2H 2D 4C 4D 4S 3C 3D 3S 9S 9D";    
    	runGame(input);	
    }
/**
 * This function takes a 2 letter string, splits it and creates a card object with appropriate rank and suit. 
 * @param card - the card in string form. 
 * @return the newly created card object.
 */
    public static Card createCard(String card) {
		String[] cardArray = card.split("");
		Card createdCard = new Card(cardArray[0], cardArray[1]);
		return createdCard;
    }
/**
 * This function contains the logic for running a single round of the poker game.    
 * @param p1 - the first Player
 * @param p2 - the second Player
 */
    public static void runRound(Player p1, Player p2) {
    	int p1Rank = p1.playerHand.rankHand();
    	int p2Rank = p2.playerHand.rankHand();
    	boolean winnerFound = false;
    	
    	while(!winnerFound) {{
    		
    	}
    		if(p1Rank > p2Rank) {
    			System.out.println(p1.playerName);
    			winnerFound = true;
    		}
    		else if (p2Rank > p1Rank) {
    			System.out.println(p2.playerName);
    			winnerFound = true;
    		}
    		else {
    			p1Rank = p1.playerHand.getTieBreaker();
    			p2Rank = p2.playerHand.getTieBreaker();
    		}
    	}
    	
    }
/**
 * This function contains the logic for the full game of poker.     
 * @param input - the string input used to set up the game 
 */
    public static void runGame(String input) {
    	Scanner s = new Scanner(input);  
    	//get number of test cases from first line of input
    	String firstLine = s.nextLine();
    	numTestCases = Integer.parseInt(firstLine);	
    	
    	while(numTestCases>0) {
    	//get player hands and cards from next line of input
    	String currentHands = s.nextLine();
    	String[] currentCards = currentHands.split(" ");

        ArrayList<Card> p1cards = new ArrayList<Card>();
        ArrayList<Card> p2cards = new ArrayList<Card>();

        //divide cards to each player
    	for(int i = 0; i < 10; i ++) {
    		if(i<5) {
        		Card c = createCard(currentCards[i]);
        		p1cards.add(c);
    		}
    		else {
        		Card c = createCard(currentCards[i]);
        		p2cards.add(c);	
    		}
    	}
    	
    	//create hands and the players
    	Hand p1Hand = new Hand(p1cards);
    	Hand p2Hand = new Hand(p2cards);
    	
    	Player Player1 = new Player("Player1", p1Hand);
    	Player Player2 = new Player("Player2", p2Hand);	
    	
    	runRound(Player1, Player2);
    	numTestCases--;
    	}
    	
    	//close scanner
        s.close();

    }
}