package cards;

public enum CardCombination {

    NO_COMBINATION(0),
    PAIR(1),
    TWO_PAIRS(2),
    THREE_OF_KIND(3),
    FULL_HOUSE(4),
    FOUR_OF_KIND(5),
    STREET(6);

    private int strength;

    CardCombination(int strength) {
        this.strength = strength;
    }
}
