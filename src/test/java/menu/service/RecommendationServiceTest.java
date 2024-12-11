package menu.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import menu.domain.Recommendation;

class RecommendationServiceTest {

    private RecommendationService recommendationService;

    @BeforeEach
    void setUp() {
        recommendationService = new RecommendationService(new Recommendation());
    }

    @DisplayName("카테고리 5개를 추천한다.")
    @Test
    void 카테고리_추천() {
        List<String> recommendCategories = recommendationService.recommendCategories();

        assertThat(recommendCategories).hasSize(5);
    }

}
