package game.playingcard.comparator;

import static game.playingcard.model.CardsType.ROYAL_FLUSH;

import game.playingcard.model.Cards;
import game.playingcard.model.CardsType;
import java.util.List;

public class CardsComparator {

    private static final int EQUAL_VALUE = 0;
    private static final int BIGGER_VALUE = 1;
    private static final int SMALLER_VALUE = -1;

    public int compare(final Cards firstCards, final Cards secondCards) {
        firstCards.setCardsType(calculateCardsType(firstCards));
        secondCards.setCardsType(calculateCardsType(secondCards));

        int compareResult = firstCards.getCardsType().getValue() - (secondCards.getCardsType()).getValue();

        if (compareResult != EQUAL_VALUE) {
            return compareResult;
        }

        if (firstCards.getCardsType() == CardsType.FLUSH_STRAIGHT) {
            if(firstCards.isSmallestSequence() && secondCards.isSmallestSequence()){
                return EQUAL_VALUE;
            }
            if(firstCards.isSmallestSequence()){
                return SMALLER_VALUE;
            }
            return BIGGER_VALUE;
        }

        return compareCardsCharacters(firstCards, secondCards);

    }

    private int compareCardsCharacters(final Cards firstCards, final Cards secondCards) {
        int result = 0;
        List<Integer> firstCardsValues = firstCards.getValueList();
        List<Integer> secondCardsValues = secondCards.getValueList();

        for (int i = 0; i < Cards.CARD_SIZE; i++) {
            if (firstCardsValues.get(i).equals(secondCardsValues.get(i))) {
                continue;
            }
            result = firstCardsValues.get(i) - secondCardsValues.get(i);
            break;
        }
        return result;
    }

    private CardsType calculateCardsType(final Cards cards) {
        if (cards.isSameShape() && cards.isLargestSequence()) {
            return ROYAL_FLUSH;
        }

        if (cards.isSameShape() && cards.isSequence()) {
            return CardsType.FLUSH_STRAIGHT;
        }

        if(cards.contains4SameCharacters()){
            return CardsType.FOUR_OF_A_KIND;
        }

        return null;
    }
}
