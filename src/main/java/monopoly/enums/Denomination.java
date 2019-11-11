package monopoly.enums;


public enum Denomination {
    ONE (1),
    FIVE (5),
    TEN (10),
    TWENTY (20),
    FIFTY (50),
    ONE_HUNDRED (100),
    FIVE_HUNDRED (500);

    private final int value;

    Denomination(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
