package game.playingcard.model;

import game.playingcard.exception.InvalidCardsException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Cards {
    public static final int CARD_SIZE = 5;
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
        List<String> charactersInCards = cards.stream().map(Card::getCharacter).collect(Collectors.toList());
        for (String character : characters) {
            if (!charactersInCards.contains(character)) {
                return false;
            }
        }
        return true;
    }


    public boolean isSequence() {
        int largestCardValue = cards.get(0).getValue();
        int smallestCardValue = cards.get(cards.size() - 1).getValue();
        return largestCardValue - smallestCardValue == CARD_SIZE - 1 ||
                isSmallestSequence();
    }

    public boolean isSmallestSequence() {
        return containsAllCharacters(List.of("2","3","4","5","A"));
    }


    public List<Integer> getValueList() {
        return cards.stream().map(Card::getValue).collect(Collectors.toList());
    }

    public boolean isLargestSequence() {
        return containsAllCharacters(List.of("A", "K", "Q", "J", "T"));
    }

    public boolean contains4SameCharacters() {
        Map<String, Integer> characterCountMap = new HashMap<>();
        for (Card card : cards) {
            if (characterCountMap.containsKey(card.getCharacter())) {
                characterCountMap.put(card.getCharacter(), characterCountMap.get(card.getCharacter()) + 1);
            } else {
                characterCountMap.put(card.getCharacter(), 1);
            }
        }

        return characterCountMap.containsValue(4);
    }
}
