package monopoly.items;

import monopoly.Player;
import monopoly.inventory.ITradable;
import monopoly.inventory.Inventory;
import monopoly.rendering.Mesh;
import monopoly.rendering.RenderableObject;

public class GetOutOfJailCard extends RenderableObject implements ITradable {

    private int amount;

    public GetOutOfJailCard(Mesh mesh) {
        super(mesh);
        amount = 0;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean trade(Inventory target, int amount) {
        //TODO: implement trading of card
        return false;
    }

    public void activate(Player player ) {
        //TODO: implement get out of jail card
    }
}
