final public class Card {
    private final FaceValue faceValue;
    private final Suit suit;

    Card(FaceValue faceValue, Suit suit) {
        this.faceValue = faceValue;
        this.suit = suit;
    }

    public FaceValue value() {
        return faceValue;
    }

    public Suit suit() {
        return suit;
    }

    public String toString() {
        return String.format("{Card suit=%s, faceValue=%s}", suit.name(), faceValue.name());
    }
}
