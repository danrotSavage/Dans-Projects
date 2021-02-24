package Tiles.Units.Enemys;

import Tiles.Empty;
import Tiles.Units.Enemy;
import Tiles.Units.Player;
import Tiles.Units.Players.Hunter;
import Tiles.Units.Players.Mage;
import Tiles.Units.Players.Rogue;
import Tiles.Units.Players.Warrior;
import Tiles.Units.Unit;
import VisitorPatern.Visitor;

import Misc.Position;

import java.beans.Visibility;

public  class Trap extends Enemy {

    private  int VisibilityTime;
    private  int InvisibilityTime;
    private  int range=1;

    private String fakeSymbol =".";
    private int ViInCounter=0;
    private boolean Visible=true;

    public Trap(String name, char symbol, int healthPool, int Attack, int Defense, int expValue, int visibilityTime, int invisibilityTime,Position position){
        super(healthPool,Attack,Defense,name,symbol,expValue, position);

        VisibilityTime = visibilityTime;
        InvisibilityTime = invisibilityTime;
    }
    public Position inRangeMove(Player player) {
        Attack(player);
        ViInCounter++;
        return position;//dont move
    }
    public Position outOfRangeMove(){
        ViInCounter++;
        return position;//dont move

    }
    public int getRange() {
        return range;
    }

    @Override
    public String ToString(){
        IfVisible();
        if(Visible){
            return String.valueOf(Symbol);
        }
        else {
            return  fakeSymbol;
        }
    }

    private void IfVisible(){
        if(ViInCounter<VisibilityTime){
            Visible=true;
        }
        if(VisibilityTime+InvisibilityTime==ViInCounter){
            ViInCounter=0;
            Visible=true;
        }
        if(InvisibilityTime>ViInCounter&& VisibilityTime<=ViInCounter){
            Visible=false;
        }
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
    public boolean visit(Empty empty){return false;}

    @Override
    public void endTurn() {
        ViInCounter++;
    }
}
