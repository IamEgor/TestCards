package cards;

import cards.exception.CheatingDeckOfCardsException;
import cards.exception.ImpossibleCountOfRepeatingCards;
import cards.exception.MalformedCardListException;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CardChecker {


    /**
     * @param cardsList list of cards
     * @return CardCombination
     * @throws MalformedCardListException   if your list of cards is empty or its size is not equals to 5
     * @throws CheatingDeckOfCardsException if you have provided 5 card with the same rank
     */
    public CardCombination getCombination(List<CardModel> cardsList) throws MalformedCardListException, CheatingDeckOfCardsException {

        if (cardsList == null || cardsList.size() == 0) {
            throw new MalformedCardListException("You should provide card set");
        } else if (cardsList.size() != 5) {
            throw new MalformedCardListException("You should provide 5 cards");
        }


        if (isStraight(cardsList)) {
            return CardCombination.STRAIGHT;
        }

        Collector<CardModel, ?, Map<CardModel, Long>> collector = Collectors.groupingBy(Function.identity(), Collectors.counting());
        Map<CardModel, Long> groupedCardModel = cardsList.stream().collect(collector);

        Set<Map.Entry<CardModel, Long>> groupedCardModelEntries = groupedCardModel.entrySet();

        final int numberOfRepeatingItems = groupedCardModelEntries.size();
        switch (numberOfRepeatingItems) {
            case 1:
                throw new CheatingDeckOfCardsException("You have provided 5 card with the same rank!");
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
            case 5:
                return CardCombination.NO_COMBINATION;
            default:
                throw new ImpossibleCountOfRepeatingCards(numberOfRepeatingItems);
        }
    }

    private boolean isStraight(List<CardModel> cardsList) {

        List<Integer> cardsListRankList = cardsList.stream()
                .sorted(new StraightComparator())
                .map(CardModel::getRank)
                .collect(Collectors.toList());

        boolean isStraight = true;
        int previousRank = -1;
        for (Integer rank : cardsListRankList) {
            if (previousRank != -1 && rank != previousRank + 1) {
                isStraight = false;
                break;
            }
            previousRank = rank;
        }

        return isStraight;
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
