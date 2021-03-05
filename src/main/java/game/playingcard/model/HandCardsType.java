package game.playingcard.model;

public enum HandCardsType {
    TWO_PAIRS(95),
    THREE_OF_A_KIND(96),
    STRAIGHT(96),
    FULL_HOUSE(97),
    FOUR_OF_A_KIND(98),
    FLUSH_STRAIGHT(99),
    ROYAL_FLUSH(100);


    private final int value;

    HandCardsType(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
