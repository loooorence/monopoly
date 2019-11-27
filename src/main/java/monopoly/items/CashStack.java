package monopoly.items;

import monopoly.inventory.ITradable;
import monopoly.inventory.Inventory;
import monopoly.rendering.Mesh;
import monopoly.rendering.RenderableObject;

public class CashStack extends RenderableObject implements ITradable {

    private int amount;
    
    public CashStack(int amount, Mesh mesh) {
        super(mesh);
        this.amount = amount;
    }
    
    public int getAmount() {
        return amount;
    }

    public void changeAmount(int delta) {
        amount += delta;
    }
    
    @Override
    public boolean trade(Inventory target, int amount) {
        this.changeAmount(-amount);
        target.getCashStack().changeAmount(amount);
        return true;
    }
}
