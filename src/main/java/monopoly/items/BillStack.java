package monopoly.items;

import monopoly.enums.Denomination;
import monopoly.inventory.ITradable;
import monopoly.inventory.Inventory;

public class BillStack implements ITradable {
    private int amount;
    private int value;
    private final Denomination denomination;
    
    public BillStack(Denomination denomination, int amount) {
        this.denomination = denomination;
        this.amount = amount;
        this.value = denomination.getValue();
    }
    
    public int getAmount() {
        return amount;
    }
    
    @Override
    public boolean trade(Inventory source, Inventory target, int amount) {
        // TODO: implement trade of money between inventories
        // use getMoneyMap() from each inventory and manipulate each map
        return false;
    }

    public int getValue() {
        return this.value;
    }

    public Denomination getDenomination() {
        return this.denomination;
    }
}
