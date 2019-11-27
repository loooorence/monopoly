package monopoly.items;

import monopoly.Player;
import monopoly.enums.CardAction;
import monopoly.rendering.Mesh;

public class ChanceCard extends DrawableCard {

    public ChanceCard(String text, Mesh mesh, CardAction action, int[] values) {
        super(text, mesh, action, values);
    }

    @Override
    public void onDraw(Player player) {
        //TODO: implement chance cards
        if (getAction() == CardAction.MOVE) {
            player.moveSpaces(getValues()[0]);
        }
    }
}
