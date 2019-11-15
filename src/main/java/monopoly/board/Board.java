package monopoly.board;

import monopoly.Player;
import monopoly.rendering.Mesh;
import monopoly.rendering.RenderableObject;
import monopoly.util.CircularlyLinkedList;

public class Board extends RenderableObject {

    private final CircularlyLinkedList<Tile> tileList;

    public Board(Mesh mesh) {
        super(mesh);
        this.tileList = new CircularlyLinkedList<>();
        initBoard();
    }

    private void initBoard() {
        //TODO: load property cards and tiles
        //resources/board.json
    }

    public CircularlyLinkedList<Tile> getTileList() {
        return this.tileList;
    }

    public void updateBoard(Player currentPlayer) {

    }

}
