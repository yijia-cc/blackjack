import java.util.ArrayList;
import java.util.List;
import java.util.Random;

final public class Deck {
    private static final Random RANDOM = new Random();
    private final List<Card> allCards = new ArrayList<>();
    private int nextToDeal= 0;

    Deck(int numOfStandardDecks) {
        for (int i = 0; i < numOfStandardDecks; i++) {
            allCards.addAll(getStandardDeck());
        }
    }

    public void shuffle() {
        for (int i = 0; i < allCards.size(); i++) {
            int j = RANDOM.nextInt(allCards.size() - i) + i;
            Card card1 = allCards.get(i);
            Card card2 = allCards.get(j);
            allCards.set(i, card2);
            allCards.set(j, card1);
        }
    }

    public Card dealCard() {
        if (nextToDeal == allCards.size()) {
            nextToDeal = 0;
            shuffle();
        }
        return allCards.get(nextToDeal++);
    }

    private List<Card> getStandardDeck() {
        List<Card> cards = new ArrayList<>();
        for (FaceValue faceValue : FaceValue.values()) {
            for (Suit suit : Suit.values()) {
                cards.add(new Card(faceValue, suit));
            }
        }
        return cards;
    }
}
