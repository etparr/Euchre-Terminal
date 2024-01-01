import java.util.InputMismatchException;
import java.util.Scanner;
public class Trick {
    Scanner scanner = new Scanner(System.in);
    private String trumpSuit;
    private int leadPlayer;
    private Card[] playedCards;
    private Hands hands;

    /**
     * Constructs a new Trick object.
     *
     * @param hands      The hands of the players participating in the trick.
     * @param trumpSuit  The suit designated as the trump suit for the trick.
     * @param leadPlayer The index of the player leading the trick.
     */
    public Trick(Hands hands, String trumpSuit, int leadPlayer) {
        this.trumpSuit = trumpSuit;
        this.leadPlayer = leadPlayer;
        this.hands = hands;
        this.playedCards = new Card[4];
    }

    /**
     * Plays the trick, with each player taking turns to play one card.
     *
     * @return The index of the player who wins the trick based on game rules.
     */
    public int playTrick() {
        for (int i = 0; i < 4; i++) {
            int currentPlayer = (leadPlayer + i) % 4;
            playCard(currentPlayer);
        }
        return determineWinningPlayer();
    }

    /**
     * Allows a player to play a card from their hand.
     *
     * This method displays the current trump suit, the cards that have been played so far,
     * and then prompts the specified player to pick a card from their hand. The chosen card
     * is then added to the array of played cards and removed from the player's hand.
     *
     * @param player the index of the player who is playing the card (0-based index).
     *               For example, if 'player' is 0, it refers to the first player.
     */
    public void playCard(int player) {
        int cardIndex = -1;
        boolean validInput = false;

        TextUtil.textBrick();
        TextUtil.textLine();

        System.out.println("Trump: "  + trumpSuit);
        TextUtil.textLine();

        System.out.println("Played Cards: ");
        printPlayedCards();
        TextUtil.textLine();

        System.out.println("Player " + (player + 1) + " Pick Your Card: ");
        hands.printHand(player);
        TextUtil.textLine();

        while (!validInput) {
            try {
                cardIndex = scanner.nextInt() - 1;
                if (cardIndex >= 0 && cardIndex < hands.getLengthOfHand(player)) {
                    if (hands.getCardInHand(player, cardIndex) != null) {
                        validInput = true;
                    }
                    else {
                        System.out.print("Please enter a card you have not already played.\n");
                    }
                }
                else {
                    System.out.print("Please enter a number between 1 and " + hands.getLengthOfHand(player) + ".\n");
                    }
            } catch (InputMismatchException e) {
                System.out.print("Please enter a number.\n");
                scanner.nextLine();
            }
        }

        Card card = hands.getCardInHand(player, cardIndex);
        playedCards[player] = card;
        hands.removeCard(player, cardIndex);
    }

    public int determineWinningPlayer() {
        Card highestValueCard = new Card(playedCards[0].getSuit(), new Rank ("Eight", 8));
        Card highestTrumpCard = new Card(trumpSuit, new Rank ("Eight", 8));
        for (int i = 0; i < playedCards.length; i++) {
            if (playedCards[i].getSuit().equals(trumpSuit)) {
                if (playedCards[i].getRankValue() > highestTrumpCard.getRankValue()) {
                    highestTrumpCard = playedCards[i];
                }
            } else if (playedCards[i].getSuit().equals(playedCards[0].getSuit())) {
                if (playedCards[i].getRankValue() > highestTrumpCard.getRankValue()) {
                    highestValueCard = playedCards[i];
                }
            }
        }
        // Debugging
        /**
        Card tcard = highestTrumpCard;
        Card vcard = highestValueCard;
        System.out.println(tcard.getSuit() + " " + tcard.getRank());
        System.out.println(vcard.getSuit() + " " + vcard.getRank());
         **/

        if (highestTrumpCard.getRankValue() > 8) {
            return findPlayerByCard(highestTrumpCard);
        }
        else {
            return findPlayerByCard(highestValueCard);
        }
    }

    public int determineWinningTeam(int winningPlayer) {
        int winningTeam;
        if (winningPlayer == 0 || winningPlayer == 2) {
            winningTeam = 1;
        }
        else {
            winningTeam = 2;
        }
        return winningTeam;
    }
    // 0 1 2 3
    public int findPlayerByCard(Card winningCard) {
        int n = -1;
        for (int i = 0; i < playedCards.length; i++) {
            if (playedCards[i].equals(winningCard)) {
                n = (leadPlayer + i) % 3;
                break;
            }
        }
        return n;
    }
    public void printPlayedCards() {
        for (int i = 0; i < playedCards.length; i++) {
            Card card = playedCards[i];
            if (card != null) {
                System.out.println((i + 1) + ". " + card.getSuit() + " " + card.getName());
            } else {
                System.out.println((i + 1) + ". Card not played");
            }
        }
    }
}