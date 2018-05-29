import java.util.Comparator;

/**
 * This class is used to sort the cards.
 * 
 * @author Michael Velez
 * Created on May 29, 2018
 *
 */
public class Sortbycard implements Comparator<Card> {
    public int compare(Card a, Card b)
    {
        return a.getRankValue() - b.getRankValue();
    }
}