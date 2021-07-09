final public class Dealer extends Player {
    private static final int DEALER_THRESH_HOLD = 17;
    boolean hasRevealed;

    Dealer(BlackjackGame blackjackGame) {
        super(blackjackGame, -1, "Dealer");
    }

    @Override
    public boolean canTakeAction() {
        if (!hasRevealed) {
            return true;
        }
        return !isBusted() && !hasBlackjack() && hand.calculateBestScore() < DEALER_THRESH_HOLD;
    }

    @Override
    public void takeAction() {
        if (!hasRevealed) {
            blackjackGame.dealerRevealHiddenCards();
            hand.revealHiddenCards();
            hasRevealed = true;
            return;
        }
        hit();
    }

    public void addHiddenCard (Card card) {
        hand.addHiddenCard(card);
    }
}
