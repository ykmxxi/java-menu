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
    }

    private List<String> createMealGroup() {
        return RetryHandler.retry(() -> {
            List<String> coachNames = splitInput();
            validateCoachNameInput(coachNames);
            return recommendationService.createMealGroup(coachNames);
        }, outputView);
    }

    private List<String> splitInput() {
        return Arrays.stream(inputView.readCoachNames()
                .split(",")
        ).toList();
    }

    private void validateCoachNameInput(final List<String> coachNames) {
        for (String coachName : coachNames) {
            InputValidator.validate(coachName);
        }
    }

}
