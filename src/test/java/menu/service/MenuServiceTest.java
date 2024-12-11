package menu.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import menu.domain.Recommendation;

class MenuServiceTest {

    @DisplayName("카테고리 5개를 추천한다.")
    @Test
    void 카테고리_추천() {
        MenuService menuService = new MenuService(new Recommendation());

        List<String> recommendCategories = menuService.recommendCategories();

        assertThat(recommendCategories).hasSize(5);
    }

}
