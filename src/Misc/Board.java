
package Misc;


        import ObserverPattern.EndTurn;
        import Tiles.Tile;
        import Tiles.Units.Enemy;
        import Tiles.Units.Player;
        import Tiles.Units.Unit;
        import java.io.IOException;
        import java.nio.file.Files;
        import java.nio.file.Paths;
        import java.util.Collections;
        import java.util.LinkedList;
        import java.util.List;
        import java.util.Scanner;

public class Board {
    private Tile[][] Board;

    private Player player;
    private List<Enemy> unitsPosition = new LinkedList<>();
    private EndTurn endTurnObject;

    public Board(String path) {
        List<String> lines = readAllLine(path);
        endTurnObject = new EndTurn();
        Board = new Tile[lines.size()][longestRowLength(lines)];
        int rowPlace = 0;
        for (String row : lines) {
            for (int i = 0; i < row.length(); i++) {
                //if unit add position
               /* if (row.charAt(i) == '@')
                    playerPosition = new Position(rowPlace, i);

                if (row.charAt(i) != '.' & row.charAt(i) != '#' & row.charAt(i) != '@')
                    unitsPosition.add(new Position(rowPlace, i));*/
                Player p = UnitFactory.createPlayer(row.charAt(i), new Position(rowPlace, i));
                Tile t = UnitFactory.createTile(row.charAt(i), new Position(rowPlace, i));
                Enemy m = UnitFactory.createEnemy(row.charAt(i), new Position(rowPlace, i));
                if (p != null && t == null && m == null) {
                    player = p;
                    Board[rowPlace][i] = p;
                    endTurnObject.addObserver(p);
                } else if (p == null && t != null && m == null) {
                    Board[rowPlace][i] = t;
                } else if (p == null && t == null && m != null) {
                    unitsPosition.add(m);
                    Board[rowPlace][i] = m;
                    endTurnObject.addObserver(m);

                } else {
                    throw new IllegalArgumentException("unit factory didn't created Tile");
                }

            }
            rowPlace++;
        }
    }

    public void endTurn()
    {

        MessageHandler.printMessage(this.toString());
        for (Enemy e : unitsPosition)
        {
            if(e.getHealth()==0) {
                unitsPosition.remove(e);
                Board[e.getPosition().getX()][e.getPosition().getY()]= UnitFactory.createTile('.',e.getPosition());
            }
        }
        this.endTurnObject.notifyObserver();


    }

    public void playAllTurns() {

        Scanner reader = new Scanner(System.in);

        char c = reader.next().charAt(0);

        PlayerTurn(c);
        enemyTurn();

    }

    public boolean levelOver()
    {
        return (unitsPosition.isEmpty()| player.getHealth()==0);
    }
    public boolean won()
    {
        return player.getHealth()!=0;
    }

    public void enemyTurn() {
        for (Enemy enemy : unitsPosition) {
            Position moveTo;
            if (Position.range(enemy.getPosition(), player.getPosition()) < enemy.getRange())
                moveTo = enemy.inRangeMove(player);//monster move to player ,trap attack player and not moves
            else
                moveTo = enemy.outOfRangeMove();
            if (enemy.Move(Board[moveTo.getX()][moveTo.getY()])) {
                moveUnit(enemy.getPosition(), moveTo);

            }
        }
    }
    public void removeEnemy(Enemy e)
    {
        unitsPosition.remove(e);

    }

    public void PlayerTurn(char move) {
        int playerX = player.getPosition().getX();
        int playerY = player.getPosition().getY();
        Position moveTo = player.getPosition();
        boolean canMove = false;
        switch (move) {
            //monster
            case 'w':
                canMove = Board[playerX][playerY].Move(Board[playerX-1][playerY ]);
                moveTo = new Position(playerX-1, playerY );
                break;
            case 's':
                canMove = Board[playerX][playerY].Move(Board[playerX+1][playerY ]);
                moveTo = new Position(playerX+1, playerY );
                break;
            case 'a':
                canMove = Board[playerX][playerY].Move(Board[playerX ][playerY-1]);
                moveTo = new Position(playerX , playerY-1);
                break;
            case 'd':
                canMove = Board[playerX][playerY].Move(Board[playerX ][playerY+1]);
                moveTo = new Position(playerX , playerY+1);
                break;
            case 'q':
                canMove = Board[playerX][playerY].Move(Board[playerX][playerY]);
                moveTo = new Position(playerX, playerY);
                break;
            //  case 'e':
            //      player.castAbility(unitsPosition);


        }
        if (canMove) {

            moveUnit(player.getPosition(), moveTo);
        }
    }

    private void moveUnit(Position from, Position to) {//after checking if it is posible move the unit
        int fromX = from.getX();
        int fromY = from.getY();
        Tile moving = Board[fromX][fromY];
        Board[fromX][fromY] = UnitFactory.createTile('.', new Position(fromX, fromY));//put empty tile where the unit where
        int toX = to.getX();
        int toY = to.getY();
        Board[toX][toY] = moving;
        moving.setPosition(new Position(toX, toY));//take the unit forward
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < Board.length; i++) {
            for (int j = 0; j < Board[0].length; j++) {
                str = str + Board[i][j].ToString();


            }
            str += "\n";
        }
        return str;
    }


    public List<String> readAllLine(String path) {
        List<String> lines = Collections.emptyList();
        try {
            int length = -1;
            lines = Files.readAllLines(Paths.get(path));


        } catch (IOException e) {
            System.out.println(e.getMessage() + "  " + e.getStackTrace());

        }
        return lines;
    }

    public int longestRowLength(List<String> lines) {
        int length = lines.get(0).length();
        for (String m : lines) {
            if (m.length() > length)
                length = m.length();
        }
        return length;

    }

}
