package monopoly.inventory;

import monopoly.enums.Denomination;
import monopoly.items.BillStack;
import monopoly.items.PropertyCard;

import java.util.ArrayList;
import java.util.HashMap;

public class Inventory {
    private ArrayList<PropertyCard> properties;
    private HashMap<Denomination, BillStack> moneyMap;
    
    public Inventory(ArrayList<PropertyCard> properties, boolean isBanker) {
       this.properties = properties;
        // TODO: initialize money HashMap using Denomination enum
    }

    public int getTotalMoney() {
        // TODO: calculate and return total amount of money in this inventory
        return -1;
    }

    public ArrayList<PropertyCard> getProperties() {
        return this.properties;
    }

    public HashMap<Denomination, BillStack> getMoneyMap() {
        return moneyMap;
    }

    /**
     * Sends <code>amount</code> of money to <code>invToSendTo</code>.
     * This method determines how much of each {@link Denomination} should be sent,
     * and calls <code>BillStack.trade</code> method on each BillStack that must be changed.
     *
     * @param invToSendTo inventory for money to be sent to
     * @param amount total amount of money to be sent
     * @param isChange whether this call is normal or sending change for a previous call
     * @return
     */
    public boolean sendMoney(Inventory invToSendTo, int amount, boolean isChange) {
        // TODO: implement sending money
        // calculate amount of each denomination, then use trade method on each BillStack that needs to be changed

        // return true if money was able to be sent, false otherwise
        return false;
    }
}
