package menu.presentation.view;

import java.util.List;

public class OutputView {

    public void printErrorMessage(final String message) {
        System.out.println(String.join(
                " ", "[ERROR]", message, "다시 입력해주세요."
        ));
    }

    public void printServiceStartMessage() {
        System.out.println("점심 메뉴 추천을 시작합니다.");
        System.out.println();
    }

    public void printServiceEndMessage() {
        System.out.println("추천을 완료했습니다.");
    }

    public void printCategories(final List<String> recommendCategories) {
        System.out.println("메뉴 추천 결과입니다.");
        System.out.println("[ 구분 | 월요일 | 화요일 | 수요일 | 목요일 | 금요일 ]");
        System.out.println(format("카테고리", recommendCategories));
    }

    public void printMenus(final List<String> coachNames, final List<List<String>> recommendMenus) {
        for (int i = 0; i < coachNames.size(); i++) {
            System.out.println(format(coachNames.get(i), recommendMenus.get(i)));
        }
    }

    private String format(final String type, final List<String> outputs) {
        String join = String.join(" | ", type, String.join(" | ", outputs));
        return "[ " + join + " ]";

    }

}
