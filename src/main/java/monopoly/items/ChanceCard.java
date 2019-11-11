package monopoly.items;

import monopoly.Player;

public class ChanceCard extends DrawableCard {

    public ChanceCard(String text, int id, String texture) {
        super(text, id, texture);
    }

    @Override
    public void onDraw(Player player) {
        //TODO: implement chance cards
        // if-else or switch for each id (this.id)
    }
}
