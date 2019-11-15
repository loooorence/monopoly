package monopoly.board.tiles;

import monopoly.Player;
import monopoly.board.Tile;
import monopoly.rendering.Mesh;

public class TileJailGoTo extends Tile {

    protected TileJailGoTo(String title, Mesh mesh) {
        super(title, mesh);
    }

    @Override
    public void onLanded(Player player) {

    }

    @Override
    public void renderMarker() {

    }
}
