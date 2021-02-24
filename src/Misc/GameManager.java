package Misc;

import java.util.Scanner;

public class GameManager {


    private Board level;

    public void playGame(String url) {
        boolean keepGoing = true;
        while (keepGoing) {
            level = new Board(url);
            keepGoing = playLevel(level);
        }

    }

    public static boolean playLevel(Board b) {
        Board level = b;
        MessageHandler.printMessage(b.toString());
        while (!b.levelOver()) {
            b.playAllTurns();
            b.endTurn();
        }
        return b.won();
    }

}