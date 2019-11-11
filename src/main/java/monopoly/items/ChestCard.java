package monopoly.items;

import monopoly.Player;

public class ChestCard extends DrawableCard {

    public ChestCard(String text, int id, String texture) {
        super(text, id, texture);
    }

    @Override
    public void onDraw(Player player) {
        //TODO: implement community chest cards
        // if-else or switch for each id (this.id)
    }
}
