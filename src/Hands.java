
import java.util.Scanner;
import java.util.InputMismatchException;

public class Hands {
    Scanner scanner = new Scanner(System.in);
    private Card[][] hands;
    private final int PLAYER_DEALER = 0;

    public Hands(int numberOfSets, int cardsPerSet) {
        hands = new Card[numberOfSets + 1][cardsPerSet];
    }

    /**
     * @param handIndex
     * @param cardIndex
     * @param card
     */
    public void setHands(int handIndex, int cardIndex, Card card) {
        hands[handIndex][cardIndex] = card;
    }

    /**
     * @param handIndex
     * @param cardIndex
     * @return Card
     */
    public Card getCardInHand(int handIndex, int cardIndex) {
        return hands[handIndex][cardIndex];
    }

    public Card[] getHand(int handIndex) {

        return hands[handIndex];
    }

    public void removeCard(int handIndex, int cardIndex) {

        hands[handIndex][cardIndex] = null;
    }

    /**
     * @param handIndex
     * @return int length
     */
    public int getLengthOfHand(int handIndex) {
        return hands[handIndex].length;
    }

    public void printHands() {
        for (int i = 0; i < hands.length - 1; i++) {
            System.out.println("\nHand " + i);
            for (int j = 0; j < hands[i].length; j++) {
                Card card = hands[i][j];
                System.out.println((j + 1) + ". " + card.getSuit() + " " + card.getName() + " Value: " + card.getRankValue());
            }
        }

        System.out.println("\nDealers Pool");
        for (int i = 0; i < hands[4].length - 1; i++) {
            Card card = hands[4][i];
            System.out.println((i + 1) + ". " + card.getSuit() + " " + card.getName() + " Value: " + card.getRankValue());
        }
    }

    public void printHand(int hand) {
        for (int i = 0; i < hands.length; i++) {
            Card card = hands[hand][i];
            if (card != null) {
                System.out.println((i + 1) + ". " + card.getName() + " of " + card.getSuit());
            }
        }
    }

    public Card getTopOfDealersPool() {
        return hands[4][0];
    }

    public void printTopOfDealersPool() {
        Card card = getTopOfDealersPool();
        System.out.println("Trump: " + card.getName() + " of " + card.getSuit());
    }

    /**
     * Selects the trump card for the current round of the game.
     * The method cycles through players starting from the dealer,
     * allowing them to either pass or pick the top card of the dealer's pool as the trump.
     * If all players pass, a new trump is picked using pickNewTrump() method.
     *
     * @return the selected trump Card for the round.
     */
    public Card pickTrump() {
        Card trump = getTopOfDealersPool();
        int playerCount = 4;

        for (int i = 1; i <= playerCount; i++) {
            int currentPlayer = (PLAYER_DEALER + i) % playerCount;
            int decision = 0;

            TextUtil.textBrick();

            TextUtil.textLine();
            System.out.println("Player " + (currentPlayer + 1));
            printTopOfDealersPool();
            TextUtil.textLine();

            System.out.print("Your Hand: \n");
            printHand(currentPlayer);
            TextUtil.textLine();

            System.out.print("Pass (1) or Pick it up? (2)\n");
            TextUtil.textLine();

            boolean validInput = false;
            while (!validInput) {
                try {
                    decision = scanner.nextInt();

                    if (decision == 1 || decision == 2) {
                        validInput = true;
                    } else {
                        System.out.println("Please enter 1 or 2.\n");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a number.");
                    scanner.nextLine();
                }
            }

            if (decision == 2) {
                break;
            } else if (currentPlayer == PLAYER_DEALER) {
                trump = pickNewTrump();
                break;
            }
        }
        establishBowers(trump);
        return trump;
    }

    /**
     * Selects a new trump card when all players have passed on the initial offer.
     * Players are prompted to choose a suit from their hand.
     * If the choice reaches the last player (Dealer), they must choose a suit.
     *
     * @return the newly selected trump Card based on players' decisions.
     */
    public Card pickNewTrump() {
        Card trump = new Card("Hearts", new Rank("Eight", 8));
        int playerCount = 4;

        for (int i = 1; i <= playerCount; i++) {
            int currentPlayer = (PLAYER_DEALER + i) % playerCount;
            int decision = 0;

            TextUtil.textBrick();

            TextUtil.textLine();
            System.out.println("Player " + (currentPlayer + 1));
            System.out.print("Your Hand: \n");
            printHand(currentPlayer);
            TextUtil.textLine();

            System.out.print("Pick a suit from a card in your hand (1-5) or pass (6)\n");
            TextUtil.textLine();

            boolean validInput = false;
            while (!validInput) {
                try {
                    decision = scanner.nextInt();

                    if (decision > 0 && decision < 7) {
                        validInput = true;
                    } else {
                        System.out.println("Please enter a number 1-6.\n");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a number.");
                    scanner.nextLine();
                }
            }

            if (decision < 6) {
                trump = getCardInHand(currentPlayer, decision - 1);
                break;
            } else if (currentPlayer == ((PLAYER_DEALER + 3) % playerCount)) {
                System.out.print("Your Hand: \n");
                printHand(currentPlayer);
                System.out.print("Pick a suit from a card in your hand (1-5)\n");
                decision = scanner.nextInt();
                trump = getCardInHand(currentPlayer, decision);
                break;
            }
        }
        return trump;
    }

    /**
     * Lets players choose if they want to sort hands, then sorts depending on decision.
     */
    public void callSortHand(Card trumpCard) {
        int playerCount = 4;

        for (int i = 1; i <= playerCount; i++) {
            int currentPlayer = (PLAYER_DEALER + i) % playerCount;
            int decision = -1;

            TextUtil.textBrick();

            TextUtil.textLine();
            System.out.println("Player " + (currentPlayer + 1));
            printTopOfDealersPool();
            TextUtil.textLine();

            System.out.print("Your Hand: \n");
            printHand(currentPlayer);
            TextUtil.textLine();

            System.out.println("Do you want to sort your hand? Yes (1) or No (2)");
            boolean validInputS = false;
            while (!validInputS) {
                try {
                    decision = scanner.nextInt();

                    if (decision == 1 || decision == 2) {
                        validInputS = true;
                    } else {
                        System.out.println("Please enter 1 or 2.\n");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a number.");
                    scanner.nextLine();
                }
            }
            if (decision == 1) {
                sortHandNoBower(currentPlayer);
            }
        }
    }

    /**
     * Sorts a player's hand of cards in ascending order based on their suits. Does not sort left bower.
     *
     * @param player The player for whom to sort the hand.
     */
    public void sortHandNoBower(int player) {
        // Retrieve the player's hand
        Card[] hand = hands[player];
        boolean sorted = false;

        while (!sorted) {
            sorted = true;

            for (int i = 0; i < hand.length - 1; i++) {
                if (shouldSwapNoBower(hand[i], hand[i + 1])) {
                    // Swap the cards
                    Card temp = hand[i];
                    hand[i] = hand[i + 1];
                    hand[i + 1] = temp;
                    sorted = false;
                }
            }
        }
    }

    public void sortHandWithBower(int player, Card trumpCard) {
        // Retrieve the player's hand
        Card[] hand = hands[player];
        boolean sorted = false;

        while (!sorted) {
            sorted = true;

            for (int i = 0; i < hand.length - 1; i++) {
                if (shouldSwapWithBower(hand[i], hand[i + 1], trumpCard)) {
                    // Swap the cards
                    Card temp = hand[i];
                    hand[i] = hand[i + 1];
                    hand[i + 1] = temp;
                    sorted = false;
                }
            }
        }
    }

    /**
     * Sorts each player hand + dealers pool with bower
     * @param trumpCard
     */
    public void sortHandsWithBower(Card trumpCard) {
        int playerCount = 4;
        for (int i = 0; i < playerCount; i++) {
            sortHandWithBower(i, trumpCard);
        }
    }

    /**
     * Sorts each player hand without the right bower.
     */
    public void sortHandsNoBower() {
        int playerCount = 4;
        for (int i = 0; i < playerCount; i++) {
            sortHandNoBower(i);
        }
    }


    private void establishBowers(Card trump) {
        int playerCount = 4;

        for (int i = 0; i < playerCount; i++) {
            for (int j = 0; j < 5; j++) {
                if (hands[i][j].getName().equals("Jack")) {
                    if (hands[i][j].getSuit().equals(trump.getSuit())) {
                        hands[i][j].setValueOfCard(15);
                    } else if (hands[i][j].getSuit().equals(trump.oppositeSuit())) {
                        hands[i][j].setValueOfCard(14);
                    }
                }
            }
        }
    }

    private boolean shouldSwapWithBower(Card card1, Card card2, Card trumpCard) {
        String trumpSuit = trumpCard.getSuit();
        String rightBowerSuit = trumpCard.oppositeSuit();

        boolean isRightBower1 = card1.getSuit().equals(rightBowerSuit) && card1.getRankValue() == 11; // Assuming Jack has rank value 11
        boolean isRightBower2 = card2.getSuit().equals(rightBowerSuit) && card2.getRankValue() == 11;

        // Special handling for right bower
        if (isRightBower1 && !isRightBower2) return true;
        if (!isRightBower1 && isRightBower2) return false;

        // Sort by suit
        if (!card1.getSuit().equals(card2.getSuit())) {
            return card1.getSuit().compareTo(card2.getSuit()) > 0;
        }

        // Sort by rank within the same suit
        return card1.getRankValue() > card2.getRankValue();
    }

    private boolean shouldSwapNoBower(Card card1, Card card2) {
        // Sort by suit first
        int suitComparison = card1.getSuit().compareTo(card2.getSuit());
        if (suitComparison > 0) {
            return true;
        } else if (suitComparison < 0) {
            return false;
        }

        // If suits are the same, sort by rank
        return card1.getRankValue() > card2.getRankValue();
    }
}

