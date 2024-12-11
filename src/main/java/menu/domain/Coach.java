package menu.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Coach {

    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 4;
    private static final int MIN_CAN_NOT_EATABLE_MENU_COUNT = 0;
    private static final int MAX_CAN_NOT_EATABLE_MENU_COUNT = 2;

    private final String name;
    private final List<Menu> canNotEatableMenus = new ArrayList<>();

    public Coach(final String name) {
        validate(name, canNotEatableMenus);
        this.name = name;
    }

    public void addCanNotEatableMenus(final List<Menu> menus) {
        validateCanNotEatableMenusCount(menus);
        canNotEatableMenus.addAll(menus);
    }

    public boolean isEatable(final Menu menu) {
        return !canNotEatableMenus.contains(menu);
    }

    private void validate(final String name, final List<Menu> canNotEatableMenus) {
        validateNameLength(name);
    }

    private void validateNameLength(final String name) {
        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("코치의 이름은 최소 %d글자, 최대 %d글자입니다.".formatted(
                    MIN_NAME_LENGTH, MAX_NAME_LENGTH
            ));
        }
    }

    private void validateCanNotEatableMenusCount(final List<Menu> canNotEatableMenus) {
        if (!canNotEatableMenus.isEmpty() && canNotEatableMenus.size() > MAX_CAN_NOT_EATABLE_MENU_COUNT) {
            throw new IllegalArgumentException("못먹는 음식은 최소 %d개, 최대 %d개 가능합니다.".formatted(
                    MIN_CAN_NOT_EATABLE_MENU_COUNT, MAX_CAN_NOT_EATABLE_MENU_COUNT
            ));
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Coach coach)) {
            return false;
        }
        return Objects.equals(name, coach.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

}
