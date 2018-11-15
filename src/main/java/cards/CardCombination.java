package cards;

public enum CardCombination {

    NO_COMBINATION(0),
    PAIR(1),
    TWO_PAIRS(2),
    THREE_OF_KIND(3),
    STRAIGHT(4),
    FULL_HOUSE(5),
    FOUR_OF_KIND(6);


    private int strength;

    CardCombination(int strength) {
        this.strength = strength;
    }
}
