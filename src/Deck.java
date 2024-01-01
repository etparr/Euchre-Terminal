import java.util.Random;
public class Deck {
    private Card[] deck;

    public Deck() {
        deck = new Card[24];

        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        Rank[] ranks = {
                new Rank("Nine", 9),
                new Rank("Ten", 10),
                new Rank("Jack", 11),
                new Rank("Queen", 12),
                new Rank("King", 13),
                new Rank("Ace", 14)
        };

        int cardCount = 0;
        for (String suit : suits) {
            for (Rank rank : ranks) {
                deck[cardCount] = new Card(suit, rank);
                cardCount++;
            }
        }
    }

    public void shuffleDeck() {
        Random random = new Random();

        for (int i = deck.length - 1; i > 0; i--) {
            int randomIndex = random.nextInt(i + 1);
            Card temp = deck[i];
            deck[i] = deck[randomIndex];
            deck[randomIndex] = temp;
        }
    }
    public int getDeckLength() {
        return deck.length;
}
    public Card getCardAtIndex(int index) {
        return deck[index];
    }
    public void printDeck() {
        for (int i = 0; i < deck.length; i++) {
            Card card = deck[i];
            System.out.println("Card " + (i + 1) + ": " + card.getSuit() + " " + card.getName());
        }
    }
    public Hands dealDeck() {
        int totalCards = deck.length;
        int cardsPerSet = 5;
        int numberOfSets = 4;

        Hands dealtHands = new Hands(numberOfSets, cardsPerSet);

        for (int i = 0; i < numberOfSets; i++) {
            for (int j = 0; j < cardsPerSet; j++) {
                dealtHands.setHands(i, j, deck[j + cardsPerSet * i] );
            }
        }
         // dealers pool
        for (int i = 0; i < 4; i++) {
            dealtHands.setHands(4, i, deck[i + 20]);
        }

        return dealtHands;
    }


}
