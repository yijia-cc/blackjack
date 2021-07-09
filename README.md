# Black Jack

## Getting Started

### Build

```bash
./gradlew build
```

### Run

```bash
java -jar build/libs/blackjack-1.0-SNAPSHOT.jar
```

## Testing

Unit tests should be added in the future to prevent regression.

```bash
./gradlew test
```

## TODO

### Basic

- Add unit tests to prevent regression.
- Support async Web UI to multiple player to play them game.
- Allow players to purchase wagers for betting.
- Support leaderboard to increase the competitiveness the game.

### Advanced Scaling

- Add instrumentation code to collect logs & metrics to facilitate debugging and increase system observability.
- Support running multiple games concurrently on a single game server.
- Support multi-server architecture (e.g. load balancing, sharding) for better online game performance.
- Support dynamically allocate game servers based on the player's region & availability zone to minimize network latency.
- Use centralized state manager, immutable data structures and functional programming paradigm to eliminate
  locks, to bootstrap performance, and to support undo/redo.