import java.util.*;

final public class TerminalUI implements BlackjackGameUI {
    private static final int NUM_SEATS = 6;
    private static final Map<String, Action> actionMapping = new HashMap<>();

    static {
        actionMapping.put("H", Action.HIT);
        actionMapping.put("S", Action.STAND);
    }

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void showGameIntro() {
        System.out.println("Welcome to Blackjack!");
        System.out.println();
        System.out.println("  BLACKJACK RULES: ");
        System.out.println("	-Each player is dealt 2 cards. The dealer is dealt 2 cards with one face-up and one face-down.");
        System.out.println("	-Cards are equal to their value with face cards being 10 and an Ace being 1 or 11.");
        System.out.println("	-The players cards are added up for their total.");
        System.out.println("	-Players “Hit” to gain another card from the deck. Players “Stay” to keep their current card total.");
        System.out.println("	-Dealer “Hits” until they equal or exceed 17.");
        System.out.println("	-The goal is to have a higher card total than the dealer without going over 21.");
        System.out.println("	-If the player total equals the dealer total, it is a “Push” and the hand ends.");
        System.out.println("	-Players win their bet if they beat the dealer. Players win 1.5x their bet if they get “Blackjack” which is 21.");
        System.out.println();
        System.out.println();
    }

    @Override
    public GameSetup requestGameSetup() {
        int numPlayers;
        List<PlayerInfo> playerInfos = new ArrayList<>();

        while (true) {
            System.out.print("How many people are playing (1-6)? ");
            numPlayers = scanner.nextInt();
            if (numPlayers <= NUM_SEATS && numPlayers > 0) {
                break;
            }
            System.out.println("Invalid input, please try again.");
        }

        // Asks for player names and assigns them
        for (int i = 0; i < numPlayers; i++) {
            System.out.print("What is player " + (i + 1) + "'s name? ");
            String playerName = scanner.next();
            PlayerInfo playerInfo = new PlayerInfo(i, playerName);
            playerInfos.add(playerInfo);
        }
        return new GameSetup(NUM_SEATS, playerInfos);
    }

    @Override
    public Action requestPlayerAction(Player player) {
        // Ask player
        String input;

        while (true) {
            System.out.printf("Hey %s, what action do you want to take?\n", player.getIdentifiableName());
            System.out.println("H = Hit, S = Stand");
            input = scanner.next().toUpperCase();
            if (actionMapping.containsKey(input)) {
                break;
            }
            System.out.println("Invalid input, please try again.");
            requestPlayerAction(player);
        }

        return actionMapping.get(input);
    }

    @Override
    public void finishGame(Dealer dealer, List <Player> winners, List <Player> losers, List <Player> pushed) {
        System.out.println("---------------------------");
        System.out.println("||       Game Over       ||");
        System.out.println("---------------------------");

        System.out.println("Dealer: ");
        printPlayerResult(dealer);

        System.out.println();
        System.out.println("Winners: ");
        for  (Player player : winners) {
            printPlayerResult(player);
        }
        System.out.println();
        System.out.println("Pushed players: ");
        for (Player player : pushed) {
            printPlayerResult(player);
        }

        System.out.println();
        System.out.println("Losers: ");
        for (Player player : losers) {
            printPlayerResult(player);
        }
    }

    @Override
    public void showPlayerJoinGame(Player player) {
        System.out.printf("Player %s with ID=%s joined the game\n", player.name, player.id);
    }

    @Override
    public void showShufflingDeck() {
        System.out.println("Shuffling deck");
    }

    @Override
    public void showDealingVisibleCardToPlayer(Player player, Card card) {
        System.out.printf("Dealing %s to %s\n", card, player.getIdentifiableName());
    }

    @Override
    public void showDealingHiddenCardToPlayer(Player player) {
        System.out.printf("Dealing hidden card to %s\n", player.getIdentifiableName());
    }

    @Override
    public void showPlayerHand(Player player) {
        List<String> cardString = new ArrayList<>();
        for (Card card : player.hand.getVisibleCards()) {
            cardString.add(card.toString());
        }
        int count = player.hand.getHiddenCards().size();
        for (int i = 0; i < count; i++) {
            cardString.add("Hidden Card");
        }
        System.out.printf("Player %s, Hand=[%s]\n", player.getIdentifiableName(), cardString);
    }

    @Override
    public void showPlayerAction(Player player, Action action) {
        System.out.printf("Player %s, %s\n", player.getIdentifiableName(), action.name());
    }

    @Override
    public void startNextTurn() {
        System.out.println("++++++++++++++++++++++++++++");
        System.out.println("Start new turn");
    }

    @Override
    public void finishTurn() {
        System.out.println("----------------------------");
    }

    @Override
    public void showStartGame() {
        System.out.println("============================");
        System.out.println("|       Game started       |");
        System.out.println("============================");
    }

    @Override
    public void showPlayerActionStart() {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }

    @Override
    public void showPlayerActionEnd() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    @Override
    public void dealerRevealHiddenCards() {
        System.out.println("Dealer reveals hidden cards");
    }

    private void printPlayerResult(Player player) {
        if (player.isBusted()) {
            System.out.printf("Player %s, BUSTED\n", player.getIdentifiableName());
        } else if (player.hasBlackjack()) {
            System.out.printf("Player %s, BLACK JACK\n", player.getIdentifiableName());
        } else {
            System.out.printf("Player %s, score=%s\n", player.getIdentifiableName(), player.calculateBestScore());
        }
    }
}
