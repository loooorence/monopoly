package monopoly.inventory;

public abstract class InventoryHolder {

    protected Inventory inventory;
    protected String name;
    protected String texture;

    protected InventoryHolder(String name, String texture) {
        this.name = name;
        this.texture = texture;
    }

    public String getName() {
        return this.name;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
