package game.playingcard.model;

public class Card {
     private String character;
     private int  value;
     private CardShape shape;


    public Card(final  String character, final int value, final CardShape shipType) {
        this.character = character;
        this.value = value;
        this.shape = shipType;
    }

    public String getCharacter() {
        return character;
    }

    public int getValue() {
        return value;
    }

    public CardShape getShape() {
        return shape;
    }
}
