package monopoly.items;

import monopoly.Player;
import monopoly.enums.CardAction;
import monopoly.rendering.IRenderable;

public abstract class DrawableCard implements IRenderable {
    protected final String text;
    private final String texture;
    private final CardAction action;
    private final int[] values;

    DrawableCard(String text, String texture, CardAction action, int[] values) {
        this.text = text;
        this.texture = texture;
        this.action = action;
        this.values = values;
    }

    public abstract void onDraw(Player player);
}
