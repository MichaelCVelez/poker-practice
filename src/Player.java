/**
 * This class represents a player in the game.
 * 
 * @author Michael Velez
 * Created on May 29, 2018
 *
 */

public class Player {
	
	/*At this point player only has a name and a hand 
	but could gain new values if the game was further extended*/
	public String playerName = "";
	public Hand playerHand;
	
	Player(String playerName, Hand playerHand){
		this.playerName = playerName;
		this.playerHand = playerHand;
	}

}
