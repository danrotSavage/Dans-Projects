package Tiles.Units;

import Misc.Position;
import java.util.Random;

public abstract class Enemy extends Unit {
    protected int ExpValue;

    public Enemy(int healthPool, int damage, int Defense, String name, char symbol, int Exp, Position position) {
        super(healthPool, damage, Defense, name, symbol,position);
        ExpValue = Exp;
    }

    public abstract Position inRangeMove(Player player);

    public int getExpValue() {
        return ExpValue;
    }
    public abstract int getRange();

    public boolean Attack(Player player) {
        Random randAttacker = new Random();
        int attackPool = Damage;
        int attack = randAttacker.nextInt(attackPool);//attacker damage

        Random randDefender = new Random();
        int defencePool = player.getDefense();
        int defence = randDefender.nextInt(defencePool);//Defence damage

        int AttackDamage = attack - defence;
        if (AttackDamage < 0) {
            AttackDamage = -1 * AttackDamage;
        }

        int DefenderRemainedHealth = player.SubHealth(AttackDamage);

        if (DefenderRemainedHealth <= 0) {
            return true;//player dead
        }
        return false;
    }

    public abstract Position outOfRangeMove();
}