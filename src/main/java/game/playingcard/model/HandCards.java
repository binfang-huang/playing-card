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
    private int equalNumber;


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
                            moveCardsWithGivenCharactersToEnd(List.of(key));
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
                            moveCardsWithGivenCharactersToEnd(List.of(key));
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

        if (isThreeOfKind()) {
            this.handCardsType = HandCardsType.THREE_OF_A_KIND;
            Map<String, Integer> characterCountMap = getCharacterCountMap();

            List<String> charactersToEnd = new ArrayList<>();
            characterCountMap.forEach((key, value) -> {
                        if (value != 3) {
                            charactersToEnd.add(key);
                        }
                    }
            );
            moveCardsWithGivenCharactersToEnd(charactersToEnd);
            return;
        }

        if (isTwoPairs()) {
            this.handCardsType = HandCardsType.TWO_PAIRS;

            Map<String, Integer> characterCountMap = getCharacterCountMap();

            characterCountMap.forEach((key, value) -> {
                        if (value != 2) {
                            moveCardsWithGivenCharactersToEnd(List.of(key));
                        }
                    }
            );
            return;
        }

        if (isPairs()) {
            this.handCardsType = HandCardsType.TWO_PAIRS;

            Map<String, Integer> characterCountMap = getCharacterCountMap();
            List<String> charactersToEnd = new ArrayList<>();
            characterCountMap.forEach((key, value) -> {
                        if (value != 2) {
                            charactersToEnd.add(key);
                        }
                    }
            );
            moveCardsWithGivenCharactersToEnd(charactersToEnd);
            return;
        }

        this.handCardsType = HandCardsType.HIGH_CARDS;
    }

    private boolean isPairs() {
        Map<String, Integer> characterCountMap = getCharacterCountMap();

        return characterCountMap.size() == 4 && characterCountMap.containsValue(2);
    }

    private boolean isTwoPairs() {
        Map<String, Integer> characterCountMap = getCharacterCountMap();

        return characterCountMap.size() == 3 && characterCountMap.containsValue(2) && characterCountMap.containsValue(1);
    }

    private boolean isThreeOfKind() {
        Map<String, Integer> characterCountMap = getCharacterCountMap();

        return characterCountMap.size() == 3 && characterCountMap.containsValue(3);
    }

    private void resortSequence() {
        moveCardsWithGivenCharactersToEnd(List.of("A"));
    }

    private boolean isStraight() {
        return !isSameShape() && isSequence();
    }

    private void moveCardsWithGivenCharactersToEnd(List<String> characters) {
        List<Card> cardsAtFront = new ArrayList<>();
        List<Card> cardsAtEnd = new ArrayList<>();
        for (Card card : sortedCards) {
            if (!characters.contains(card.getCharacter())) {
                cardsAtFront.add(card);
            } else {
                cardsAtEnd.add(card);
            }
        }
        cardsAtFront.addAll(cardsAtEnd);

        this.sortedCards = cardsAtFront;

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
        if (isSmallestSequence()) {
            return true;
        }
        for (int i = 0; i < sortedCards.size() - 1; i++) {
            if (sortedCards.get(i).compareTo(sortedCards.get(i + 1)) != 1) {
                return false;
            }
        }
        return true;
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
        equalNumber = 0;

        if (compareResult != 0) {
            return compareResult;
        }

        for (int i = 0; i < sortedCards.size(); i++) {
            Card firstCard = sortedCards.get(i);
            Card secondCard = handCards.sortedCards.get(i);
            if (firstCard.compareTo(secondCard) != equalNumber) {
                return firstCard.compareTo(secondCard);
            }
        }
        return 0;

    }

    String getCardsValueAsString() {
        return sortedCards.stream().map(it -> String.valueOf(it.getValue())).collect(Collectors.joining());
    }

}
