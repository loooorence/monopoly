package monopoly.items;

import monopoly.Player;
import monopoly.enums.CardAction;
import monopoly.rendering.Mesh;
import monopoly.rendering.RenderableObject;

public abstract class DrawableCard extends RenderableObject {
    protected final String text;
    private final CardAction action;
    private final int[] values;

    DrawableCard(String text, Mesh mesh, CardAction action, int[] values) {
        super(mesh);
        this.text = text;
        this.action = action;
        this.values = values;
    }

    public abstract void onDraw(Player player);
}
