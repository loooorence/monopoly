package monopoly.board.tiles;

import monopoly.Player;
import monopoly.board.Tile;

public class TileChest extends Tile {

    protected TileChest(String title, String texture) {
        super(title, texture);
    }

    @Override
    public void onLanded(Player player) {

    }

    @Override
    public void renderMarker() {

    }
}
