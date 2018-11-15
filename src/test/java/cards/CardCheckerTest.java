package cards;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class CardCheckerTest {

    private CardChecker mCardChecker;

    @Before
    public void init() {
        mCardChecker = new CardChecker();
    }

    @Test()
    public void testStreet1() {
        CardCombination combination1 = mCardChecker.getCombination(Arrays.asList(CardModel.ACE, CardModel.QUEEN, CardModel.JACK, CardModel.KING, CardModel.TEN));
        Assert.assertEquals(CardCombination.STREET, combination1);
        CardCombination combination2 = mCardChecker.getCombination(Arrays.asList(CardModel.ACE, CardModel.SEVEN, CardModel.JACK, CardModel.KING, CardModel.TEN));
        Assert.assertNotEquals(CardCombination.STREET, combination2);
    }

    @Test()
    public void testStreet2() {
        CardCombination combination1 = mCardChecker.getCombination(Arrays.asList(CardModel.TWO, CardModel.THREE, CardModel.FIVE, CardModel.FOUR, CardModel.SIX));
        Assert.assertEquals(CardCombination.STREET, combination1);
        CardCombination combination2 = mCardChecker.getCombination(Arrays.asList(CardModel.TWO, CardModel.THREE, CardModel.FIVE, CardModel.FOUR, CardModel.THREE));
        Assert.assertNotEquals(CardCombination.STREET, combination2);
    }

    @Test()
    public void testPair() {
        CardCombination combination1 = mCardChecker.getCombination(Arrays.asList(CardModel.ACE, CardModel.QUEEN, CardModel.ACE, CardModel.KING, CardModel.TEN));
        Assert.assertEquals(CardCombination.PAIR, combination1);
        CardCombination combination2 = mCardChecker.getCombination(Arrays.asList(CardModel.ACE, CardModel.QUEEN, CardModel.JACK, CardModel.KING, CardModel.TEN));
        Assert.assertNotEquals(CardCombination.PAIR, combination2);
    }

    @Test()
    public void testTwoPairs() {
        CardCombination combination1 = mCardChecker.getCombination(Arrays.asList(CardModel.ACE, CardModel.QUEEN, CardModel.ACE, CardModel.KING, CardModel.QUEEN));
        Assert.assertEquals(CardCombination.TWO_PAIRS, combination1);
        CardCombination combination2 = mCardChecker.getCombination(Arrays.asList(CardModel.ACE, CardModel.QUEEN, CardModel.JACK, CardModel.KING, CardModel.TEN));
        Assert.assertNotEquals(CardCombination.TWO_PAIRS, combination2);
    }

    @Test()
    public void testThreeOfKind() {
        CardCombination combination1 = mCardChecker.getCombination(Arrays.asList(CardModel.ACE, CardModel.QUEEN, CardModel.ACE, CardModel.KING, CardModel.ACE));
        Assert.assertEquals(CardCombination.THREE_OF_KIND, combination1);
        CardCombination combination2 = mCardChecker.getCombination(Arrays.asList(CardModel.ACE, CardModel.QUEEN, CardModel.JACK, CardModel.KING, CardModel.TEN));
        Assert.assertNotEquals(CardCombination.THREE_OF_KIND, combination2);
    }

    @Test()
    public void testFullHouse() {
        CardCombination combination1 = mCardChecker.getCombination(Arrays.asList(CardModel.ACE, CardModel.QUEEN, CardModel.ACE, CardModel.QUEEN, CardModel.ACE));
        Assert.assertEquals(CardCombination.FULL_HOUSE, combination1);
        CardCombination combination2 = mCardChecker.getCombination(Arrays.asList(CardModel.ACE, CardModel.QUEEN, CardModel.JACK, CardModel.KING, CardModel.TEN));
        Assert.assertNotEquals(CardCombination.FULL_HOUSE, combination2);
    }

    @Test()
    public void testFourOfKind() {
        CardCombination combination1 = mCardChecker.getCombination(Arrays.asList(CardModel.QUEEN, CardModel.QUEEN, CardModel.ACE, CardModel.QUEEN, CardModel.QUEEN));
        Assert.assertEquals(CardCombination.FOUR_OF_KIND, combination1);
        CardCombination combination2 = mCardChecker.getCombination(Arrays.asList(CardModel.ACE, CardModel.QUEEN, CardModel.JACK, CardModel.KING, CardModel.TEN));
        Assert.assertNotEquals(CardCombination.FOUR_OF_KIND, combination2);
    }
}