package menu.service;

import java.util.List;
import java.util.stream.IntStream;

import camp.nextstep.edu.missionutils.Randoms;

public class RandomNumbersGenerator {

    private static final int NUMBERS_SIZE = 5;
    private static final int START_INCLUSIVE = 1;
    private static final int END_INCLUSIVE = 5;

    public List<Integer> generate() {
        return IntStream.range(0, NUMBERS_SIZE)
                .mapToObj(i -> Randoms.pickNumberInRange(START_INCLUSIVE, END_INCLUSIVE))
                .toList();
    }

}
