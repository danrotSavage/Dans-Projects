package Tiles;
import Misc.Position;
import Tiles.Units.Unit;
import VisitorPatern.Visitor;
import VisitorPatern.Visited;




public abstract class Tile implements Visited, Visitor {

    protected char Symbol;

    public void setPosition(Position position) {
        this.position = position;
    }

    protected Position position;

public Tile(char symbol,Position position){
    this.position=position;
    this.Symbol=symbol;
}

    public boolean Move(Tile t)  {
       return  t.accept(this);
    }

    @Override
    public boolean visit(Wall walle) {
        return false;
    }



    @Override
    public boolean visit(Empty empty) {
        return true;
    }


    public String ToString (){
        return Character.toString(Symbol);

    }

    public Position getPosition(){
         return position;
    }

}
