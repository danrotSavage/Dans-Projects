package VisitorPatern;

import Tiles.Empty;
import Tiles.Units.Enemys.Boss;
import Tiles.Units.Enemys.Monster;
import Tiles.Units.Enemys.Trap;
import Tiles.Units.Players.Hunter;
import Tiles.Units.Players.Mage;
import Tiles.Units.Players.Rogue;
import Tiles.Units.Players.Warrior;
import Tiles.Wall;

public interface Visitor {
    public boolean visit(Wall walle);



    public boolean visit(Monster monster);
    public boolean visit(Boss boss);
    public boolean visit(Trap trap);

    public boolean visit(Hunter hunter);
    public boolean visit(Mage mage);
    public boolean visit(Rogue rogue);
    public boolean visit(Warrior warrior);


    public boolean visit(Empty empty);

}
