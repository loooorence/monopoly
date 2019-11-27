package monopoly.inventory;

import monopoly.items.CashStack;
import monopoly.items.GetOutOfJailCard;
import monopoly.items.PropertyCard;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<PropertyCard> properties;
    private CashStack cashStack;
    private GetOutOfJailCard getOutOfJailCard;
    
    public Inventory(ArrayList<PropertyCard> properties, boolean isBanker) {
       this.properties = properties;
        // TODO: initialize
    }

    public ArrayList<PropertyCard> getProperties() {
        return this.properties;
    }

    public CashStack getCashStack() {
        return cashStack;
    }

    /**
     * Sends <code>amount</code> of money to <code>invToSendTo</code>.
     * This method determines how much of each {@link Denomination} should be sent,
     * and calls <code>BillStack.trade</code> method on each BillStack that must be changed.
     *
     * @param target inventory for money to be sent to
     * @param amount total amount of money to be sent
     * @return
     */
    public boolean sendMoney(InventoryHolder target, int amount) {
        // TODO: implement sending money

        return false;
    }

    public GetOutOfJailCard getGetOutOfJailCard() {
        return getOutOfJailCard;
    }
}
