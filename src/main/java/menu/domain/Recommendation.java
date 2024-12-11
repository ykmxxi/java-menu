package menu.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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

    public Map<Coach, List<Menu>> recommendMenus(final List<Category> categories, final List<Coach> coaches) {
        Map<Coach, List<Menu>> menus = new LinkedHashMap<>();
        for (Category category : categories) {
            for (Coach coach : coaches) {
                Menu menu = Menus.find(category, getRandomMenu(category));
                List<Menu> coachMenus = menus.getOrDefault(coach, new ArrayList<>());
                while (isAlreadyEating(coachMenus, menu) || isCanNotEatableMenu(coach, menu)) {
                    menu = Menus.find(category, getRandomMenu(category));
                }
                menus.computeIfAbsent(coach, (value) -> new ArrayList<>()).add(menu);
            }
        }
        return menus;
    }

    private boolean isAlreadyEating(final List<Menu> menus, final Menu menu) {
        return menus.contains(menu);
    }

    private boolean isCanNotEatableMenu(final Coach coach, final Menu menu) {
        return !coach.isEatable(menu);
    }

    private String getRandomMenu(final Category category) {
        return Randoms.shuffle(category.menuNames())
                .getFirst();
    }

}
