package menu.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CoachTest {

    private Menu menu;

    @BeforeEach
    void setUp() {
        menu = new Menu("규동", Category.JAPANESE_FOOD);
    }

    @DisplayName("코치의 이름 길이가 범위를 벗어나면 예외가 발생한다.")
    @ValueSource(strings = {"포", "포비비비비"})
    @ParameterizedTest
    void 코치_생성_실패_이름_범위_벗어남(String name) {
        assertThatThrownBy(() -> new Coach(name, List.of()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("코치의 이름은 최소 2글자, 최대 4글자입니다.");
    }

    @DisplayName("못먹는 음식이 2개를 초과하면 예외가 발생한다")
    @Test
    void 코치_생성_실패_못먹는_음식_개수_초과() {
        assertThatThrownBy(() -> new Coach("포비", List.of(menu, menu, menu)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("못먹는 음식은 최소 0개, 최대 2개 가능합니다.");
    }

    @DisplayName("이름과 못먹는 음식을 알려주면 코치를 생성한다.")
    @Test
    void 코치_생성_성공() {
        assertDoesNotThrow(() -> new Coach("포비", List.of(menu)));
    }

}