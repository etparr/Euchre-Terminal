public class Card {
    private String suit;
    private Rank rank;

    /**
     * Constructs a new Card instance with the given suit and rank.
     *
     * @param suit the suit of the card (e.g., "Hearts", "Spades").
     * @param rank the rank of the card, represented as a Rank object, which includes
     *             both the name and value of the rank (e.g., "Ace" with value 1,
     *             "King" with value 13).
     */
    public Card(String suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public String getSuit() {
        return suit;
    }
    public String getName() {
        return rank.getName();
    }
    public String oppositeSuit() {
        if (this.suit.equals("Hearts")) {
            return "Diamonds";
        } else if (this.suit.equals("Diamonds")) {
            return "Hearts";
        } else if (this.suit.equals("Clubs")) {
            return "Spades";
        } else if (this.suit.equals("Spades")) {
            return "Clubs";
        }
        return null;
     }

    /**
     * @return String
     */
    public String cardToString(){
        return (this.getName() + " of " + this.getSuit());
}
    public int getRankValue() {
        return rank.getValue();
    }
    public void setValueOfCard(int value) {
        rank.setValue(value);
    }
}

