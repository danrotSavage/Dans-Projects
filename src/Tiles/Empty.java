package Tiles;

import Misc.Position;
import Tiles.Units.Enemys.Boss;
import Tiles.Units.Enemys.Monster;
import Tiles.Units.Enemys.Trap;
import Tiles.Units.Players.Hunter;
import Tiles.Units.Players.Mage;
import Tiles.Units.Players.Rogue;
import Tiles.Units.Players.Warrior;
import VisitorPatern.Visitor;


public class Empty extends Tile {




    public Empty(char symbol, Position position){
        super(symbol,position);
    }

    @Override
    public boolean accept(Visitor v) {
        return v.visit(this);
    }

    @Override
    public boolean visit(Wall walle) {
        return false;
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
    public boolean visit(Empty empty) {
        return false;
    }
}
