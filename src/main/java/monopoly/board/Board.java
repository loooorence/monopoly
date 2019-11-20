package monopoly.board;

import monopoly.Player;
import monopoly.board.tiles.TileGo;
import monopoly.items.PropertyCard;
import monopoly.rendering.Mesh;
import monopoly.rendering.RenderableObject;
import monopoly.util.CircularlyLinkedList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.util.Iterator;

public class Board extends RenderableObject {

    private final CircularlyLinkedList<Tile> tileList;

    public Board(Mesh mesh) {
        super(mesh);
        this.tileList = new CircularlyLinkedList<>();
        try {
            initBoard();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initBoard() throws Exception {
        //TODO: load property cards and tiles
        //resources/board.json
        JSONTokener tokener = new JSONTokener(Board.class.getResourceAsStream("/board.json"));
        JSONArray array = new JSONObject(tokener).getJSONArray("tiles");

        for (int i = 0; i < array.length(); i++) {
            JSONObject tileObject = array.getJSONObject(i);
            String className = TileGo.class.getPackage().getName() + "." + tileObject.get("type");
            Constructor c = Class.forName(className).getConstructors()[0];
            Tile newTile;
            if (c.getParameterCount() == 2) {
                newTile = (Tile) c.newInstance(tileObject.getString("title"), null);
            } else {
                if (className.contains("Property")) {
                    // create property card
                    newTile = (Tile) c.newInstance(tileObject.get("title"), null, null);
                } else {
                    int value = tileObject.getInt("extra");
                    newTile = (Tile) c.newInstance(tileObject.getString("title"), null, value);
                }
            }

            System.out.println(newTile.getTitle());
        }
    }

    public CircularlyLinkedList<Tile> getTileList() {
        return this.tileList;
    }

    public void updateBoard(Player currentPlayer) {

    }

}
