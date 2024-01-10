import org.junit.jupiter.api.*;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class HandsTest {

    private Hands hands;
    private Card trumpCard = new Card("Hearts", new Rank("Ace", 14));
    @BeforeEach
    void setUp() {
        // create deck
        Deck deck = new Deck();

        // shuffle deck
        deck.shuffleDeck();

        //deal deck
        hands = deck.dealDeck();
    }

    @Test
    void setHands() {
    }

    @Test
    void getCardInHand() {
    }

    @Test
    void getHand() {
    }

    @Test
    void removeCard() {
    }

    @Test
    void getLengthOfHand() {
    }

    @Test
    void printHands() {
    }

    @Test
    void printHand() {
    }

    @Test
    void getTopOfDealersPool() {
    }

    @Test
    void printTopOfDealersPool() {
    }

    @Test
    void pickTrump() {
    }

    @Test
    void pickNewTrump() {
    }

    @Test
    void callSortHand() {
    }

    @Nested
    class TestSortHands {
        public static void assertHandIsSortedProperly (Hands hands) {
            Set<String> suitsSorted = new HashSet<>();
            int handSize = hands.getLengthOfHand(0);

            for (int i = 0; i < handSize - 1; i++) {
                Card currentCard = hands.getCardInHand(0, i);
                Card nextCard = hands.getCardInHand(0, i + 1);

                // Check if cards are sorted by suit
                if (!currentCard.getSuit().equals(nextCard.getSuit())) {
                    assertFalse(suitsSorted.contains(nextCard.getSuit()), "Card " + i + " is not sorted by suit");
                    suitsSorted.add(nextCard.getSuit());
                }

                // Check if cards are sorted by rank within the same suit
                if (currentCard.getSuit().equals(nextCard.getSuit())) {
                    assertTrue(currentCard.getRankValue() < nextCard.getRankValue(), "Card " + i + " within suit is not sorted by rank.");
                }
            }
        }
        @Test
        void testSortHandSameSuit() {
            hands.setHands(0, 0, new Card("Clubs", new Rank("Ten", 10)));
            hands.setHands(0, 1, new Card("Clubs", new Rank("Nine", 9)));
            hands.setHands(0, 2, new Card("Clubs", new Rank("Queen", 12)));
            hands.setHands(0, 3, new Card("Clubs", new Rank("Jack", 11)));
            hands.setHands(0, 4, new Card("Clubs", new Rank("King", 13)));

            hands.sortHandNoBower(0);
            assertHandIsSortedProperly(hands);

        }
        @Test
        void testSortHandDifSuits() {
            hands.setHands(0, 0, new Card("Clubs", new Rank("Ten", 10)));
            hands.setHands(0, 1, new Card("Spades", new Rank("Ten", 10)));
            hands.setHands(0, 2, new Card("Diamonds", new Rank("Queen", 12)));
            hands.setHands(0, 3, new Card("Clubs", new Rank("Jack", 11)));
            hands.setHands(0, 4, new Card("Hearts", new Rank("King", 10)));

            hands.sortHandNoBower(0);
            assertHandIsSortedProperly(hands);

        }

        @Test
        void testSortHandWithBowers() {
            //assuming trump is Hearts
            hands.setHands(0, 0, new Card("Clubs", new Rank("Ten", 10)));
            hands.setHands(0, 1, new Card("Hearts", new Rank("Nine", 9)));
            hands.setHands(0, 2, new Card("Hearts", new Rank("Jack", 16)));
            hands.setHands(0, 3, new Card("Diamonds", new Rank("Jack", 15)));
            hands.setHands(0, 4, new Card("Clubs", new Rank("King", 13)));

            hands.sortHandWithBower(0, trumpCard);
            assertHandIsSortedProperly(hands);
        }
    }
}