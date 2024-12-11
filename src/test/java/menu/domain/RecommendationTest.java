package menu.domain;

import static menu.domain.Category.ASIAN_FOOD;
import static menu.domain.Category.JAPANESE_FOOD;
import static menu.domain.Category.KOREAN_FOOD;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RecommendationTest {

    private Recommendation recommendation;

    @BeforeEach
    void setUp() {
        recommendation = new Recommendation();
    }

    @DisplayName("추천을 위한 시퀀스들이 최대 2회인지 확인한다.")
    @MethodSource("provideSequencesAndExpected")
    @ParameterizedTest
    void 시퀀스_검증(List<Integer> sequences, boolean expected) {
        assertThat(recommendation.isValidSequences(sequences)).isEqualTo(expected);
    }

    @DisplayName("카테고리는 5개를 추천한다.")
    @Test
    void 카테고리_추천() {
        List<Integer> sequences = List.of(1, 2, 3, 4, 5);

        List<Category> recommendCategories = recommendation.recommendCategories(sequences);

        assertThat(recommendCategories).isEqualTo(Arrays.stream(Category.values())
                .toList());
    }

    @DisplayName("카테고리에 맞는 메뉴 5개를 중복된 메뉴 없이 추천한다.")
    @Test
    void 메뉴_추천() {
        Coach coach = new Coach("포비");
        List<Category> categories = List.of(JAPANESE_FOOD, JAPANESE_FOOD, KOREAN_FOOD, KOREAN_FOOD, ASIAN_FOOD);

        List<Menu> menus = recommendation.recommendMenus(categories, coach);

        assertThat(menus).hasSize(5);
        assertThat(menus).doesNotHaveDuplicates();
    }

    private static Stream<Arguments> provideSequencesAndExpected() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 2, 3, 3), true),
                Arguments.of(List.of(1, 2, 2, 2, 3), false)
        );
    }

}
