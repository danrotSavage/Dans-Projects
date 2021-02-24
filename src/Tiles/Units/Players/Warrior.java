package Tiles.Units.Players;

import Misc.MessageHandler;
import Tiles.Units.*;
import Tiles.Units.Enemys.Boss;
import Tiles.Units.Enemys.Monster;
import Tiles.Units.Enemys.Trap;
import VisitorPatern.Visitor;

import Misc.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Warrior extends Player {

    int CoolDown;
    int AbilityRange=3;//<3
    int CoolDownCounter;


    public Warrior(String name, char symbol, int healthPool, int Attack, int Defense, int coolDown, Position position){
        super(healthPool,Attack,Defense,name,symbol,0,1, position);
        this.CoolDown=coolDown;
        CoolDownCounter=0;
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
    public void castAbility(List<Enemy> enemyList, Player player) {//Special ability:Avenger’s Shield, randomly hits one enemy withingrange <3for an amountequals to 10% of the warrior’s max health and heals the warrior for amount equals to(10×def ense)(but will not exceed the total amount of health pool).
        if(CoolDownCounter==0) {
            List<Enemy> EnemyInRange =new ArrayList<>() ;

            for(Enemy e :enemyList) {
                double distance = Position.range(e.getPosition(), this.getPosition());//distance between player and enemy

                if (distance < AbilityRange) {
                    EnemyInRange.add(e);
                }
            }

            Random rand=new Random();
            int randint=rand.nextInt(EnemyInRange.size());
            Enemy e=EnemyInRange.get(randint);
            double attackPower = ((double) health.getHealthPool()) * 0.1;
            e.SubHealth((int) attackPower);

            int healPower = Defense * 10;
            addHealth(healPower);

            CoolDownCounter = 3;


        }
        else {
            MessageHandler.printMessage("Cooldown is: " + CoolDownCounter + " could not cast special ability");

                }

    }

    @Override
    protected void isLevelUp(){
        while (Exp>Level*50) {
            super.isLevelUp();
            CoolDownCounter = 0;
            health.setHealthPool(health.getHealthPool() + (Level * 5));
            Damage = Damage + (2 * Level);
            Defense = Defense + (Level);
        }
    }

    public void endTurn(){
        if(CoolDownCounter>0){CoolDownCounter--;}
        isLevelUp();
    }



}
