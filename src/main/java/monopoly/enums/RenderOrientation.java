package monopoly.enums;

public enum RenderOrientation {
    NORTH(6,10),
    EAST(-10, 6),
    SOUTH(-6, -10),
    WEST(10, -6),
    NORTH_WEST(10, 10),
    NORTH_EAST(-10, 10),
    SOUTH_EAST(-10, -10),
    SOUTH_WEST(10, -10);


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
