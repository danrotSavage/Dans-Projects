package Tiles.Units.Enemys;



import Tiles.Units.Enemy;
import Tiles.Units.Player;
import Tiles.Units.Players.Hunter;
import Tiles.Units.Players.Mage;
import Tiles.Units.Players.Rogue;
import Tiles.Units.Players.Warrior;
import VisitorPatern.Visitor;

import Misc.Position;
import java.util.Random;

public class Monster extends Enemy {
    private int VisionRange;


    public Monster( String name,char Symbol,int healthPool, int damage, int Defense, int visionRange,int expValue,Position position) {
      super(healthPool,damage,Defense,name,Symbol,expValue,position);
      this.VisionRange=visionRange;
    }

    public int getRange() {
        return VisionRange;
    }

    public Position inRangeMove(Player player) {
        int dy = this.position.getX() - player.getPosition().getX();
        int dx = this.position.getY() - player.getPosition().getY();
        if (Math.abs(dx) > Math.abs(dy)) {
            if (dx > 0)
                return new Position(this.position.getX() , this.position.getY()-1);
            else
                return new Position(this.position.getX() , this.position.getY()+1);
        } else if (dy > 0)
            return new Position(this.position.getX()-1, this.position.getY() );
        else
            return new Position(this.position.getX()+1, this.position.getY() );

    }

    public Position outOfRangeMove() {
        Random move = new Random();
        int rand = move.nextInt(5);//attacker damage

        switch (rand) {
            //monster
            case 0:
                return new Position(this.position.getX(), this.position.getY());
            case 1:
                return new Position(this.position.getX(), this.position.getY() + 1);
            case 2:
                return new Position(this.position.getX(), this.position.getY() - 1);
            case 3:
                return new Position(this.position.getX() + 1, this.position.getY());
            case 4:
                return new Position(this.position.getX() - 1, this.position.getY());

        }
        return null;

    }

    @Override
    public void endTurn() {

    }



    @Override
    public boolean accept(Visitor v) {
        return v.visit(this);
    }


    @Override
    public boolean visit(Monster monster) {
        return false;
    }

    @Override
    public boolean visit(Boss boss) {
        return false;
    }

    @Override
    public boolean visit(Trap trap) {
        return false;
    }

    @Override
    public boolean visit(Hunter hunter) {
        return Attack(hunter);
    }

    @Override
    public boolean visit(Mage mage) {
        return Attack(mage);
    }

    @Override
    public boolean visit(Rogue rogue) {
        return Attack(rogue);
    }

    @Override
    public boolean visit(Warrior warrior) {
        return Attack(warrior);
    }



}
