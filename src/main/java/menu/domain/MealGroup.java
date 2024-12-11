package menu.domain;

import java.util.HashSet;
import java.util.List;

public class MealGroup {

    private static final int MIN_GROUP_COUNT = 2;
    private static final int MAX_GROUP_COUNT = 5;

    private final List<Coach> coaches;

    public MealGroup(final List<Coach> coaches) {
        validate(coaches);
        this.coaches = coaches;
    }

    private void validate(final List<Coach> coaches) {
        validateGroupCount(coaches);
        validateNameDuplication(coaches);
    }

    public Coach findCoach(final String coachName) {
        for (Coach coach : coaches) {
            if (coach.hasSameName(coachName)) {
                return coach;
            }
        }
        throw new IllegalArgumentException("존재하지 않는 코치입니다.");
    }

    public void addCanNotEatableMenus(final Coach target, final List<Menu> menus) {
        for (Coach coach : coaches) {
            if (coach.equals(target)) {
                coach.addCanNotEatableMenus(menus);
            }
        }
    }

    private void validateGroupCount(final List<Coach> coaches) {
        if (coaches.size() < MIN_GROUP_COUNT || coaches.size() > MAX_GROUP_COUNT) {
            throw new IllegalArgumentException("최소 %d명, 최대 %d명까지 식사를 함께할 수 있습니다.".formatted(
                    MIN_GROUP_COUNT, MAX_GROUP_COUNT
            ));
        }
    }

    private void validateNameDuplication(final List<Coach> coaches) {
        if (new HashSet<>(coaches).size() != coaches.size()) {
            throw new IllegalArgumentException("중복된 이름의 코치가 존재합니다.");
        }
    }

}
