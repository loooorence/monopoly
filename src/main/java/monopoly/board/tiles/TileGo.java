package monopoly.board.tiles;

import monopoly.Player;
import monopoly.board.IPassable;
import monopoly.board.Tile;
import monopoly.rendering.Mesh;

public class TileGo extends Tile implements IPassable {

    private int rewardAmount;

    public TileGo(String title, Mesh mesh, int rewardAmount) {
        super(title, mesh);
        this.rewardAmount = rewardAmount;
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
