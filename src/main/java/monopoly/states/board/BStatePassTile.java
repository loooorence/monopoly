package monopoly.states.board;

import monopoly.Player;
import monopoly.board.IPassable;
import monopoly.board.Tile;
import monopoly.states.BoardState;
import monopoly.states.IState;

public class BStatePassTile extends BoardState {

    private Tile tilePassed;

    public BStatePassTile(Player player, Tile tilePassed) {
        super(player);
        this.tilePassed = tilePassed;
    }

    @Override
    public void renderUpdate(float alpha) {

    }

    @Override
    public void beginState() {
        if (tilePassed instanceof IPassable) {
            ((IPassable)tilePassed).onPassed(player);
        }
    }

    @Override
    public IState update() {
        return null;
    }

    @Override
    public boolean isStacked() {
        return true;
    }
}
