package cards;

import java.util.Comparator;

public class StreetComparator implements Comparator<CardModel> {
    @Override
    public int compare(CardModel o1, CardModel o2) {
        return o1.compareTo(o2);
    }
}
