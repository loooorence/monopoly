package monopoly.board;

import monopoly.Player;
import monopoly.rendering.IRenderable;
import monopoly.util.CircularlyLinkedList;

public class Board implements IRenderable{

    private final CircularlyLinkedList<Tile> tileList;

    public Board() {
        this.tileList = new CircularlyLinkedList();
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
