package game.playingcard.model;

import static game.playingcard.model.HandCardsType.ROYAL_FLUSH;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HandCards implements Comparable<HandCards> {
    private List<Card> sortedCards;
    private HandCardsType handCardsType;


    public HandCards(final List<Card> sortedCards) {
        this.sortedCards = sortedCards;
        this.sortedCards.sort(Comparator.comparing(Card::getValue, Comparator.reverseOrder()));
        calculateCardsTypeAndReorder();
    }

    private void calculateCardsTypeAndReorder() {
        if (isRoyalFlush()) {
            this.handCardsType = ROYAL_FLUSH;
            return;
        }

        if (isFlushStraight()) {
            this.handCardsType = HandCardsType.FLUSH_STRAIGHT;

            resortSequence();
            return;

        }

        if (isFourOfKind()) {
            this.handCardsType = HandCardsType.FOUR_OF_A_KIND;
            Map<String, Integer> characterCountMap = getCharacterCountMap();

            characterCountMap.forEach((key, value) -> {
                        if (value != 4) {
                            sortedCards = moveCardsWithGivenCharacterToEnd(key);
                        }
                    }
            );

            return;
        }

        if (isFullHouse()) {
            this.handCardsType = HandCardsType.FULL_HOUSE;
            Map<String, Integer> characterCountMap = getCharacterCountMap();
            characterCountMap.forEach((key, value) -> {
                        if (value == 2) {
                            sortedCards = moveCardsWithGivenCharacterToEnd(key);
                        }
                    }
            );
            return;
        }
        if (isStraight()) {
            this.handCardsType = HandCardsType.STRAIGHT;

            resortSequence();
            return;
        }
    }

    private void resortSequence() {
        if (sortedCards.contains("A")) {
            sortedCards = moveCardsWithGivenCharacterToEnd("A");
        }
    }

    private boolean isStraight() {
        return !isSameShape() && isSequence();
    }

    private List<Card> moveCardsWithGivenCharacterToEnd(final String character) {
        List<Card> reorderedCards = new ArrayList<>();
        for (Card card : sortedCards) {
            if (!card.getCharacter().equals(character)) {
                reorderedCards.add(0, card);
            } else {
                reorderedCards.add(reorderedCards.size(), card);
            }
        }
        return reorderedCards;
    }


    private boolean isSameShape() {
        return sortedCards.stream().map(Card::getShape).distinct().count() == 1;
    }

    private boolean containsAllCharacters(List<String> characters) {
        List<String> charactersInCards = sortedCards.stream().map(Card::getCharacter).collect(Collectors.toList());
        for (String character : characters) {
            if (!charactersInCards.contains(character)) {
                return false;
            }
        }
        return true;
    }

    private boolean isSequence() {
        int largestCardValue = sortedCards.get(0).getValue();
        int smallestCardValue = sortedCards.get(sortedCards.size() - 1).getValue();
        return largestCardValue - smallestCardValue == sortedCards.size() - 1 ||
                isSmallestSequence();
    }

    private boolean isSmallestSequence() {
        return containsAllCharacters(List.of("2", "3", "4", "5", "A"));
    }


    private boolean isLargestSequence() {
        return containsAllCharacters(List.of("A", "K", "Q", "J", "T"));
    }

    private boolean isFourOfKind() {
        Map<String, Integer> characterCountMap = getCharacterCountMap();

        return characterCountMap.containsValue(4);
    }

    private Map<String, Integer> getCharacterCountMap() {
        Map<String, Integer> characterCountMap = new HashMap<>();
        for (Card card : sortedCards) {
            if (characterCountMap.containsKey(card.getCharacter())) {
                characterCountMap.put(card.getCharacter(), characterCountMap.get(card.getCharacter()) + 1);
            } else {
                characterCountMap.put(card.getCharacter(), 1);
            }
        }
        return characterCountMap;
    }

    private boolean isFullHouse() {
        Map<String, Integer> characterCountMap = getCharacterCountMap();

        return characterCountMap.containsValue(3) && characterCountMap.containsValue(2);
    }

    private boolean isRoyalFlush() {
        return isSameShape() && isLargestSequence();
    }

    private boolean isFlushStraight() {
        return isSameShape() && isSequence();
    }

    private HandCardsType getHandCardsType() {
        return handCardsType;
    }


    @Override
    public int compareTo(final HandCards handCards) {
        int compareResult = this.handCardsType.getValue() - handCards.getHandCardsType().getValue();

        if (compareResult != 0) {
            return compareResult;
        }

        return this.getCardsValueAsString().compareTo(handCards.getCardsValueAsString());

    }

    private String getCardsValueAsString() {
        return sortedCards.stream().map(it -> String.valueOf(it.getValue())).collect(Collectors.joining());
    }
}
