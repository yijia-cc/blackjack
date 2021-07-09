import java.util.*;

final public class WebUI implements BlackjackGameUI {
    // Use message queue to implement asynchronous, event driven Web UI through Web Socket.

    @Override
    public void showGameIntro() {
        throw new UnsupportedOperationException();
    }

    @Override
    public GameSetup requestGameSetup() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Action requestPlayerAction(Player player) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void finishGame(Dealer dealer, List<Player> winners, List<Player> losers, List<Player> pushed) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void showPlayerJoinGame(Player player) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void showShufflingDeck() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void showDealingVisibleCardToPlayer(Player player, Card card) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void showDealingHiddenCardToPlayer(Player player) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void showPlayerHand(Player player) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void showPlayerAction(Player player, Action hit) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void startNextTurn() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void finishTurn() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void showStartGame() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void showPlayerActionStart() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void showPlayerActionEnd() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void dealerRevealHiddenCards() {
        throw new UnsupportedOperationException();
    }
}
