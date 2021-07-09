import java.util.List;

public abstract class Player {
    protected final String name;
    protected final int id;
    // TODO: support multiple hands
    protected Hand hand = new Hand();
    protected BlackjackGame blackjackGame;
    protected boolean hasDoneStand;

    Player(BlackjackGame blackjackGame, int id, String name) {
        this.blackjackGame = blackjackGame;
        this.id = id;
        this.name = name;
    }

    public abstract boolean canTakeAction();

    public abstract void takeAction();

    public void addVisibleCards (Card card) {
        hand.addVisibleCard(card);
    }

    public int calculateBestScore() {
        return hand.calculateBestScore();
    }

    public String getIdentifiableName() {
        return String.format("%s(ID=%s)", name, id);
    }

    protected boolean isBusted() {
        return hand.isBusted();
    }

    protected boolean hasBlackjack() {
        return hand.isBlackjack();
    }

    protected void hit() {
        blackjackGame.showPlayerAction(this, Action.HIT);
        hand.addVisibleCard(blackjackGame.dealCard());
    }

    protected void stand() {
        blackjackGame.showPlayerAction(this, Action.STAND);
        hasDoneStand = true;
    }
}
