package monopoly.states.board;

import monopoly.Player;
import monopoly.board.Tile;
import monopoly.states.BoardState;
import monopoly.states.IState;

public class BStateLandTile extends BoardState {

    private Tile tileLanded;

    protected BStateLandTile(Player player, Tile tileLanded) {
        super(player);
        this.tileLanded = tileLanded;
    }

    @Override
    public void renderUpdate(float alpha) {

    }

    @Override
    public void beginState() {
        tileLanded.onLanded(player);
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
