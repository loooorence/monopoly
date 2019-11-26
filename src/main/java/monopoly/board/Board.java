package monopoly.board;

import monopoly.Player;
import monopoly.board.tiles.TileGo;
import monopoly.enums.PropertyType;
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
import java.util.ArrayList;

public class Board extends RenderableObject {

    private final CircularlyLinkedList<Tile> tileList;
    public ArrayList<PropertyCard> cards; //ArrayList for propertyCards
    public Board(Mesh mesh) {
        super(mesh);
        this.tileList = new CircularlyLinkedList<>();
        this.cards = new ArrayList<PropertyCard>();
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
                    JSONObject propertyObject = tileObject.getJSONObject("property");
                    //Check if TileProperty uses rents or not
                    int[] costs; //array for property values
                    //Check if it is a property
                    if(propertyObject.has("rents") && propertyObject.has("mortgage")) {
                        costs = new int[propertyObject.getJSONArray("rents").length() + 3];
                        //Obtain costs from json file
                        for(int k = 0; k < costs.length-3; k++){
                            costs[k] = propertyObject.getJSONArray("rents").getJSONObject(k).getInt("rent");
                        }
                        //Manually add mortgage/house cost/hotel cost
                        costs[6] = propertyObject.getInt("mortgage");
                        costs[7] = propertyObject.getInt("cost_house");
                        costs[8] = propertyObject.getInt("cost_hotel");
                        for (int k = 0; k < costs.length; k++){
                            System.out.print(costs[k] + " ");
                        }
                        System.out.println("");
                    }
                    //Check if it's a railroad
                    else if(propertyObject.has("rents")){
                        costs = new int[propertyObject.getJSONArray("rents").length()];
                        //Add in costs read in from json object
                        for(int k = 0; k < costs.length; k++){
                            costs[k] = propertyObject.getJSONArray("rents").getJSONObject(k).getInt("rent");
                        }
                        //Printing for test purposes
                        for (int k = 0; k < costs.length; k++){
                            System.out.print(costs[k] + " ");
                        }
                        System.out.println("");
                    }
                    //If no rents - utility, alter int[]
                    else{
                        costs = new int[2];
                        costs[0] = (propertyObject.getInt("mult1"));
                        costs[1] = (propertyObject.getInt("mult2"));
                        for (int k = 0; k < costs.length; k++){
                            System.out.print(costs[k] + " ");
                        }
                        System.out.println("");
                    }
                    //Creation of propertyCard object to be attached to Tile
                    PropertyCard card = new PropertyCard(tileObject.getString("title"), PropertyType.values()[propertyObject.getInt("group")], costs, null);
                    //Add PropertyCard to ArrayList
                    cards.add(card);
                    //Creation of Tile
                    newTile = (Tile) c.newInstance(tileObject.get("title"), null, card);
                } else {
                    int value = tileObject.getInt("extra");
                    newTile = (Tile) c.newInstance(tileObject.getString("title"), null, value);
                }
            }
        }
    }

    public CircularlyLinkedList<Tile> getTileList() {
        return this.tileList;
    }

    public void updateBoard(Player currentPlayer) {

    }

}
