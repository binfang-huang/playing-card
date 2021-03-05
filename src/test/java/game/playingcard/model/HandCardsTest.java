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
        Assertions.assertEquals(firstHandCards.getCardsValueAsString(), "1413121110");
        Assertions.assertEquals(secondHandCards.getCardsValueAsString(), "1413121110");
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
        Assertions.assertEquals(firstHandCards.getCardsValueAsString(), "543214");
        Assertions.assertEquals(secondHandCards.getCardsValueAsString(), "543214");
    }

    @Test
    void should_return_negative_value_if_two_hands_of_cards_are_FlushStraight_and_first_cards_characters_are_bigger() {
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

        Assertions.assertEquals(firstHandCards.getCardsValueAsString(), "543214");
        Assertions.assertEquals(secondHandCards.getCardsValueAsString(), "65432");
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
        Assertions.assertEquals(firstHandCards.getCardsValueAsString(), "1413121110");
        Assertions.assertEquals(secondHandCards.getCardsValueAsString(), "65432");
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
        Assertions.assertEquals(firstHandCards.getCardsValueAsString(), "1414141410");
        Assertions.assertEquals(secondHandCards.getCardsValueAsString(), "1313131310");
    }

    @Test
    void should_return_negative_value_if_two_hand_cards_are_FullHouse_and_first_hand_cards_characters_are_smaller() {
        HandCards firstHandCards = new HandCards(Arrays.asList(new Card("4", 4, CardShape.DIAMOND),
                new Card("4", 4, CardShape.SPADE),
                new Card("A", 14, CardShape.CLUB),
                new Card("4", 4, CardShape.DIAMOND),
                new Card("A", 14, CardShape.HEARTS)));

        HandCards secondHandCards = new HandCards(Arrays.asList(new Card("7", 7, CardShape.DIAMOND),
                new Card("7", 7, CardShape.SPADE),
                new Card("6", 6, CardShape.CLUB),
                new Card("7", 7, CardShape.DIAMOND),
                new Card("6", 6, CardShape.HEARTS)));


        int result = firstHandCards.compareTo(secondHandCards);

        Assertions.assertTrue(result < 0);
        Assertions.assertEquals(firstHandCards.getCardsValueAsString(), "4441414");
        Assertions.assertEquals(secondHandCards.getCardsValueAsString(), "77766");

        HandCards thirdHandCards = new HandCards(Arrays.asList(new Card("4", 4, CardShape.DIAMOND),
                new Card("4", 4, CardShape.SPADE),
                new Card("A", 14, CardShape.CLUB),
                new Card("4", 4, CardShape.DIAMOND),
                new Card("A", 14, CardShape.HEARTS)));

        HandCards fourthHandCards = new HandCards(Arrays.asList(new Card("4", 4, CardShape.DIAMOND),
                new Card("4", 4, CardShape.SPADE),
                new Card("4", 4, CardShape.CLUB),
                new Card("7", 7, CardShape.DIAMOND),
                new Card("7", 7, CardShape.HEARTS)));

        result = thirdHandCards.compareTo(secondHandCards);

        Assertions.assertTrue(result < 0);
        Assertions.assertEquals(thirdHandCards.getCardsValueAsString(), "4441414");
        Assertions.assertEquals(fourthHandCards.getCardsValueAsString(), "44477");
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
        Assertions.assertEquals(firstHandCards.getCardsValueAsString(), "543214");
        Assertions.assertEquals(secondHandCards.getCardsValueAsString(), "65432");


         firstHandCards = new HandCards(Arrays.asList(new Card("2", 2, CardShape.DIAMOND),
                 new Card("5", 5, CardShape.DIAMOND),
                 new Card("3", 3, CardShape.HEARTS),
                 new Card("4", 4, CardShape.DIAMOND),
                 new Card("6", 6, CardShape.DIAMOND)));

         secondHandCards = new HandCards(Arrays.asList(new Card("7", 7, CardShape.DIAMOND),
                new Card("5", 5, CardShape.DIAMOND),
                new Card("3", 3, CardShape.HEARTS),
                new Card("4", 4, CardShape.DIAMOND),
                new Card("6", 6, CardShape.DIAMOND)));

         result = firstHandCards.compareTo(secondHandCards);

        Assertions.assertTrue(result < 0);
        Assertions.assertEquals(secondHandCards.getCardsValueAsString(), "76543");
        Assertions.assertEquals(firstHandCards.getCardsValueAsString(), "65432");
    }

    @Test
    void should_return_negative_value_if_two_hand_cards_are_ThreeOfKind_and_first_hand_cards_characters_are_smaller() {
        HandCards firstHandCards = new HandCards(Arrays.asList(new Card("5", 5, CardShape.HEARTS),
                new Card("5", 5, CardShape.HEARTS),
                new Card("6", 6, CardShape.HEARTS),
                new Card("5", 5, CardShape.SPADE),
                new Card("8", 8, CardShape.HEARTS))
        );

        HandCards secondHandCards = new HandCards(Arrays.asList(new Card("5", 5, CardShape.HEARTS),
                new Card("5", 5, CardShape.HEARTS),
                new Card("9", 9, CardShape.HEARTS),
                new Card("5", 5, CardShape.SPADE),
                new Card("8", 8, CardShape.HEARTS)));

        int result = firstHandCards.compareTo(secondHandCards);

        Assertions.assertTrue(result < 0);
        Assertions.assertEquals(firstHandCards.getCardsValueAsString(), "55586");
        Assertions.assertEquals(secondHandCards.getCardsValueAsString(), "55598");
    }

    @Test
    void should_return_negative_value_if_two_hand_cards_are_TwoPairs_and_first_hand_cards_characters_are_smaller() {
        HandCards firstHandCards = new HandCards(Arrays.asList(new Card("5", 5, CardShape.HEARTS),
                new Card("5", 5, CardShape.HEARTS),
                new Card("6", 6, CardShape.HEARTS),
                new Card("6", 6, CardShape.SPADE),
                new Card("8", 8, CardShape.HEARTS)));

        HandCards secondHandCards = new HandCards(Arrays.asList(new Card("5", 5, CardShape.HEARTS),
                new Card("5", 5, CardShape.HEARTS),
                new Card("7", 7, CardShape.HEARTS),
                new Card("7", 7, CardShape.SPADE),
                new Card("8", 8, CardShape.HEARTS)));

        int result = firstHandCards.compareTo(secondHandCards);

        Assertions.assertTrue(result < 0);
        Assertions.assertEquals(firstHandCards.getCardsValueAsString(), "66558");
        Assertions.assertEquals(secondHandCards.getCardsValueAsString(), "77558");

        firstHandCards = new HandCards(Arrays.asList(new Card("5", 5, CardShape.HEARTS),
                new Card("5", 5, CardShape.HEARTS),
                new Card("6", 6, CardShape.HEARTS),
                new Card("6", 6, CardShape.SPADE),
                new Card("8", 8, CardShape.HEARTS)));

        secondHandCards = new HandCards(Arrays.asList(new Card("6", 6, CardShape.HEARTS),
                new Card("6", 6, CardShape.HEARTS),
                new Card("5", 5, CardShape.HEARTS),
                new Card("9", 9, CardShape.SPADE),
                new Card("5", 5, CardShape.HEARTS)));

         result = firstHandCards.compareTo(secondHandCards);

        Assertions.assertTrue(result < 0);
        Assertions.assertEquals(firstHandCards.getCardsValueAsString(), "66558");
        Assertions.assertEquals(secondHandCards.getCardsValueAsString(), "66559");
    }
}
