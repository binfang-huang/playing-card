package game.playingcard.comparator;

import static game.playingcard.model.CardsType.ROYAL_FLUSH;

import game.playingcard.model.Cards;
import game.playingcard.model.CardsType;
import java.util.Arrays;
import java.util.List;

public class CardsComparator {

    public int compare(final Cards firstCards, final Cards secondCards) {
        firstCards.setCardsType(calculateCardsType(firstCards));
        secondCards.setCardsType(calculateCardsType(secondCards));

        int compareResult = firstCards.getCardsType().getValue() - (secondCards.getCardsType()).getValue();

        if (compareResult != 0) {
            return compareResult;
        }

        List<Integer> firstCardsValues = firstCards.getValueList();
        List<Integer> secondCardsValues = secondCards.getValueList();

        for (int i = 0; i < Cards.CARD_SIZE; i++) {
            if (firstCardsValues.get(i) == secondCardsValues.get(i)) {
                continue;
            }
            compareResult = firstCardsValues.get(i) - secondCardsValues.get(i);
            break;
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
