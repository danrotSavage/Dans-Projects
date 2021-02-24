package Tiles.Units;

public class Health {
    int healthPool;
    int healthAmount;


    public Health (int healthPool,int healthAmount)
    {
        this.healthPool=healthPool;
        this.healthAmount=healthAmount;
    }

    public int subtractHealth(int HowMuch){
        int newHealth=healthAmount-HowMuch;
        if(newHealth<1){
            healthAmount=0;
            return 0;
        }
        else if(newHealth>=healthPool){
            healthAmount=healthPool;
            return healthAmount;
        }
        else{
            healthAmount=newHealth;
            return newHealth;
        }
    }

    public int addHealth(int HowMuch){
        int newHealth=healthAmount+HowMuch;
        if(newHealth>healthPool){
            healthAmount=healthPool;
            return healthPool;
        }
        else if(newHealth<1){
            healthAmount=0;
            return 0;
        }
        else{
            healthAmount=newHealth;
            return newHealth;
        }
    }




    public int getHealthPool() {
        return healthPool;
    }
    public void setHealthPool(int healthPool) {
        this.healthPool = healthPool;
    }
    public int getHealthAmount() {
        return healthAmount;
    }
    public void setHealthAmount(int healthAmount) {
        this.healthAmount = healthAmount;
    }
}
