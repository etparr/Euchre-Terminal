public class Rank{
    private String name;
    private int value;
    /**
     * Constructs a new Rank object with the specified name and value.
     *
     * @param name  the name of the rank (e.g., "Ace", "King", "Queen", "Eight").
     * @param value the value associated with the rank. The interpretation of the value
     *              depends on the rules of the card game (e.g., 1 for Ace, 13 for King).
     */
    public Rank(String name, int value) {
        this.name = name;
        this.value = value;
    }
    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
    public void setValue(int valueInt){
        value = valueInt;
    }
}
