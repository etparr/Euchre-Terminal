import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class HandsTest {
    private Hands hands;

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

    @Test
    void sortHand() {
    }
}