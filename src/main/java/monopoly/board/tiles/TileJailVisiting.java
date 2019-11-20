package monopoly.board.tiles;

import monopoly.Player;
import monopoly.board.IPassable;
import monopoly.board.Tile;
import monopoly.rendering.Mesh;

public class TileJailVisiting extends Tile implements IPassable {

    public TileJailVisiting(String title, Mesh mesh) {
        super(title, mesh);
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
