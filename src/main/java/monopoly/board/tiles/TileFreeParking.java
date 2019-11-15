package monopoly.board.tiles;

import monopoly.Player;
import monopoly.board.Tile;

public class TileFreeParking extends Tile {

    public TileFreeParking(String title, String texture) {
        super(title,texture);
    }

    @Override
    public void onLanded(Player player) {

    }

    @Override
    public void renderMarker() {

    }

}
