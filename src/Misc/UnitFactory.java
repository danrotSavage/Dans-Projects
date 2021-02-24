package Misc;

import Misc.Position;
import Tiles.Empty;
import Tiles.Tile;
import Tiles.Units.Enemy;
import Tiles.Units.Enemys.Monster;
import Tiles.Units.Enemys.Trap;
import Tiles.Units.Player;
import Tiles.Units.Players.Mage;
import Tiles.Units.Players.Rogue;
import Tiles.Units.Players.Warrior;
import Tiles.Wall;



public class UnitFactory {


    public static Tile createTile(char symbol, Position position) {
        switch (symbol) {
            //monster
            case'.':
                return new Empty('.',position);
            case'#':
                return new Wall('#',position);
        }
    return null;
    }

    public static Enemy createEnemy(char symbol, Position position) {
        switch (symbol) {
            //monster
            case 's':
                return new Monster("Lannister Soldier", 's', 80, 8, 3, 3, 25,position);
            case 'k':
                return new Monster("Lannister Knight", 'k', 200, 14, 8, 4, 50,position);
            case 'q':
                return new Monster("Queen’s Guard", 'q', 400, 20, 15, 5, 100,position);
            case 'z':
                return new Monster("Wright", 'z', 600, 30, 15, 3, 100,position);
            case 'b':
                return new Monster("Bear-Wright", 'b', 1000, 75, 30, 4, 250,position);
            case 'g':
                return new Monster("Giant-Wright", 'g', 1500, 100, 40, 5, 500,position);
            case 'w':
                return new Monster("White Walker", 'w', 2000, 150, 50, 6, 100,position);
            case 'M':
                return new Monster("The Mountain", 'M', 1000, 60, 25, 6, 500,position);
            case 'C':
                return new Monster("Queen Cersei", 'C', 100, 10, 10, 1, 1000,position);
            case 'K':
                return new Monster("Night’s King", 'K', 5000, 300, 150, 8, 5000,position);
            //traps
            case 'B':
                return new Trap("Bonus Trap", 'B', 1, 1, 1, 250, 1, 5,position);
            case 'Q':
                return new Trap("Queen’s Trap", 'Q', 250, 50, 10, 100, 3, 7,position);
            case 'D':
                return new Trap("Death Trap", 'D', 500, 100, 20, 250, 1, 10,position);

        }
        return null;
    }

    public static Player createPlayer(char symbol, Position position) {
        /*switch (symbol) {
            //players
            case '1':
                return new Warrior("Jon Snow", '@',300, 30, 4, 3,position);
            case '2':
                return new Warrior("The Hound",'@', 400, 20, 6, 5,position);
            case '3':
                return new Mage("Melisandre",'@', 100, 5, 1, 300, 30, 15, 5, 6,position);
            case '4':
                return new Mage("Thoros of Myr",'@', 250, 25, 4, 150, 20, 20, 3, 4,position);
            case '5':
                return new Rogue("Arya Stark", '@',150, 40, 2, 20,position);
            case '6':
                return new Rogue("Bronn",'@', 250, 35, 3, 50, position);

        }
        return null;


         */
        if(symbol=='@'){
            return new Warrior("Jon Snow", '@',200000, 1000, 4, 3,position);//only for testing
        }
        return null;

    }
}