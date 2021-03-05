package game.playingcard.model;

import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HandCardsTest {

    @Test
    void should_return_0_if_two_hands_of_cards_are_RoyalFlush() {

        HandCards firstHandCards = new HandCards(Arrays.asList(new Card("A", 14, CardShape.HEARTS),
                new Card("J", 11, CardShape.HEARTS),
                new Card("K", 13, CardShape.HEARTS),
                new Card("Q", 12, CardShape.HEARTS),
                new Card("T", 10, CardShape.HEARTS)));

        HandCards secondHandCards = new HandCards(Arrays.asList(new Card("A", 14, CardShape.SPADE),
                new Card("T", 10, CardShape.SPADE),
                new Card("K", 13, CardShape.SPADE),
                new Card("Q", 12, CardShape.SPADE),
                new Card("J", 11, CardShape.SPADE)));

        int result = firstHandCards.compareTo(secondHandCards);

        Assertions.assertEquals(0, result);
    }


    @Test
    void should_return_0_if_two_hands_of_cards_are_FlushStraight_and_Characters_are_the_same() {
        HandCards firstHandCards = new HandCards(Arrays.asList(new Card("5", 5, CardShape.HEARTS),
                new Card("4", 4, CardShape.HEARTS),
                new Card("3", 3, CardShape.HEARTS),
                new Card("2", 2, CardShape.HEARTS),
                new Card("A", 14, CardShape.HEARTS)));

        HandCards secondHandCards = new HandCards(Arrays.asList(new Card("2", 2, CardShape.SPADE),
                new Card("3", 3, CardShape.SPADE),
                new Card("4", 4, CardShape.SPADE),
                new Card("5", 5, CardShape.SPADE),
                new Card("A", 14, CardShape.SPADE)));

        int result = firstHandCards.compareTo(secondHandCards);

        Assertions.assertEquals(0, result);
    }

    @Test
    void should_return_negtive_value_if_two_hands_of_cards_are_FlushStraight_and_first_cards_characters_are_bigger() {
        HandCards firstHandCards = new HandCards(Arrays.asList(new Card("5", 5, CardShape.HEARTS),
                new Card("3", 3, CardShape.HEARTS),
                new Card("2", 2, CardShape.HEARTS),
                new Card("4", 4, CardShape.HEARTS),
                new Card("A", 14, CardShape.HEARTS)));

        HandCards secondHandCards = new HandCards(Arrays.asList(new Card("2", 2, CardShape.SPADE),
                new Card("5", 5, CardShape.SPADE),
                new Card("3", 3, CardShape.SPADE),
                new Card("4", 4, CardShape.SPADE),
                new Card("6", 6, CardShape.SPADE)));

        int result = firstHandCards.compareTo(secondHandCards);

        Assertions.assertTrue(result < 0);
    }

    @Test
    void should_return_positive_value_if_first_cards_type_is_bigger_than_second_cards_type() {
        HandCards firstHandCards = new HandCards(Arrays.asList(new Card("A", 14, CardShape.HEARTS),
                new Card("K", 13, CardShape.HEARTS),
                new Card("J", 11, CardShape.HEARTS),
                new Card("Q", 12, CardShape.HEARTS),
                new Card("T", 10, CardShape.HEARTS)));

        HandCards secondHandCards = new HandCards(Arrays.asList(new Card("2", 2, CardShape.SPADE),
                new Card("3", 3, CardShape.SPADE),
                new Card("5", 5, CardShape.SPADE),
                new Card("4", 4, CardShape.SPADE),
                new Card("6", 6, CardShape.SPADE)));

        int result = firstHandCards.compareTo(secondHandCards);

        Assertions.assertTrue(result > 0);
    }

    @Test
    void should_return_active_if_two_hand_cards_are_FourOfKind_and_first_hand_cards_characters_are_bigger() {
        HandCards firstHandCards = new HandCards(Arrays.asList(new Card("A", 14, CardShape.HEARTS),
                new Card("A", 14, CardShape.SPADE),
                new Card("A", 14, CardShape.DIAMOND),
                new Card("T", 10, CardShape.HEARTS),
                new Card("A", 14, CardShape.CLUB)));

        HandCards secondHandCards = new HandCards(Arrays.asList(new Card("K", 13, CardShape.HEARTS),
                new Card("K", 13, CardShape.SPADE),
                new Card("K", 13, CardShape.DIAMOND),
                new Card("T", 10, CardShape.HEARTS),
                new Card("K", 13, CardShape.CLUB)
        ));

        int result = firstHandCards.compareTo(secondHandCards);

        Assertions.assertTrue(result > 0);
    }

    @Test
    void should_return_negative_value_if_two_hand_cards_are_FullHouse_and_first_hand_cards_characters_are_smaller() {
        HandCards firstHandCards = new HandCards(Arrays.asList(new Card("4", 4, CardShape.DIAMOND),
                new Card("4", 4, CardShape.SPADE),
                new Card("9", 9, CardShape.CLUB),
                new Card("4", 4, CardShape.DIAMOND),
                new Card("9", 9, CardShape.HEARTS)));

        HandCards secondHandCards = new HandCards(Arrays.asList(new Card("7", 7, CardShape.DIAMOND),
                new Card("7", 7, CardShape.SPADE),
                new Card("6", 6, CardShape.CLUB),
                new Card("7", 7, CardShape.DIAMOND),
                new Card("6", 6, CardShape.HEARTS)));

        int result = firstHandCards.compareTo(secondHandCards);

        Assertions.assertTrue(result < 0);
    }

    @Test
    void should_return_negative_value_if_two_hand_cards_are_Straight_and_first_hand_cards_characters_are_smaller() {
        HandCards firstHandCards = new HandCards(Arrays.asList(new Card("5", 5, CardShape.HEARTS),
                new Card("3", 3, CardShape.HEARTS),
                new Card("2", 2, CardShape.SPADE),
                new Card("4", 4, CardShape.HEARTS),
                new Card("A", 14, CardShape.HEARTS)));

        HandCards secondHandCards = new HandCards(Arrays.asList(new Card("2", 2, CardShape.DIAMOND),
                new Card("5", 5, CardShape.DIAMOND),
                new Card("3", 3, CardShape.HEARTS),
                new Card("4", 4, CardShape.DIAMOND),
                new Card("6", 6, CardShape.DIAMOND)));

        int result = firstHandCards.compareTo(secondHandCards);

        Assertions.assertTrue(result < 0);
    }
}
