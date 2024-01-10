
public class Main {
    public static void main(String[] args) {

        // create deck
        Deck deck = new Deck();

        // shuffle deck
        deck.shuffleDeck();

        //deal deck
        Hands dealtHands = deck.dealDeck();

        //Sort Hands
        dealtHands.sortHandsNoBower();

        // Pick Trump
        Card trumpCard = dealtHands.pickTrump();

        //Sort Hands with Trump
        dealtHands.sortHandsWithBower(trumpCard);

        // Play three Tricks
        int team1Wins = 0;
        int team2Wins = 0;
        int winningPlayer= 0;

        for (int trickNumber = 1; trickNumber <= 5; trickNumber++) {
            Trick trick = new Trick(dealtHands, trumpCard, (trickNumber == 1) ? 1 : winningPlayer);
            winningPlayer = trick.playTrick();
            int winningTeam = trick.determineWinningTeam(winningPlayer);

            // Update team win counts
            if (winningTeam == 1) {
                team1Wins++;
                TextUtil.textBrick();
                TextUtil.textLine();
                System.out.println("Trump: " + trumpCard.getSuit());
                TextUtil.textLine();
                trick.printPlayedCards();
                System.out.println("\nTeam 1 Wins the Trick. Player " + (winningPlayer + 1) + " goes first.");
                TextUtil.textLine();

            } else if (winningTeam == 2) {
                team2Wins++;
                TextUtil.textBrick();
                TextUtil.textLine();
                System.out.println("Trump: " + trumpCard.getSuit());
                TextUtil.textLine();
                trick.printPlayedCards();
                if (trickNumber != 5) {
                    System.out.println("\nTeam 2 Wins the Trick. Player " + (winningPlayer + 1) + " goes first.");
                }
                else {
                    System.out.println("\nTeam 2 Wins the Trick Thanks to Player " + (winningPlayer + 1) + ".");
                }
                TextUtil.textLine();
            }
        }
        // Who wins + score
        if (team1Wins > team2Wins) {
            TextUtil.textBrick();
            System.out.println("Team 1 Wins the Game with " + team1Wins + " Tricks!");
        }
        else {
            TextUtil.textBrick();
            System.out.println("Team 2 Wins the Game with " + team2Wins + " Tricks!");
        }
    }
}
