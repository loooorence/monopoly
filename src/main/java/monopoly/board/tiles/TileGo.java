package monopoly.board.tiles;

import monopoly.Player;
import monopoly.board.IPassable;
import monopoly.board.Tile;

public class TileGo extends Tile implements IPassable {

    private int rewardAmount;

    protected TileGo(String title, String texture, int rewardAmount) {
        super(title, texture);
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
