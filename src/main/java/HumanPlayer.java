public class HumanPlayer extends Player {
    HumanPlayer(BlackjackGame blackjackGame, PlayerInfo playerInfo) {
        this(blackjackGame, playerInfo.getId(), playerInfo.getName());
    }

    HumanPlayer(BlackjackGame blackjackGame, int id, String name) {
        super(blackjackGame, id, name);
    }

    @Override
    public boolean canTakeAction() {
        if (isBusted()) {
            return false;
        }
        if (hasBlackjack()) {
            return false;
        }
        return !hasDoneStand;
    }

    @Override
    public void takeAction() {
        Action action = blackjackGame.requestPlayerAction(this);
        switch (action) {
            case HIT:
                hit();
                break;
            case STAND:
                stand();
                break;
            default:
                stand();
        }
    }
}
