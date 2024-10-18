package baseball;

import java.util.ArrayList;
import java.util.List;

public class BaseballController {
    private final BaseballModel baseballModel;
    private final BaseballView baseballView;
    private final List<Integer> user;

    public BaseballController() {
        baseballModel = new BaseballModel();
        baseballView = new BaseballView();
        user = new ArrayList<>();
    }

    public void gameStart() {
        System.out.println("숫자 야구 게임을 시작합니다.");
        while (true) {
            user.clear();
            String userInput = baseballView.getUserInput();
            exception(userInput);
            for (int i = 0; i < Constants.NUMBER_SIZE; i++) {
                user.add(userInput.charAt(i) - '0');
            }
            int[] result = baseballModel.calculateStrikeAndBall(user);
            if (result[0] == 3) { //게임 승리
                baseballView.printWinMessage();
                int restart = baseballView.askForRestart();
                if (restart == 1) {
                    baseballModel.getNewComputerNum();
                } else if (restart == 2) {
                    return;
                } else {
                    throw new IllegalArgumentException("1,2만 입력하세요");
                }
            } else {
                baseballView.printResult(result[0], result[1]);
            }
        }
    }

    private static void exception(String input) {
        if (input.length() != Constants.NUMBER_SIZE) {
            throw new IllegalArgumentException("3개의 값이 들어가야 합니다.");
        }

        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("정수형 변환 불가");
        }

        if (hasDuplicatedDigits(input)) {
            throw new IllegalArgumentException("3개의 숫자는 모두 달라야 합니다.");
        }

    }

    private static boolean hasDuplicatedDigits(String input) {
        for (int i = 0; i < input.length(); i++) {
            for (int j = i + 1; j < input.length(); j++) {
                if (input.charAt(i) == input.charAt(j)) {
                    return true;
                }
            }
        }
        return false;
    }
}
