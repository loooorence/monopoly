package monopoly.inventory;

import monopoly.rendering.Mesh;
import monopoly.rendering.RenderableObject;

public abstract class InventoryHolder extends RenderableObject {

    protected Inventory inventory;
    protected String name;

    protected InventoryHolder(String name, Mesh mesh) {
        super(mesh);
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
