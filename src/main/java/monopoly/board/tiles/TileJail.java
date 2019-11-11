package monopoly.board.tiles;

import monopoly.Player;
import monopoly.board.Tile;

public class TileJail extends Tile {

    private int jailTime;

    public TileJail(String title, String texture, int jailTime) {
        super(title, texture);
        this.jailTime = jailTime;
    }

    @Override
    public void onLanded(Player player) {

    }

    @Override
    public void renderMarker() {

    }

}
