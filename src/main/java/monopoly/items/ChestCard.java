package monopoly.items;

import monopoly.Player;
import monopoly.enums.CardAction;
import monopoly.rendering.Mesh;

public class ChestCard extends DrawableCard {

    public ChestCard(String text, Mesh mesh, CardAction action, int[] values) {
        super(text, mesh, action, values);
    }

    @Override
    public void onDraw(Player player) {
        //TODO: implement community chest cards
        // perform tasks based on action and values (see wiki)
    }
}
