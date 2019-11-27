package monopoly.board.tiles;

import monopoly.Player;
import monopoly.board.Tile;
import monopoly.rendering.Mesh;

public class TileFreeParking extends Tile {

    public TileFreeParking(String title, Mesh mesh) {
        super(title,mesh);
    }

    @Override
    public void onLanded(Player player) {

    }

}
