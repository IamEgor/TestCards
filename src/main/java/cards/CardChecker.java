package cards;

import cards.exception.CheatingDeckOfCardsException;
import cards.exception.ImpossibleCountOfRepeatingCards;
import cards.exception.MalformedCardListException;
import cards.utils.Constans;

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

        final int numberOfRepeatingItems = groupedCardModel.size();
        switch (numberOfRepeatingItems) {
            case Constans.ONE_UNIQUE_CARD_IN_HAND:
                throw new CheatingDeckOfCardsException("You have provided 5 card with the same rank!");
            case Constans.TWO_UNIQUE_CARDS_IN_HAND:
                if (isFourOfKind(groupedCardModel)) {
                    return CardCombination.FOUR_OF_KIND;
                } else {
                    return CardCombination.FULL_HOUSE;
                }
            case Constans.THREE_UNIQUE_CARDS_IN_HAND:
                if (isThreeOfKind(groupedCardModel)) {
                    return CardCombination.THREE_OF_KIND;
                } else {
                    return CardCombination.TWO_PAIRS;
                }
            case Constans.FOUR_UNIQUE_CARDS_IN_HAND:
                return CardCombination.PAIR;
            case Constans.FIVE_UNIQUE_CARDS_IN_HAND:
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

    private boolean isFourOfKind(Map<CardModel, Long> cardHandMap) {
        return isNumOfKind(cardHandMap, 4);
    }

    private boolean isThreeOfKind(Map<CardModel, Long> cardHandMap) {
        return isNumOfKind(cardHandMap, 3);
    }

    private boolean isNumOfKind(Map<CardModel, Long> cardHandMap, int numberOfRepetitions) {
        Set<Map.Entry<CardModel, Long>> cardHandMapEntries = cardHandMap.entrySet();
        boolean isThreeOfKind = false;
        for (Map.Entry<CardModel, Long> entry : cardHandMapEntries) {
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
