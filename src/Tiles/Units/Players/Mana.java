package Tiles.Units.Players;

import Tiles.Units.Health;

public class Mana {//using health to implement its methods
   private Health health;

    public int getCost() {
        return Cost;
    }

    private int Cost;
    public Mana (int ManaPool,int ManaCost){
        health=new Health(ManaPool,ManaPool);
        Cost=ManaCost;
    }

    public int subtractMana(int HowMuch){
        return health.subtractHealth(HowMuch);
    }

    public int addHealth(int HowMuch){
        return health.addHealth(HowMuch);
    }

    public void setManaPool(int newManaPool){
        health.setHealthPool(newManaPool);
    }
    public void setManaCurrent(int newCurrentMana){
        health.setHealthAmount(newCurrentMana);
    }
    public int getManaPool(){
        return health.getHealthPool();
    }

    public int getCurrentMana(){
        return health.getHealthAmount();
    }

}


