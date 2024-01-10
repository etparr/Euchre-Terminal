import java.util.InputMismatchException;
import java.util.Scanner;
public class Trick {
    Scanner scanner = new Scanner(System.in);
    private Card trump;
    private int leadPlayer;
    private Card[] playedCards;
    private Hands hands;

    /**
     * Constructs a new Trick object.
     *
     * @param hands The hands of the players participating in the trick.
     * @param trump The suit designated as the trump suit for the trick.
     * @param leadPlayer The index of the player leading the trick.
     */
    public Trick(Hands hands, Card trump, int leadPlayer) {
        this.trump = trump;
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
            playCard(currentPlayer, i);
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
    public void playCard(int player, int turn) {
        int cardIndex = -1;
        boolean validInput = false;

        TextUtil.textBrick();
        TextUtil.textLine();

        System.out.println("Trump: "  + trump.getSuit());
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
        playedCards[turn] = card;
        hands.removeCard(player, cardIndex);
    }

    /**
     * Determines the winning player based on the highest-value card played in a trick.
     * It compares the cards played by each player and selects the one with the highest rank.
     * If a trump suit is in play, it gives priority to the highest trump card; otherwise,
     * it chooses the highest card of the leading suit.
     *
     * @return The index of the winning player (0, 1, 2, or 3) in the current trick.
     */
    public int determineWinningPlayer() {
        Card highestValueCard = new Card(playedCards[0].getSuit(), new Rank ("Eight", 8));
        Card highestTrumpCard = new Card(trump.getSuit(), new Rank ("Eight", 8));

        for (int i = 0; i < playedCards.length; i++) {
            boolean isTrumpSuit = playedCards[i].getSuit().equals(trump.getSuit());
            boolean isOppositeSuitJack = playedCards[i].getSuit().equals(trump.oppositeSuit()) && playedCards[i].getName().equals("Jack");
            boolean isLeadingCardSuit = playedCards[i].getSuit().equals(playedCards[0].getSuit());

            if (isTrumpSuit || isOppositeSuitJack) {
                if (playedCards[i].getRankValue() > highestTrumpCard.getRankValue()) {
                    highestTrumpCard = playedCards[i];
                }
            } else if (isLeadingCardSuit) {
                if (playedCards[i].getRankValue() > highestValueCard.getRankValue()) {
                    highestValueCard = playedCards[i];
                }
            }
        }

        /**
        // Debugging
        Card tCard = highestTrumpCard;
        Card vCard = highestValueCard;
        System.out.println(tCard.getSuit() + " " + tCard.getName());
        System.out.println(vCard.getSuit() + " " + vCard.getName());
         **/
        if (highestTrumpCard.getRankValue() > 8) {
            return findWinningPlayerByWinningCard(highestTrumpCard);
        }
        else {
            return findWinningPlayerByWinningCard(highestValueCard);
        }
    }

    public int determineWinningTeam(int winningPlayer) {
        int winningTeam = -1;
        if (winningPlayer == 0 || winningPlayer == 2) {
            winningTeam = 1;
        }
        else if (winningPlayer == 1 || winningPlayer == 3) {
            winningTeam = 2;
        }
        return winningTeam;
    }
    // 0 1 2 3
    public int findWinningPlayerByWinningCard(Card winningCard) {
        int n = -1;
        int playerCount = 4;
        for (int i = 0; i < playedCards.length; i++) {
            boolean isSameSuit = playedCards[i].getSuit().equals(winningCard.getSuit());
            boolean isSameRankName = playedCards[i].getName().equals(winningCard.getName());
            boolean isSameCard = isSameRankName && isSameSuit;

            if (isSameCard) {
                n = (leadPlayer + i) % playerCount;
                break;
            }
        }
        return n;
    }

    public void printPlayedCards() {
        int playerCount = 4;
        for (int i = 0; i < playedCards.length; i++) {
            Card card = playedCards[(leadPlayer + i) % playerCount];

            if (card != null) {
                System.out.println("Player " + (i + 1) + ": " + card.getName() + " of " + card.getSuit());
            } else {
                System.out.println("Player " + (i + 1) + ": Card not played");
            }
        }
    }

    /**
     * Set the played card
     * @param card
     * @param index
     */
    public void setPlayedCard(Card card, int index) {
        playedCards[index] = card;
    }
}