package menu.presentation;

import java.util.Arrays;
import java.util.List;

import menu.presentation.view.InputValidator;
import menu.presentation.view.InputView;
import menu.presentation.view.OutputView;
import menu.service.RecommendationService;

public class RecommendationClient {

    private final InputView inputView;
    private final OutputView outputView;
    private final RecommendationService recommendationService;

    public RecommendationClient(final InputView inputView, final OutputView outputView,
                                final RecommendationService recommendationService
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.recommendationService = recommendationService;
    }

    public void run() {
        outputView.printServiceStartMessage();
        List<String> coachNames = createMealGroup();
        addCanNotEatableMenus(coachNames);

    }

    private List<String> createMealGroup() {
        return RetryHandler.retry(() -> {
            List<String> coachNames = splitInput(inputView.readCoachNames());
            validateCoachNameInput(coachNames);
            return recommendationService.createMealGroup(coachNames);
        }, outputView);
    }

    private void addCanNotEatableMenus(final List<String> coachNames) {
        for (String coachName : coachNames) {
            RetryHandler.retry(() -> {
                List<String> canNotEatableMenus = splitInput(inputView.readCanNotEatableMenus(coachName));
                if (!canNotEatableMenus.isEmpty()) {
                    return recommendationService.addCanNotEatableMenus(coachName, canNotEatableMenus);
                }
                return true;
            }, outputView);
        }
    }

    private List<String> splitInput(final String input) {
        return Arrays.stream(input.split(","))
                .toList();
    }

    private void validateCoachNameInput(final List<String> coachNames) {
        for (String coachName : coachNames) {
            InputValidator.validate(coachName);
        }
    }

}
