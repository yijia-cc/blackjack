import java.util.List;

final public class GameSetup {
    private final int numSeats;
    private final List<PlayerInfo> playerInfos;

    GameSetup(int numSeats, List<PlayerInfo> playerInfos) {
        this.numSeats = numSeats;
        this.playerInfos = playerInfos;
    }

    public int getNumSeats() {
        return numSeats;
    }

    public List<PlayerInfo> getPlayerInfos() {
        return playerInfos;
    }
}
