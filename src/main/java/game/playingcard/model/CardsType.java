package game.playingcard.model;

public enum CardsType {
    ROYAL_FLUSH(100), FLUSH_STRAIGHT(99), FOUR_OF_A_KIND(98);


    private final int value;

    CardsType(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
