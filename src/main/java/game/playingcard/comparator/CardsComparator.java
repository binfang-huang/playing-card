package game.playingcard.comparator;

import static game.playingcard.model.CardsType.ROYAL_FLUSH;

import game.playingcard.model.Cards;
import game.playingcard.model.CardsType;
import java.util.Arrays;

public class CardsComparator {
    public int compare(final Cards firstCards, final Cards secondCards) {
        firstCards.setCardsType(calculateCardsType(firstCards));
        secondCards.setCardsType(calculateCardsType(secondCards));

        int compareResult = firstCards.getCardsType().getValue() - (secondCards.getCardsType()).getValue();

        if (compareResult != 0) {
            return compareResult;
        }

        if (firstCards.getCardsType() == ROYAL_FLUSH) {
            return 0;
        }

        if(firstCards.getCardsType() == CardsType.FLUSH_STRAIGHT) {
            return firstCards.getLargestCardValue() - secondCards.getLargestCardValue();
        }

        return compareResult;

    }

    private CardsType calculateCardsType(final Cards cards) {
        if (cards.isSameShape() && cards.containsAllCharacters(Arrays.asList("A", "K", "Q", "J", "T"))) {
            return ROYAL_FLUSH;
        }

        if (cards.isSameShape() && cards.isSequenceLine()) {
            return CardsType.FLUSH_STRAIGHT;
        }
        return null;
    }
}
