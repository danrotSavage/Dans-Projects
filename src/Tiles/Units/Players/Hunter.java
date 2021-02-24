package Tiles.Units.Players;

import Misc.MessageHandler;
import Tiles.Units.Enemy;
import Tiles.Units.Enemys.Boss;
import Tiles.Units.Enemys.Monster;
import Tiles.Units.Enemys.Trap;
import Tiles.Units.Player;
import Tiles.Units.Unit;
import VisitorPatern.Visitor;

import Misc.Position;

import java.util.ArrayList;
import java.util.List;

public class Hunter extends Player {

    int Range;
    int ArrowsCount;
    int tickCounts;

    public Hunter(String name, char symbol, int healthPool, int Attack, int Defense, int Range, Position position){
        super(healthPool,Attack,Defense,name,symbol,0,1,position);
        this.Range=Range;
        this.ArrowsCount=10;
        tickCounts=0;

    }

    @Override
    public boolean accept(Visitor v) {
        return v.visit(this);
    }

    @Override
    public boolean visit(Monster monster) {
        return Attack(monster);
    }

    @Override
    public boolean visit(Boss boss) {
        return Attack(boss);
    }

    @Override
    public boolean visit(Trap trap) {
        return Attack(trap);
    }

    @Override
    public boolean visit(Hunter hunter) {
        return false;
    }

    @Override
    public boolean visit(Mage mage) {
        return false;
    }

    @Override
    public boolean visit(Rogue rogue) {
        return false;
    }

    @Override
    public boolean visit(Warrior warrior) {
        return false;
    }


    @Override
    public void castAbility(List<Enemy> enemyList, Player player) {
        boolean EnemyInRange =false;
            if (ArrowsCount==0)
                MessageHandler.printMessage("Not enough arrows to cast special ability.");
            for (Enemy e :enemyList)
            {
                if(EnemyInRange)
                    break;
                if (Position.range(e.getPosition(),player.getPosition())<this.Range) {
                    player.Attack(e);
                    EnemyInRange=true;
                }
                if (!EnemyInRange)
                    MessageHandler.printMessage("No enemy was in range.");
            }
    }

    @Override
    protected void isLevelUp(){
        while(Exp>Level*50) {
            super.isLevelUp();
            ArrowsCount=ArrowsCount+(10*Level);
            Damage=Damage+(2*Level);
            Defense=Defense+Level;
        }
    }


    @Override
    public void endTurn() {
       isLevelUp();
       if(tickCounts==10)
       {
            ArrowsCount=ArrowsCount+Level;
            tickCounts=0;
       }
       else{
            tickCounts++;
        }
    }
}
