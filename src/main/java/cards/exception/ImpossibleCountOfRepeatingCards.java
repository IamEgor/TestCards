package cards.exception;

public class ImpossibleCountOfRepeatingCards extends IllegalStateException {

    public ImpossibleCountOfRepeatingCards(int countOfRepeatingCards) {
        super(String.format("You have %d reps in 4 cards!", countOfRepeatingCards));
    }
}
