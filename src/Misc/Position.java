package Misc;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y)
    {
        this.x=x;
        this.y=y;
    }



    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }


    public static double range(Position attacker, Position defender)
    {
        return Math.sqrt(Math.pow( attacker.getX()-defender.getX(),2.0) +Math.pow( attacker.getY()-defender.getY(),2.0));

    }

}
