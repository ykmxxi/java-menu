package menu.service;

import java.util.ArrayList;
import java.util.List;

import menu.domain.Category;
import menu.domain.Coach;
import menu.domain.MealGroup;
import menu.domain.Menu;
import menu.domain.Menus;
import menu.domain.Recommendation;

public class RecommendationService {

    private final Recommendation recommendation;
    private MealGroup mealGroup;

    public RecommendationService() {
        this.recommendation = new Recommendation();
    }

    public List<String> createMealGroup(final List<String> coachNames) {
        List<Coach> coaches = new ArrayList<>();
        for (String coachName : coachNames) {
            Coach coach = new Coach(coachName);
            coaches.add(coach);
        }
        mealGroup = new MealGroup(coaches);
        return coaches.stream()
                .map(Coach::name)
                .toList();
    }

    public boolean addCanNotEatableMenus(final String coachName, final List<String> canNotEatableMenus) {
        Coach coach = mealGroup.findCoach(coachName);
        if (canNotEatableMenus.isEmpty()) {
            return true;
        }
        mealGroup.addCanNotEatableMenus(coach, canNotEatableMenus.stream()
                .map(Menus::findByName)
                .toList());
        return true;
    }

    public List<String> recommendCategories() {
        List<Integer> numbers = getRandomNumbers();
        List<Category> recommendCategories = recommendation.recommendCategories(numbers);
        return recommendCategories.stream()
                .map(Category::getName)
                .toList();
    }

    public List<String> recommendMenus(final String coachName, final List<String> recommendCategories) {
        List<Category> categories = new ArrayList<>();
        for (String recommendCategory : recommendCategories) {
            categories.add(Category.findByName(recommendCategory));
        }
        return recommendation.recommendMenus(categories, mealGroup.findCoach(coachName)).stream()
                .map(Menu::name)
                .toList();
    }

    private List<Integer> getRandomNumbers() {
        RandomNumbersGenerator randomNumbersGenerator = new RandomNumbersGenerator();
        List<Integer> numbers = randomNumbersGenerator.generate();
        while (!recommendation.isValidSequences(numbers)) {
            numbers = randomNumbersGenerator.generate();
        }
        return numbers;
    }

}
