package menu.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MealGroupTest {

    @DisplayName("식사 그룹 인원이 2명 미만이거나 5명 초과인 경우 예외가 발생한다.")
    @MethodSource("provideOutOfRangeCoaches")
    @ParameterizedTest
    void 식사_그룹_생성_실패_인원수_벗어남(List<Coach> coaches) {
        assertThatThrownBy(() -> new MealGroup(coaches))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("최소 2명, 최대 5명까지 식사를 함께할 수 있습니다.");
    }

    @DisplayName("중복된 이름을 갖는 코치가 존재하면 예외가 발생한다.")
    @Test
    void 식사_그룹_생성_실패_중복된_이름_코치_존재() {
        assertThatThrownBy(() -> new MealGroup(coaches("포비", "포비")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복된 이름의 코치가 존재합니다.");
    }

    private static Stream<Arguments> provideOutOfRangeCoaches() {
        return Stream.of(
                Arguments.of(coaches("포비")),
                Arguments.of(coaches("포비", "구구", "제임스", "토미", "포코", "길동"))
        );
    }

    private static List<Coach> coaches(final String... names) {
        List<Coach> coaches = new ArrayList<>();
        for (String name : names) {
            coaches.add(new Coach(name, List.of()));
        }
        return coaches;
    }

}
