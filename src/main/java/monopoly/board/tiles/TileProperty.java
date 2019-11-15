package monopoly.board.tiles;

import monopoly.Player;
import monopoly.board.Tile;
import monopoly.items.PropertyCard;
import monopoly.rendering.Mesh;

public class TileProperty extends Tile {

    private final PropertyCard card;
    private boolean mortgaged = false;
    private int houseCount;
    private boolean hasHotel;
    private final int price;

    protected TileProperty(String title, Mesh mesh, PropertyCard card) {
        super(title, mesh);
        this.card = card;
        this.price = -1;
        this.houseCount = 0;
        this.hasHotel = false;
    }

    @Override
    public void onLanded(Player player) {

    }

    @Override
    public void renderMarker() {

    }
}
