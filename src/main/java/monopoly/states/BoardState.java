package monopoly.states;

import monopoly.Player;

public abstract class BoardState implements IState {

    protected Player player;

    protected BoardState(Player player) {
        this.player = player;
    }

    /**
     * Update the state for render-sensitive updates.
     *
     * @param alpha the percentage of an update interval that has passed since the last render
     */
    public abstract void renderUpdate(float alpha);
}
