package menu.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import camp.nextstep.edu.missionutils.Randoms;

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

    public List<Menu> recommendMenus(final List<Category> categories, final Coach coach) {
        List<Menu> menus = new ArrayList<>();
        for (Category category : categories) {
            Menu menu = getRandomMenu(category);
            while (isAlreadyEating(menus, menu) || isCanNotEatableMenu(coach, menu)) {
                menu = getRandomMenu(category);
            }
            menus.add(menu);
        }
        return menus;
    }

    private boolean isAlreadyEating(final List<Menu> menus, final Menu menu) {
        return menus.contains(menu);
    }

    private boolean isCanNotEatableMenu(final Coach coach, final Menu menu) {
        return !coach.isEatable(menu);
    }

    private Menu getRandomMenu(final Category category) {
        String randomMenuName = Randoms.shuffle(category.menuNames())
                .getFirst();
        return category.findMenu(randomMenuName);
    }

}
