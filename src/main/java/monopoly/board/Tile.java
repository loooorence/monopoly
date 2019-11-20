package monopoly.board;

import monopoly.Player;
import monopoly.enums.RenderOrientation;
import monopoly.rendering.Mesh;
import monopoly.rendering.RenderableObject;

public abstract class Tile extends RenderableObject {

    private String title;
    private RenderOrientation renderOrientation;

    protected Tile(String title, Mesh mesh) {
        super(mesh);
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }
    
    public abstract void onLanded(Player player);

    public abstract void renderMarker();
}
