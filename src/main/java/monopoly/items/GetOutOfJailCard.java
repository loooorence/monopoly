package monopoly.items;

import monopoly.Player;
import monopoly.inventory.ITradable;
import monopoly.inventory.Inventory;
import monopoly.rendering.Mesh;
import monopoly.rendering.RenderableObject;

public class GetOutOfJailCard extends RenderableObject implements ITradable {

    public GetOutOfJailCard(Mesh mesh) {
        super(mesh);
    }

    @Override
    public boolean trade(Inventory source, Inventory target, int amount) {
        //TODO: implement trading of card
        return false;
    }

    public void activate(Player player ) {
        //TODO: implement get out of jail card
    }
}
