package monopoly.items;

import monopoly.Player;
import monopoly.enums.CardAction;

public class ChanceCard extends DrawableCard {

    public ChanceCard(String text, String texture, CardAction action, int[] values) {
        super(text, texture, action, values);
    }

    @Override
    public void onDraw(Player player) {
        //TODO: implement chance cards
        // perform tasks based on action and values (see wiki)
    }
}
