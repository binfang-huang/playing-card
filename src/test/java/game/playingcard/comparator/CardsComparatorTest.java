package game.playingcard.comparator;

import game.playingcard.model.Card;
import game.playingcard.model.CardShape;
import game.playingcard.model.Cards;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CardsComparatorTest {
    private CardsComparator cardsComparator = new CardsComparator();

    @Test
    void should_return_0_if_two_hands_of_cards_are_Royal_Flush() {

        Cards firstCards = new Cards(Arrays.asList(new Card("A", 14, CardShape.HONG_TAO),
                new Card("K", 13, CardShape.HONG_TAO),
                new Card("Q", 12, CardShape.HONG_TAO),
                new Card("J", 11, CardShape.HONG_TAO),
                new Card("T", 10, CardShape.HONG_TAO)));

        Cards secondCards = new Cards(Arrays.asList(new Card("A", 14, CardShape.HEI_TAO),
                new Card("Q", 12, CardShape.HEI_TAO),
                new Card("T", 10, CardShape.HEI_TAO),
                new Card("K", 13, CardShape.HEI_TAO),
                new Card("J", 11, CardShape.HEI_TAO)));

        int result = cardsComparator.compare(firstCards,secondCards);

        Assertions.assertEquals(0, result);
    }



    @Test
    void should_return_0_if_two_hands_of_cards_are_Flush_Straight_and_Characters_are_the_same() {
        Cards firstCards = new Cards(Arrays.asList(new Card("5", 5, CardShape.HONG_TAO),
                new Card("4", 4, CardShape.HONG_TAO),
                new Card("3", 3, CardShape.HONG_TAO),
                new Card("2", 2, CardShape.HONG_TAO),
                new Card("A", 14, CardShape.HONG_TAO)));

        Cards secondCards = new Cards(Arrays.asList(new Card("2", 2, CardShape.HEI_TAO),
                new Card("3", 3, CardShape.HEI_TAO),
                new Card("4", 4, CardShape.HEI_TAO),
                new Card("5", 5, CardShape.HEI_TAO),
                new Card("A", 14, CardShape.HEI_TAO)));

        int result = cardsComparator.compare(firstCards,secondCards);

        Assertions.assertEquals(0, result);
    }

    @Test
    void should_return_negtive_value_if_two_hands_of_cards_are_Flush_Straight_and_first_cards_characters_are_bigger() {
        Cards firstCards = new Cards(Arrays.asList(new Card("5", 5, CardShape.HONG_TAO),
                new Card("4", 4, CardShape.HONG_TAO),
                new Card("3", 3, CardShape.HONG_TAO),
                new Card("2", 2, CardShape.HONG_TAO),
                new Card("A", 14, CardShape.HONG_TAO)));

        Cards secondCards = new Cards(Arrays.asList(new Card("2", 2, CardShape.HEI_TAO),
                new Card("3", 3, CardShape.HEI_TAO),
                new Card("4", 4, CardShape.HEI_TAO),
                new Card("5", 5, CardShape.HEI_TAO),
                new Card("6", 6, CardShape.HEI_TAO)));

        int result = cardsComparator.compare(firstCards,secondCards);

        Assertions.assertTrue(result<0);
    }

    @Test
    void should_return_positive_value_if_first_cards_type_is_bigger_than_second_cards_type() {
        Cards firstCards = new Cards(Arrays.asList(new Card("A", 14, CardShape.HONG_TAO),
                new Card("K", 13, CardShape.HONG_TAO),
                new Card("Q", 12, CardShape.HONG_TAO),
                new Card("J", 11, CardShape.HONG_TAO),
                new Card("T", 10, CardShape.HONG_TAO)));

        Cards secondCards = new Cards(Arrays.asList(new Card("2", 2, CardShape.HEI_TAO),
                new Card("3", 3, CardShape.HEI_TAO),
                new Card("4", 4, CardShape.HEI_TAO),
                new Card("5", 5, CardShape.HEI_TAO),
                new Card("6", 6, CardShape.HEI_TAO)));

        int result = cardsComparator.compare(firstCards,secondCards);

        Assertions.assertTrue(result>0);
    }

    @Test
    void should_return_active_if_two_hand_cards_are_4_of_kinds_and_first_hand_cards_characters_are_bigger() {
        Cards firstCards = new Cards(Arrays.asList(new Card("A", 14, CardShape.HONG_TAO),
                new Card("A", 14, CardShape.HEI_TAO),
                new Card("A", 14, CardShape.FANG_PIAN),
                new Card("A", 14, CardShape.MEI_HUA),
                new Card("T", 10, CardShape.HONG_TAO)));

        Cards secondCards = new Cards(Arrays.asList(new Card("K", 13, CardShape.HONG_TAO),
                new Card("K", 13, CardShape.HEI_TAO),
                new Card("K", 13, CardShape.FANG_PIAN),
                new Card("K", 13, CardShape.MEI_HUA),
                new Card("T", 10, CardShape.HONG_TAO)));

        int result = cardsComparator.compare(firstCards,secondCards);

        Assertions.assertTrue(result>0);
    }
}