package monopoly.items;

import monopoly.Player;
import monopoly.rendering.IRenderable;

public abstract class DrawableCard implements IRenderable {
    protected final String text;
    protected final int id;
    private final String texture;

    DrawableCard(String text, int id, String texture) {
        this.text = text;
        this.id = id;
        this.texture = texture;
    }

    public abstract void onDraw(Player player);
}
