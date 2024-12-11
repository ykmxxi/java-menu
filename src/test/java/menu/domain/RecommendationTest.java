package menu.domain;

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

    @Test
    void 카테고리_추천() {
        List<Integer> sequences = List.of(1, 2, 3, 4, 5);

        List<Category> recommendCategories = recommendation.recommendCategories(sequences);

        assertThat(recommendCategories).isEqualTo(Arrays.stream(Category.values())
                .toList());
    }

    private static Stream<Arguments> provideSequencesAndExpected() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 2, 3, 3), true),
                Arguments.of(List.of(1, 2, 2, 2, 3), false)
        );
    }

}
