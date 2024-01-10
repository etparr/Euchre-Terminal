import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

class TrickTest {
    private Trick trick;
    private Card trumpCard = new Card("Hearts", new Rank("Ace", 14));
    @BeforeEach
    void setUp() {
        Deck deck = new Deck();

        //deal deck
        Hands dealtHands = deck.dealDeck();

        Card trumpCard = new Card("Hearts", new Rank("Nine", 9));
        int leadPlayer = 0;

        trick = new Trick(dealtHands, trumpCard, leadPlayer);
    }


    @Test
    void playTrick() {
        // Your test code for playTrick goes here
    }

    @Test
    void playCard() {
        // Your test code for playCard goes here
    }

    @Nested
    class TestDetermineWinningPlayer {
        @Test
        void testDifferentSuitsNoFollowLeadingSuit() {
            trick.setPlayedCard(new Card("Diamonds", new Rank("Nine", 9)), 0);
            trick.setPlayedCard(new Card("Spades", new Rank("King", 13)), 1);
            trick.setPlayedCard(new Card("Clubs", new Rank("Jack", 11)), 2);
            trick.setPlayedCard(new Card("Spades", new Rank("Queen", 12)), 3);

            int winningPlayer = trick.determineWinningPlayer();

            assertEquals(0, winningPlayer, "Player 0 should win with the Nine of Diamonds.");
        }

        @Test
        void testTrumpWins() {
            trick.setPlayedCard(new Card("Diamonds", new Rank("Ace", 14)), 0);
            trick.setPlayedCard(new Card("Hearts", new Rank("King", 13)), 1);
            trick.setPlayedCard(new Card("Clubs", new Rank("Jack", 11)), 2);
            trick.setPlayedCard(new Card("Spades", new Rank("Queen", 12)), 3);

            int winningPlayer = trick.determineWinningPlayer();

            assertEquals(1, winningPlayer, "Player 1 should win with the King of Hearts");
        }

        @Test
        void testAllTrump() {
            trick.setPlayedCard(new Card("Hearts", new Rank("Ace", 14)), 0);
            trick.setPlayedCard(new Card("Hearts", new Rank("King", 13)), 1);
            trick.setPlayedCard(new Card("Hearts", new Rank("Jack", 16)), 2);
            trick.setPlayedCard(new Card("Diamond", new Rank("Jack", 15)), 3);
            int winningPlayer = trick.determineWinningPlayer();

            assertEquals(2, winningPlayer, "Player 2 should win with the Jack of Hearts.");
        }
        @Test
        void testAllTrump2() {
            trick.setPlayedCard(new Card("Hearts", new Rank("Ten", 10)), 0);
            trick.setPlayedCard(new Card("Hearts", new Rank("Nine", 9)), 1);
            trick.setPlayedCard(new Card("Hearts", new Rank("Ace", 14)), 2);
            trick.setPlayedCard(new Card("Diamond", new Rank("Queen", 12)), 3);
            int winningPlayer = trick.determineWinningPlayer();

            assertEquals(2, winningPlayer, "Player 2 should win with the Ace of Hearts.");
        }

        @Test
        void testLeftBower() {
            trick.setPlayedCard(new Card("Hearts", new Rank("Ace", 14)), 0);
            trick.setPlayedCard(new Card("Hearts", new Rank("King", 13)), 1);
            trick.setPlayedCard(new Card("Clubs", new Rank("Ace", 14)), 2);
            trick.setPlayedCard(new Card("Diamonds", new Rank("Jack", 15)), 3);

            int winningPlayer = trick.determineWinningPlayer();

            assertEquals(3, winningPlayer, "Player 3 should win with the Jack of Diamonds.");
        }

        @Test
        void testNoTrumpFollowLeadingSuit() {

            trick.setPlayedCard(new Card("Diamonds", new Rank("Ten", 10)), 0);
            trick.setPlayedCard(new Card("Diamonds", new Rank("King", 13)), 1);
            trick.setPlayedCard(new Card("Diamonds", new Rank("Ace", 14)), 2);
            trick.setPlayedCard(new Card("Diamonds", new Rank("Queen", 12)), 3);

            int winningPlayer = trick.determineWinningPlayer();

            assertEquals(2, winningPlayer, "Player 2 should win with the Ace of Diamonds.");
        }
        @Test
        void testNoTrumpFollowLeadingSuit2() {

            trick.setPlayedCard(new Card("Diamonds", new Rank("Nine", 9)), 0);
            trick.setPlayedCard(new Card("Diamonds", new Rank("Ten", 10)), 1);
            trick.setPlayedCard(new Card("Diamonds", new Rank("King", 13)), 2);
            trick.setPlayedCard(new Card("Diamonds", new Rank("Queen", 12)), 3);

            int winningPlayer = trick.determineWinningPlayer();

            assertEquals(2, winningPlayer, "Player 3 should win with the Queen of Diamonds.");
        }
        @Test
        void testNoTrumpFollowLeadingSuit3() {

            trick.setPlayedCard(new Card("Diamonds", new Rank("Nine", 9)), 0);
            trick.setPlayedCard(new Card("Clubs", new Rank("Jack", 11)), 1);
            trick.setPlayedCard(new Card("Diamonds", new Rank("Ten", 10)), 2);
            trick.setPlayedCard(new Card("Diamonds", new Rank("Queen", 12)), 3);

            int winningPlayer = trick.determineWinningPlayer();

            assertEquals(3, winningPlayer, "Player 3 should win with the Queen of Diamonds.");
        }

        @Test
        void testSpecialCase() {
            trumpCard = new Card("Clubs", new Rank("Jack", 16));
            trick.setPlayedCard(new Card("Hearts", new Rank("Queen", 12)), 0);
            trick.setPlayedCard(new Card("Hearts", new Rank("Ace", 14)), 1);
            trick.setPlayedCard(new Card("Hearts", new Rank("Nine", 9)), 2);
            trick.setPlayedCard(new Card("Hearts", new Rank("Ten", 10)), 3);

            int winningPlayer = trick.determineWinningPlayer();

            assertEquals(1, winningPlayer, "Player 1 should win with the Ace of Hearts.");

        }
    }

    @Test
    void determineWinningTeam() {
        // Your test code for determineWinningTeam goes here
    }
    @Nested
    class testfindWinningPlayerByWinningCard {
        @Test
        void testfindWinningPlayerByWinningCard () {
            trumpCard = new Card("Clubs", new Rank("Jack", 16));
            trick.setPlayedCard(new Card("Hearts", new Rank("Queen", 12)), 0);
            trick.setPlayedCard(new Card("Hearts", new Rank("Ace", 14)), 1);
            trick.setPlayedCard(new Card("Hearts", new Rank("Nine", 9)), 2);
            trick.setPlayedCard(new Card("Hearts", new Rank("Ten", 10)), 3);

            Card card = new Card("Hearts", new Rank("Ace", 14));

            int winningPlayer = trick.findWinningPlayerByWinningCard(card);

            assertEquals(1, winningPlayer, "Player 1 should win with the Ace of Hearts.");

        }
        @Test
        void testfindWinningPlayerByWinningCard2() {
            trumpCard = new Card("Hearts", new Rank("Jack", 16));
            trick.setPlayedCard(new Card("Diamonds", new Rank("King", 13)), 0);
            trick.setPlayedCard(new Card("Clubs", new Rank("Ace", 14)), 1);
            trick.setPlayedCard(new Card("Clubs", new Rank("Ten", 10)), 2);
            trick.setPlayedCard(new Card("Diamonds", new Rank("Nine", 9)), 3);

            Card card = new Card("Diamonds", new Rank("King", 13));

            int winningPlayer = trick.findWinningPlayerByWinningCard(card);

            assertEquals(0, winningPlayer, "Player 0 should win with the King of Diamonds.");

        }
    }
}