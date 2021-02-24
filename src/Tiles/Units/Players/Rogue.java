package Tiles.Units.Players;

import Misc.MessageHandler;
import Tiles.Units.*;

import Tiles.Units.Enemys.Boss;
import Tiles.Units.Enemys.Monster;
import Tiles.Units.Enemys.Trap;
import VisitorPatern.Visitor;

import Misc.Position;

import java.util.List;

public class Rogue extends Player {

    int Cost;
    int currentEnergy=100;
    public Rogue(String name,char symbol,int healthPool,int Attack,int Defense,int Cost,Position position){
        super(healthPool,Attack,Defense,name,symbol,0,1, position);
        this.Cost=Cost;

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
            if(currentEnergy<Cost)
                MessageHandler.printMessage("Not enough energy, could not use special ability");
            else {
                MessageHandler.printMessage(this.name+" used Fan of Knives");
                currentEnergy = currentEnergy - Cost;
                for (Enemy e :enemyList)
                    if(Position.range(this.position,e.getPosition())<2)
                        player.Attack(e);

            }
    }

    @Override
    protected void isLevelUp(){
        while(Exp>Level*50) {
            super.isLevelUp();
        }
        currentEnergy=100;
        Damage=Damage+(3*Level);
    }


    @Override
    public void endTurn() {
         isLevelUp();
         currentEnergy=Math.min(currentEnergy+10,100);
    }
}
