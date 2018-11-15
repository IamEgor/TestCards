package cards;

import java.util.Comparator;

public class StraightComparator implements Comparator<CardModel> {
    @Override
    public int compare(CardModel o1, CardModel o2) {
        return o1.compareTo(o2);
    }
}
