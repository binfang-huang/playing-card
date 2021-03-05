package game.playingcard.model;

import game.playingcard.exception.InvalidCardsException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {
    private static final int CARD_SIZE = 5;
    private List<Card> cards;
    private CardsType cardsType;


    public Cards(final List<Card> cards) {
        if (cards == null || cards.isEmpty() || cards.size() != CARD_SIZE) {
            throw new InvalidCardsException("cards is invalid");
        }
        this.cards = cards;
        cards.sort(Comparator.comparing(Card::getValue, Comparator.reverseOrder()));
    }

    public CardsType getCardsType() {
        return cardsType;
    }

    public void setCardsType(final CardsType cardsType) {
        this.cardsType = cardsType;
    }

    public boolean isSameShape() {
        return cards.stream().map(Card::getShape).distinct().count() == 1;
    }

    public boolean containsAllCharacters(List<String> characters) {
        List<String> charactersInCards = cards.stream().map(it -> it.getCharacter()).collect(Collectors.toList());
        for (String character : characters) {
            if (!charactersInCards.contains(character)) {
                return false;
            }
        }
        return true;
    }


    public boolean isSequenceLine() {
        int largestCardValue = cards.get(0).getValue();
        int smallestCardValue = cards.get(cards.size() - 1).getValue();
        int secondSmallestCardValue = cards.get(cards.size() - 2).getValue();
        return largestCardValue - smallestCardValue == CARD_SIZE - 1 ||
                (largestCardValue == 13 && secondSmallestCardValue == 10 && smallestCardValue == 1);
    }

    public int getLargestCardValue() {
        return cards.get(0).getValue();
    }
}
