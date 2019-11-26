package monopoly.states.board;

import monopoly.Player;
import monopoly.board.Tile;
import monopoly.states.BoardState;
import monopoly.states.IState;
import monopoly.util.Queue;
import org.joml.Vector3f;

public class BStateMovement extends BoardState {

    private Queue<Tile> movementTiles;
    private Tile targetTile;

    public BStateMovement(Player player, Queue<Tile> movementTiles) {
        super(player);
        this.movementTiles = movementTiles;
    }

    public BStateMovement(Player player, Tile targetTile) {
        super(player);
        this.targetTile = targetTile;
    }

    @Override
    public void beginState() {
        if (movementTiles != null) {
            advancePlayer();
        }
    }

    private void advancePlayer() {
        targetTile = movementTiles.dequeue();
        Vector3f targetPosition = targetTile.getPosition();
        player.moveObjectToTarget(targetPosition.x, targetPosition.y, targetPosition.z, Player.MOVEMENT_SPEED);
    }

    @Override
    public IState update() {
        if (movementTiles == null) {
            Vector3f targetPosition = targetTile.getPosition();
            player.setPosition(targetPosition.x, targetPosition.y, targetPosition.z);
            return null;
        } else {
            if (!player.isMoving()) {
                if (!movementTiles.isEmpty()) {
                    BStatePassTile nextState = new BStatePassTile(player, targetTile);
                    advancePlayer();
                    return nextState;
                } else {
                    return new BStateLandTile(player, targetTile);
                }
            } else {
                return this;
            }
        }
    }

    @Override
    public boolean isStacked() {
        return true;
    }

    @Override
    public void renderUpdate(float alpha) {
        player.updateTransformations(alpha);
    }
}
