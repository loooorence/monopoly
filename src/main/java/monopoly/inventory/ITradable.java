package monopoly.inventory;


public interface ITradable {

    /**
     * Moves tradable objects between inventories.
     * This method handles moving implementations of <code>ITradable</code> from
     * <code>source</code> inventory to <code>target</code> inventory.
     *
     * @param source the inventory containing the items to be traded
     * @param target the inventory that will receive the items
     * @param amount the amount that will be traded
     * @return whether trade was successful
     */
    boolean trade(Inventory source, Inventory target, int amount);
    
}
