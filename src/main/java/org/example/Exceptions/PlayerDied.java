package org.example.Exceptions;

public class PlayerDied extends RuntimeException {
    public PlayerDied(String diedPlayer, String livePlayer) {
      super("━".repeat(40)
              + "\n"
              + " ".repeat(5) + diedPlayer + " died\n"
              + " ".repeat(5) + livePlayer + " wins🏆🏆!!!!\n"
              + "━".repeat(40));
    }
}
