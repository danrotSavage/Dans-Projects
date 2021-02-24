import Misc.Board;
import Misc.GameManager;
import Tiles.Units.Enemys.Monster;
import Tiles.Units.Unit;
import Tiles.Units.Players.Warrior;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Board x=new Board("C:\\Users\\guyne\\OneDrive\\Documents\\Ben-gurion\\codingAssigment\\OOP3\\OOP3Git\\OOP3\\guy\\src\\testLevel.txt");
        GameManager.playLevel(x);
        System.out.println(x.toString());
/*
        while (1==1) {
            x.playAllTurns();

            System.out.println(x.toString());*/

        }
    }

