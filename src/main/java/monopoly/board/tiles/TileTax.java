package monopoly.board.tiles;

import monopoly.Player;
import monopoly.board.Tile;
import monopoly.rendering.Mesh;

public class TileTax extends Tile {

    private int taxAmount;

    public TileTax(String title, Mesh mesh, int taxAmount) {
        super(title, mesh);
        this.taxAmount = taxAmount;
    }

    @Override
    public void onLanded(Player player) {

    }

    @Override
    public void renderMarker() {

    }
}
