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
import java.util.Random;

public class Mage extends Player {

    int SpellPower;
    int HitCount;
    int Range;
    Mana mana;

    public Mage(String name, char symbol, int healthPool, int Attack, int Defense, int ManaPool, int ManaCost, int SpellPower, int HitCount, int Range, Position position) {
        super(healthPool, Attack, Defense, name, symbol, 0, 1,position);
        mana = new Mana(ManaPool, ManaCost);
        this.SpellPower = SpellPower;
        this.HitCount = HitCount;
        this.Range = Range;

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
          if(mana.getCurrentMana()<mana.getCost())
          {
              MessageHandler.printMessage("Could not cast special ability- mana is too low. need " + (mana.getCost()-mana.getCurrentMana())+" more mana. ");
          }
          else {
              List<Enemy> EnemyInRange =new ArrayList<>() ;
              for(Enemy e :enemyList) {
                  double distance = Position.range(e.getPosition(), this.getPosition());//distance between player and enemy

                  if (distance < Range) {
                      EnemyInRange.add(e);
                  }
              }
              mana.subtractMana(mana.getCost());
              int hits=0;
              Random rand=new Random();

              while (hits<HitCount&&!EnemyInRange.isEmpty()) {
                  int randint = rand.nextInt(EnemyInRange.size());
                  Enemy e = EnemyInRange.get(randint);
                  Attack(e);
                  hits++;
              }
          }
    }

    @Override
    protected void isLevelUp(){
        while(Exp>Level*50) {
            super.isLevelUp();
            mana.setManaPool(mana.getManaPool() + (25 * Level));

            double newCurrentMana = Math.min(mana.getManaPool(), (mana.getCurrentMana() + (mana.getManaPool() / 4)));
            mana.setManaCurrent((int) newCurrentMana);

            SpellPower = SpellPower + (10 * Level);
        }
    }


    @Override
    public void endTurn() {
       isLevelUp();
       mana.setManaCurrent(Math.min((mana.getCurrentMana()+1)*Level,mana.getManaPool()));
    }
}