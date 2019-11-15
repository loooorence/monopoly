package monopoly;

import monopoly.inventory.Inventory;
import monopoly.inventory.InventoryHolder;

public class Banker extends InventoryHolder {

    public Banker(String name, String texture) {
        super(name,texture);
        this.inventory = new Inventory(true);
    }
}
