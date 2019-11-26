package monopoly;

import monopoly.inventory.Inventory;
import monopoly.inventory.InventoryHolder;
import monopoly.items.PropertyCard;
import monopoly.rendering.Mesh;

import java.util.ArrayList;

public class Banker extends InventoryHolder {

    public Banker(String name, Mesh mesh, ArrayList<PropertyCard> properties) {
        super(name,mesh);
        this.inventory = new Inventory(properties, true);
    }
}
