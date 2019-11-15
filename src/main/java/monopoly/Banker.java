package monopoly;

import monopoly.inventory.Inventory;
import monopoly.inventory.InventoryHolder;
import monopoly.rendering.Mesh;

public class Banker extends InventoryHolder {

    public Banker(String name, Mesh mesh) {
        super(name,mesh);
        this.inventory = new Inventory(true);
    }
}
