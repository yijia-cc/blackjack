import java.util.Random;

final public class BotPlayer extends HumanPlayer {
    private static final Action[] availableActions = new Action[] {
            Action.HIT,
            Action.STAND
    };

    BotPlayer(BlackjackGame blackjackGame, int id) {
        super(blackjackGame, id, String.format("Bot %d", id));
    }

    @Override
    public void takeAction() {
        Random random = new Random();
        int index = Math.abs(random.nextInt() % 2);
        switch (availableActions[index]) {
            case HIT:
                hit();
                break;
            case STAND:
                stand();
                break;
            default:
                stand();
                break;
        }
    }
}
