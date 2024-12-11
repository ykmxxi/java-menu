package menu.domain;

import java.util.Arrays;

public enum Category {

    JAPANESE_FOOD(1),
    KOREAN_FOOD(2),
    CHINESE_FOOD(3),
    ASIAN_FOOD(4),
    WESTERN_FOOD(5);

    private final int sequence;

    Category(final int sequence) {
        this.sequence = sequence;
    }

    public static Category find(final int sequence) {
        return Arrays.stream(values())
                .filter(category -> category.sequence == sequence)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리 순서입니다."));
    }

}
