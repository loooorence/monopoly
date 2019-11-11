package monopoly.board.tiles;

import monopoly.Player;
import monopoly.board.Tile;

public class TileTax extends Tile {

    private int taxAmount;

    protected TileTax(String title, String texture, int taxAmount) {
        super(title, texture);
        this.taxAmount = taxAmount;
    }

    @Override
    public void onLanded(Player player) {

    }

    @Override
    public void renderMarker() {

    }
}
