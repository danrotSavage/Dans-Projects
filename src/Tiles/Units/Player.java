package Tiles.Units;


import java.util.Random;

import Misc.MessageHandler;
import Misc.Position;
public abstract class Player extends Unit implements HeroicUnit{
    protected int Exp;
    protected int Level;
    public Player(int healthPool, int damage, int Defense, String name,char symbol,int Exp,int Level,Position position){
        super( healthPool,  damage,  Defense,  name, symbol, position);
        this.Level=Level;
        this.Exp=Exp;
    }


    protected void isLevelUp(){
        if(Exp>Level*50){
            Level++;

            Exp=Exp-(Level*50);

            int newHealthPool=health.getHealthPool()+(10*Level);
            health.setHealthPool(newHealthPool);

            health.setHealthAmount(newHealthPool);

            Damage=Damage+(3*Level);

            Defense=Defense+(Level);
        }
    }
    public  boolean Attack( Enemy enemy){
        Random randAttacker =new Random();
        int attackPool=Damage;
        int attack=randAttacker.nextInt(attackPool);//attacker damage

        Random randDefender =new Random();
        int defencePool=enemy.getDefense();
        int defence=randDefender.nextInt(defencePool);//Defence damage

        int AttackDamage= attack-defence;
        if(AttackDamage<0){
            AttackDamage=0;
        }
        int DefenderRemainedHealth=enemy.SubHealth(AttackDamage);
        String print;
        print = this.name +" attacked" + enemy.name+"\n";
        print = print + enemy.name +" rolled "+ defence + " defense points \n";
        print = print + this.name + " hit for " + attack + " damage\n";

        MessageHandler.printMessage((print));
        if(DefenderRemainedHealth<=0){
            print = enemy.name +" has died. " +this.name + " gained " + enemy.getExpValue() +"experience points";
            MessageHandler.printMessage(print);
            Exp=Exp+enemy.getExpValue();

            return true;//Defender dead
        }
        return false;
    }


}
