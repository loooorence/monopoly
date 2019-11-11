package monopoly.items;

import monopoly.Player;
import monopoly.inventory.ITradable;
import monopoly.inventory.Inventory;

public class GetOutOfJailCard implements ITradable {

    @Override
    public boolean trade(Inventory source, Inventory target, int amount) {
        //TODO: implement trading of card
        return false;
    }

    public void activate(Player player ) {
        //TODO: implement get out of jail card
    }
}
