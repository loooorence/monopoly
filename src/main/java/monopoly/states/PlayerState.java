package monopoly.states;

import monopoly.Player;

public abstract class PlayerState implements IState {

    private Player player;

    protected PlayerState(Player player) {
        this.player = player;
    }

    public abstract void inputKeyboard(int key, int scancode, int action, int mods);

    public abstract void inputMouse(int button, int action, int mods, float xPos, float yPos);
}
