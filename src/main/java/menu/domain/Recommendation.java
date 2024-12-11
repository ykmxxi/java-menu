package menu.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Recommendation {

    private static final int MAX_RECOMMENDATION_COUNT = 2;

    public boolean isValidSequences(final List<Integer> sequences) {
        Map<Integer, Integer> numberCount = new HashMap<>();
        for (int sequence : sequences) {
            numberCount.put(sequence, numberCount.getOrDefault(sequence, 0) + 1);
            if (numberCount.get(sequence) > MAX_RECOMMENDATION_COUNT) {
                return false;
            }
        }
        return true;
    }

    public List<Category> recommendCategories(final List<Integer> sequences) {
        return sequences.stream()
                .map(Category::find)
                .toList();
    }

}
