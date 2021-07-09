final public class BlackjackAutomation {
    private final BlackjackGame blackjackGame;

    public BlackjackAutomation(BlackjackGameUI blackjackGameUI) {
        blackjackGame = new BlackjackGame(blackjackGameUI);
    }

    public static void main(String[] args) {
        BlackjackGameUI blackjackGameUI = new TerminalUI();
        BlackjackAutomation blackjackAutomation = new BlackjackAutomation(blackjackGameUI);
        blackjackAutomation.startGame();
    }

    public void startGame() {
        blackjackGame.showGameIntro();
        blackjackGame.startGame();

        while (blackjackGame.playersCanTakeAction()) {
            blackjackGame.playersTakeAction();
        }
        while (blackjackGame.dealerCanTakeAction()) {
            blackjackGame.dealerTakesAction();
        }
        blackjackGame.finishGame();
    }
}
