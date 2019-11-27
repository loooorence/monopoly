package monopoly.items;

import monopoly.board.Tile;
import monopoly.enums.PropertyType;
import monopoly.inventory.ITradable;
import monopoly.board.tiles.TileProperty;
import monopoly.inventory.Inventory;
import monopoly.rendering.Mesh;
import monopoly.rendering.RenderableObject;

public class PropertyCard extends RenderableObject implements ITradable {

    private TileProperty property;

    private final PropertyType type;

    private final String name;
    private final int[] costs;

    public PropertyCard(String name, PropertyType type, int[] costs, Mesh mesh) {
        super(mesh);
        this.name = name;
        this.costs = costs;
        this.type = type;
    }

    public void setProperty(Tile tile) {
        this.property = (TileProperty)tile;
    }

    @Override
    public boolean trade(Inventory target, int amount) {
        //TODO: implement trading of property cards
        return false;
    }
    
}
