package game.playingcard.model;

import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HandCardsTest {

    @Test
    void should_return_0_if_two_hands_of_cards_are_RoyalFlush() {

        HandCards firstHandCards = new HandCards(Arrays.asList(new Card("A", 14, CardShape.HONG_TAO),
                new Card("J", 11, CardShape.HONG_TAO),
                new Card("K", 13, CardShape.HONG_TAO),
                new Card("Q", 12, CardShape.HONG_TAO),
                new Card("T", 10, CardShape.HONG_TAO)));

        HandCards secondHandCards = new HandCards(Arrays.asList(new Card("A", 14, CardShape.HEI_TAO),
                new Card("T", 10, CardShape.HEI_TAO),
                new Card("K", 13, CardShape.HEI_TAO),
                new Card("Q", 12, CardShape.HEI_TAO),
                new Card("J", 11, CardShape.HEI_TAO)));

        int result = firstHandCards.compareTo(secondHandCards);

        Assertions.assertEquals(0, result);
    }


    @Test
    void should_return_0_if_two_hands_of_cards_are_FlushStraight_and_Characters_are_the_same() {
        HandCards firstHandCards = new HandCards(Arrays.asList(new Card("5", 5, CardShape.HONG_TAO),
                new Card("4", 4, CardShape.HONG_TAO),
                new Card("3", 3, CardShape.HONG_TAO),
                new Card("2", 2, CardShape.HONG_TAO),
                new Card("A", 14, CardShape.HONG_TAO)));

        HandCards secondHandCards = new HandCards(Arrays.asList(new Card("2", 2, CardShape.HEI_TAO),
                new Card("3", 3, CardShape.HEI_TAO),
                new Card("4", 4, CardShape.HEI_TAO),
                new Card("5", 5, CardShape.HEI_TAO),
                new Card("A", 14, CardShape.HEI_TAO)));

        int result = firstHandCards.compareTo(secondHandCards);

        Assertions.assertEquals(0, result);
    }

    @Test
    void should_return_negtive_value_if_two_hands_of_cards_are_FlushStraight_and_first_cards_characters_are_bigger() {
        HandCards firstHandCards = new HandCards(Arrays.asList(new Card("5", 5, CardShape.HONG_TAO),
                new Card("3", 3, CardShape.HONG_TAO),
                new Card("2", 2, CardShape.HONG_TAO),
                new Card("4", 4, CardShape.HONG_TAO),
                new Card("A", 14, CardShape.HONG_TAO)));

        HandCards secondHandCards = new HandCards(Arrays.asList(new Card("2", 2, CardShape.HEI_TAO),
                new Card("5", 5, CardShape.HEI_TAO),
                new Card("3", 3, CardShape.HEI_TAO),
                new Card("4", 4, CardShape.HEI_TAO),
                new Card("6", 6, CardShape.HEI_TAO)));

        int result = firstHandCards.compareTo(secondHandCards);

        Assertions.assertTrue(result < 0);
    }

    @Test
    void should_return_positive_value_if_first_cards_type_is_bigger_than_second_cards_type() {
        HandCards firstHandCards = new HandCards(Arrays.asList(new Card("A", 14, CardShape.HONG_TAO),
                new Card("K", 13, CardShape.HONG_TAO),
                new Card("J", 11, CardShape.HONG_TAO),
                new Card("Q", 12, CardShape.HONG_TAO),
                new Card("T", 10, CardShape.HONG_TAO)));

        HandCards secondHandCards = new HandCards(Arrays.asList(new Card("2", 2, CardShape.HEI_TAO),
                new Card("3", 3, CardShape.HEI_TAO),
                new Card("5", 5, CardShape.HEI_TAO),
                new Card("4", 4, CardShape.HEI_TAO),
                new Card("6", 6, CardShape.HEI_TAO)));

        int result = firstHandCards.compareTo(secondHandCards);

        Assertions.assertTrue(result > 0);
    }

    @Test
    void should_return_active_if_two_hand_cards_are_FourOfKind_and_first_hand_cards_characters_are_bigger() {
        HandCards firstHandCards = new HandCards(Arrays.asList(new Card("A", 14, CardShape.HONG_TAO),
                new Card("A", 14, CardShape.HEI_TAO),
                new Card("A", 14, CardShape.FANG_PIAN),
                new Card("T", 10, CardShape.HONG_TAO),
                new Card("A", 14, CardShape.MEI_HUA)));

        HandCards secondHandCards = new HandCards(Arrays.asList(new Card("K", 13, CardShape.HONG_TAO),
                new Card("K", 13, CardShape.HEI_TAO),
                new Card("K", 13, CardShape.FANG_PIAN),
                new Card("T", 10, CardShape.HONG_TAO),
                new Card("K", 13, CardShape.MEI_HUA)
        ));

        int result = firstHandCards.compareTo(secondHandCards);

        Assertions.assertTrue(result > 0);
    }

    @Test
    void should_return_negative_value_if_two_hand_cards_are_FullHouse_and_first_hand_cards_characters_are_smaller() {
        HandCards firstHandCards = new HandCards(Arrays.asList(new Card("4", 4, CardShape.FANG_PIAN),
                new Card("4", 4, CardShape.HEI_TAO),
                new Card("9", 9, CardShape.MEI_HUA),
                new Card("4", 4, CardShape.FANG_PIAN),
                new Card("9", 9, CardShape.HONG_TAO)));

        HandCards secondHandCards = new HandCards(Arrays.asList(new Card("7", 7, CardShape.FANG_PIAN),
                new Card("7", 7, CardShape.HEI_TAO),
                new Card("6", 6, CardShape.MEI_HUA),
                new Card("7", 7, CardShape.FANG_PIAN),
                new Card("6", 6, CardShape.HONG_TAO)));

        int result = firstHandCards.compareTo(secondHandCards);

        Assertions.assertTrue(result < 0);
    }

    @Test
    void should_return_negative_value_if_two_hand_cards_are_Straight_and_first_hand_cards_characters_are_smaller() {
        HandCards firstHandCards = new HandCards(Arrays.asList(new Card("5", 5, CardShape.HONG_TAO),
                new Card("3", 3, CardShape.HONG_TAO),
                new Card("2", 2, CardShape.HEI_TAO),
                new Card("4", 4, CardShape.HONG_TAO),
                new Card("A", 14, CardShape.HONG_TAO)));

        HandCards secondHandCards = new HandCards(Arrays.asList(new Card("2", 2, CardShape.FANG_PIAN),
                new Card("5", 5, CardShape.FANG_PIAN),
                new Card("3", 3, CardShape.HONG_TAO),
                new Card("4", 4, CardShape.FANG_PIAN),
                new Card("6", 6, CardShape.FANG_PIAN)));

        int result = firstHandCards.compareTo(secondHandCards);

        Assertions.assertTrue(result < 0);
    }
}
