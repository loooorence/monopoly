package monopoly.board;

import monopoly.Player;
import monopoly.enums.RenderOrientation;
import monopoly.rendering.IRenderable;
import monopoly.util.Stack;

public abstract class Tile implements IRenderable {

    private String title;
    private String texture;
    private RenderOrientation renderOrientation;

    protected Tile(String title, String texture) {
        this.title = title;
        this.texture = texture;
    }
    
    public abstract void onLanded(Player player);

    public abstract void renderMarker();
}
