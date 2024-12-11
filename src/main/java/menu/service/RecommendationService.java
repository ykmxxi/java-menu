package menu.service;

import java.util.List;

import menu.domain.Category;
import menu.domain.Recommendation;

public class RecommendationService {

    private final Recommendation recommendation;

    public RecommendationService(final Recommendation recommendation) {
        this.recommendation = recommendation;
    }

    public List<String> recommendCategories() {
        Recommendation recommendation = new Recommendation();
        List<Integer> numbers = getRandomNumbers();
        List<Category> recommendCategories = recommendation.recommendCategories(numbers);
        return recommendCategories.stream()
                .map(Category::getName)
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
