package monopoly.board.tiles;

import monopoly.Player;
import monopoly.board.IPassable;
import monopoly.board.Tile;

public class TileJailVisiting extends Tile implements IPassable {

    protected TileJailVisiting(String title, String texture) {
        super(title, texture);
    }

    @Override
    public void onLanded(Player player) {

    }

    @Override
    public void renderMarker() {

    }

    @Override
    public void onPassed(Player player) {

    }
}
