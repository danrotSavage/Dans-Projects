package Tiles.Units;
import ObserverPattern.Observer;
import Tiles.Empty;
import Tiles.Tile;
import Tiles.Wall;
import Misc.Position;



public abstract class Unit extends Tile implements Observer {
    protected Health health;
    protected int Damage;
    protected int Defense;
    protected String name;

    public Unit(int healthPool, int damage, int Defense, String name, char symbol, Position position) {
        super(symbol,position);
        this.health=new Health(healthPool,healthPool);
        this.Damage=damage;
        this.Defense=Defense;
        this.name=name;
    }

    @Override
    public boolean visit(Wall walle) {
        return false;
    }

    @Override
    public boolean visit(Empty empty) {
        return true;
    }

    public int getHealth() {
        return health.getHealthAmount();
    }
    public int SubHealth(int health) {
        return this.health.subtractHealth(health);
    }
    public  int addHealth(int health){
        return this.health.addHealth(health);
    }
    public int getDamage() {
        return Damage;
    }
    public int getDefense() {
        return Defense;
    }







}
