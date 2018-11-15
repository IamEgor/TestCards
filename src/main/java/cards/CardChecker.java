package cards;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CardChecker {

    public CardCombination getCombination(List<CardModel> cardModels) {

        if (cardModels == null || cardModels.size() == 0) {
            throw new IllegalStateException("You should provide card set");
        } else if (cardModels.size() != 5) {
            throw new IllegalStateException("You should provide 5 cards");
        }


        if (isStreet(cardModels)) {
            return CardCombination.STREET;
        }


        Collector<CardModel, ?, Map<CardModel, Long>> collector = Collectors.groupingBy(Function.identity(), Collectors.counting());
        Map<CardModel, Long> groupedCardModel = cardModels.stream().collect(collector);

        System.out.println(groupedCardModel);

        Set<Map.Entry<CardModel, Long>> groupedCardModelEntries = groupedCardModel.entrySet();

        switch (groupedCardModelEntries.size()) {
            case 2:
                if (isFourOfKind(groupedCardModelEntries)) {
                    return CardCombination.FOUR_OF_KIND;
                } else {
                    return CardCombination.FULL_HOUSE;
                }
            case 3:
                if (isThreeOfKind(groupedCardModelEntries)) {
                    return CardCombination.THREE_OF_KIND;
                } else {
                    return CardCombination.TWO_PAIRS;
                }
            case 4:
                return CardCombination.PAIR;
            default:
                return CardCombination.NO_COMBINATION;
        }
    }

    private boolean isStreet(List<CardModel> cardModels) {

        List<Integer> cardModelStrengthList = cardModels.stream()
                .sorted(new StreetComparator())
                .map(CardModel::getStrength)
                .collect(Collectors.toList());

        boolean isStreet = true;
        int previousStrength = -1;
        for (Integer strength : cardModelStrengthList) {
            if (previousStrength != -1 && strength != previousStrength + 1) {
                isStreet = false;
                break;
            }
            previousStrength = strength;
        }

        return isStreet;
    }

    private boolean isFourOfKind(Set<Map.Entry<CardModel, Long>> entries) {
        return isNumOfKind(entries, 4);
    }

    private boolean isThreeOfKind(Set<Map.Entry<CardModel, Long>> entries) {
        return isNumOfKind(entries, 3);
    }

    private boolean isNumOfKind(Set<Map.Entry<CardModel, Long>> entries, int numberOfRepetitions) {
        boolean isThreeOfKind = false;
        for (Map.Entry<CardModel, Long> entry : entries) {
            if (entry.getValue() == numberOfRepetitions) {
                isThreeOfKind = true;
                break;
            }
        }
        return isThreeOfKind;
    }

    public static void main(String[] args) {
        CardChecker cardChecker = new CardChecker();
        List<CardModel> cardModels = Arrays.asList(CardModel.ACE, CardModel.ACE, CardModel.ACE, CardModel.ACE, CardModel.ACE);
        System.out.println(cardChecker.getCombination(cardModels));
        List<CardModel> cardModels2 = Arrays.asList(CardModel.ACE, CardModel.JACK, CardModel.FIVE, CardModel.ACE, CardModel.TEN);
        System.out.println(cardChecker.getCombination(cardModels2));
    }

}
