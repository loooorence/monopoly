package monopoly.board.tiles;

import monopoly.Player;
import monopoly.board.Tile;
import monopoly.rendering.Mesh;

public class TileJail extends Tile {

    private int jailTime;

    public TileJail(String title, Mesh mesh, int jailTime) {
        super(title, mesh);
        this.jailTime = jailTime;
    }

    @Override
    public void onLanded(Player player) {

    }

    @Override
    public void renderMarker() {

    }

}
