package cards;

public enum CardModel {

    TWO('2', 2),
    THREE('3', 3),
    FOUR('4', 4),
    FIVE('5', 5),
    SIX('6', 6),
    SEVEN('7', 7),
    EIGHT('8', 8),
    NINE('9', 9),
    TEN('T', 10),
    JACK('J', 11),
    QUEEN('K', 12),
    KING('Q', 13),
    ACE('A', 14);

    private char symbol;
    private int strength;

    CardModel(char symbol, int strength) {
        this.symbol = symbol;
        this.strength = strength;
    }

    public char getSymbol() {
        return symbol;
    }

    public int getStrength() {
        return strength;
    }
}
