package game.playingcard.model;

public enum CardsType {
    ROYAL_FLUSH(100), FLUSH_STRAIGHT(99);


    private final int value;

    CardsType(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
