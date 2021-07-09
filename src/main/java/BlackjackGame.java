import java.util.ArrayList;
import java.util.List;

final public class BlackjackGame {
    private static final int DEFAULT_STANDARD_DECKS = 4;

    private final Deck deck = new Deck(DEFAULT_STANDARD_DECKS);;
    private final Dealer dealer;
    private final List<Player> players = new ArrayList<>();
    private final BlackjackGameUI blackjackGameUI;

    public BlackjackGame(BlackjackGameUI blackjackGameUI) {
        this.blackjackGameUI = blackjackGameUI;
        this.dealer = new Dealer(this);
    }

    public void startGame() {
        initializePlayers();
        blackjackGameUI.showStartGame();
        blackjackGameUI.showShufflingDeck();
        deck.shuffle();
        dealInitialCards();
    }

    public boolean dealerCanTakeAction() {
        return dealer.canTakeAction();
    }

    public void dealerTakesAction() {
        dealer.takeAction();
    }

    public boolean playersCanTakeAction() {
        for (Player player : players) {
            if (player.canTakeAction()) {
                return true;
            }
        }
        return false;
    }

    public void playersTakeAction() {
        blackjackGameUI.startNextTurn();
        for (Player player : players) {
            if (!player.canTakeAction()) {
                continue;
            }
            blackjackGameUI.showPlayerActionStart();
            player.takeAction();
            blackjackGameUI.showPlayerHand(player);
            blackjackGameUI.showPlayerActionEnd();
        }
        blackjackGameUI.finishTurn();
    }

    public Card dealCard() {
        return deck.dealCard();
    }

    public Action requestPlayerAction(Player player) {
        return blackjackGameUI.requestPlayerAction(player);
    }

    public void finishGame() {
        blackjackGameUI.showPlayerHand(dealer);

        List<Player> winners = new ArrayList<>();
        List<Player> losers = new ArrayList<>();
        List<Player> pushed = new ArrayList<>();

        int dealerScore = dealer.calculateBestScore();

        for (Player player : players) {
            if (player.isBusted()) {
                losers.add(player);
                continue;
            }
            if (player.hasBlackjack()) {
                if (dealer.hasBlackjack()) {
                    pushed.add(player);
                } else {
                    winners.add(player);
                }
                continue;
            }
            if (dealer.isBusted()) {
                winners.add(player);
                continue;
            }

            int playerScore = player.calculateBestScore();
            if (playerScore > dealerScore) {
                winners.add(player);
            } else if (playerScore < dealerScore) {
                losers.add(player);
            } else {
                pushed.add(player);
            }
        }
        blackjackGameUI.finishGame(dealer, winners, losers, pushed);
    }

    public void showGameIntro() {
        blackjackGameUI.showGameIntro();
    }

    public void showPlayerAction(Player player, Action action) {
        blackjackGameUI.showPlayerAction(player, action);
    }

    public void dealerRevealHiddenCards() {
        blackjackGameUI.dealerRevealHiddenCards();
    }

    private void initializePlayers() {
        GameSetup gameSetup = blackjackGameUI.requestGameSetup();
        List<PlayerInfo> playerInfos = gameSetup.getPlayerInfos();
        for (PlayerInfo playerInfo : playerInfos) {
            Player player = new HumanPlayer(this, playerInfo);
            players.add(player);
            blackjackGameUI.showPlayerJoinGame(player);
        }
        int numBots = gameSetup.getNumSeats() - playerInfos.size();
        for (int i = 0; i < numBots; i++) {
            Player player = new BotPlayer(this, i + playerInfos.size());
            players.add(player);
            blackjackGameUI.showPlayerJoinGame(player);
        }
    }

    private void dealInitialCards() {
        for (Player player : players) {
            Card card = deck.dealCard();
            blackjackGameUI.showDealingVisibleCardToPlayer(player, card);
            player.addVisibleCards(card);
        }

        Card card = deck.dealCard();
        blackjackGameUI.showDealingVisibleCardToPlayer(dealer, card);
        dealer.addVisibleCards(card);

        for (Player player : players) {
            card = deck.dealCard();
            blackjackGameUI.showDealingVisibleCardToPlayer(player, card);
            player.addVisibleCards(card);
        }
        blackjackGameUI.showDealingHiddenCardToPlayer(dealer);
        dealer.addHiddenCard(deck.dealCard());

        for (Player player : players) {
            blackjackGameUI.showPlayerHand(player);
        }
        blackjackGameUI.showPlayerHand(dealer);
    }
}
