import java.util.List;

public interface BlackjackGameUI {
    // Designed to support Web UI in the future

    void showGameIntro();

    GameSetup requestGameSetup();

    Action requestPlayerAction(Player player);

    void finishGame(Dealer dealer, List <Player> winners, List <Player> losers, List <Player> pushed);

    void showPlayerJoinGame(Player player);

    void showShufflingDeck();

    void showDealingVisibleCardToPlayer(Player player, Card card);

    void showDealingHiddenCardToPlayer(Player player);

    void showPlayerHand(Player player);

    void showPlayerAction(Player player, Action hit);

    void startNextTurn();

    void finishTurn();

    void showStartGame();

    void showPlayerActionStart();

    void showPlayerActionEnd();

    void dealerRevealHiddenCards();
}
