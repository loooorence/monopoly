package monopoly.inventory;


public interface ITradable {

    /**
     * Moves tradable objects between inventories.
     * This method handles moving implementations of <code>ITradable</code> from
     * <code>source</code> inventory to <code>target</code> inventory.
     *
     * @param target the inventory that will receive the items
     * @param amount the amount that will be traded
     * @return whether trade was successful
     */
    boolean trade(Inventory target, int amount);
    
}
