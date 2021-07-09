import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final public class Hand {
    private static final int ACE_MAX_SCORE = 11;
    private static final int ACE_MIN_SCORE = 1;
    private static final int BUSTED_THRESHOLD = 21;
    private static final Map<FaceValue, Integer> scoreMapping = new HashMap<>();

    static {
        scoreMapping.put(FaceValue.Two, 2);
        scoreMapping.put(FaceValue.Three, 3);
        scoreMapping.put(FaceValue.Four, 4);
        scoreMapping.put(FaceValue.Five, 5);
        scoreMapping.put(FaceValue.Six, 6);
        scoreMapping.put(FaceValue.Seven, 7);
        scoreMapping.put(FaceValue.Eight, 8);
        scoreMapping.put(FaceValue.Nine, 9);
        scoreMapping.put(FaceValue.Ten, 10);
        scoreMapping.put(FaceValue.Jack, 10);
        scoreMapping.put(FaceValue.Queen, 10);
        scoreMapping.put(FaceValue.King, 10);
    }

    private final List <Card> hiddenCards = new ArrayList<>();
    private final List <Card> visibleCards = new ArrayList<>();

    public int calculateBestScore() {
        int score = sumWithoutAce();
        if (!hasAce()) {
            return score;
        }
        return addAce(score);
    }

    public boolean isBlackjack() {
        return calculateBestScore() == BUSTED_THRESHOLD;
    }

    public boolean isBusted() {
        return calculateBestScore() > BUSTED_THRESHOLD;
    }

    public void addVisibleCard (Card card) {
        visibleCards.add(card);
    }

    public void addHiddenCard (Card card) {
        hiddenCards.add(card);
    }

    public void revealHiddenCards() {
        for  (Card hiddenCard : hiddenCards) {

            visibleCards.add(hiddenCard);
        }
        hiddenCards.clear();
    }

    public List <Card> getVisibleCards() {
        return visibleCards;
    }

    public List <Card> getHiddenCards() {
        return hiddenCards;
    }

    private int addAce(int score) {
        if (score + ACE_MAX_SCORE <= BUSTED_THRESHOLD) {
            return score + ACE_MAX_SCORE;
        } else {
            return score + ACE_MIN_SCORE;
        }
    }

    private boolean hasAce() {
        return hasAce(visibleCards) && hasAce(hiddenCards);
    }

    private boolean hasAce(List <Card> cards) {
        for  (Card card : cards) {
            if (card.value() == FaceValue.Ace) {
                return true;
            }
        }
        return false;
    }

    private int sumWithoutAce() {
        return sumWithoutAce(visibleCards) + sumWithoutAce(hiddenCards);
    }

    private int sumWithoutAce(List <Card> cards) {
        int sum = 0;
        for  (Card card : cards) {
            if (card.value() == FaceValue.Ace) {
                continue;
            }
            sum += scoreMapping.get(card.value());
        }
        return sum;
    }
}
