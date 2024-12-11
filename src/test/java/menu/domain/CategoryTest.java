package menu.domain;

import static menu.domain.Category.ASIAN_FOOD;
import static menu.domain.Category.CHINESE_FOOD;
import static menu.domain.Category.JAPANESE_FOOD;
import static menu.domain.Category.KOREAN_FOOD;
import static menu.domain.Category.WESTERN_FOOD;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CategoryTest {

    @DisplayName("순서에 맞는 카테고리를 조회해 반환한다.")
    @MethodSource("provideSequenceAndExpectedCategory")
    @ParameterizedTest
    void 순서에_맞는_카테고리를_조회(int sequence, Category expectedCategory) {
        assertThat(Category.find(sequence)).isEqualTo(expectedCategory);
    }

    private static Stream<Arguments> provideSequenceAndExpectedCategory() {
        return Stream.of(
                Arguments.of(1, JAPANESE_FOOD),
                Arguments.of(2, KOREAN_FOOD),
                Arguments.of(3, CHINESE_FOOD),
                Arguments.of(4, ASIAN_FOOD),
                Arguments.of(5, WESTERN_FOOD)
        );
    }

}
