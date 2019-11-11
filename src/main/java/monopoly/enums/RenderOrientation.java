package monopoly.enums;

public enum RenderOrientation {
    NORTH(6,10),
    EAST(-10, 6),
    SOUTH(-6, -10),
    WEST(10, -6),
    CORNER_SE(10, 10),
    CORNER_SW(-10, 10),
    CORNER_NW(-10, -10),
    CORNER_NE(10, -10);


    private final int ratioX;
    private final int ratioY;
    RenderOrientation(int ratioX, int ratioY) {
        this.ratioX = ratioX;
        this.ratioY = ratioY;
    }

    public int getRatioX() {
        return this.ratioX;
    }

    public int getRatioY() {
        return this.ratioY;
    }
}
