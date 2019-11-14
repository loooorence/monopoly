package monopoly.items;

import monopoly.Player;
import monopoly.enums.CardAction;

public class ChestCard extends DrawableCard {

    public ChestCard(String text, String texture, CardAction action, int[] values) {
        super(text, texture, action, values);
    }

    @Override
    public void onDraw(Player player) {
        //TODO: implement community chest cards
        // perform tasks based on action and values (see wiki)
    }
}
