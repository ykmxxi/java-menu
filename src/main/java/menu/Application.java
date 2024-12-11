package menu;

import menu.presentation.RecommendationClient;
import menu.presentation.view.InputView;
import menu.presentation.view.OutputView;
import menu.service.RecommendationService;

public class Application {

    public static void main(String[] args) {
        RecommendationClient recommendationClient =
                new RecommendationClient(new InputView(), new OutputView(), new RecommendationService());
        recommendationClient.run();
    }

}
