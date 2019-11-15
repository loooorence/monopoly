package monopoly.items;

import monopoly.enums.PropertyType;
import monopoly.inventory.ITradable;
import monopoly.inventory.Inventory;
import monopoly.board.tiles.TileProperty;

public class PropertyCard implements ITradable {

    private TileProperty property;

    private final PropertyType type;

    private final String name;
    private final int[] costs;

    public PropertyCard(String name, PropertyType type, int[] costs) {
        this.name = name;
        this.costs = costs;
        this.type = type;
    }

    @Override
    public boolean trade(Inventory source, Inventory target, int amount) {
        //TODO: implement trading of property cards
        return false;
    }
    
}
