package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class Application {
    private static final int NUMBER_SIZE = 3;
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        List<Integer> computer = getRandomNum(); //컴퓨터 수 임의지정
        gameStart(computer);
    }

    private static void gameStart(List<Integer> computer) {
        while (true) {
            List<Integer> user = inputUser();

            int[] result = calculateStrikeAndBall(computer, user);
            computer = gameResult(result[0], result[1], computer);
            if (computer == null) return;
        }
    }

    private static int[] calculateStrikeAndBall(List<Integer> computer,List<Integer> user) {
        int strike = 0;
        int ball = 0;
        for (int i = 0; i < NUMBER_SIZE; i++) {
            if (user.get(i).equals(computer.get(i))) {
                strike++;
            } else if (user.contains(computer.get(i))) {
                ball++;
            }
        }
        return new int[]{strike,ball};
    }

    private static List<Integer> gameResult(int strike, int ball, List<Integer> computer) {
        if (strike == NUMBER_SIZE) {
            System.out.println("3스트라이크");
            System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
            System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
            int selectNextGame = Integer.parseInt(Console.readLine());
            if (selectNextGame == 2) {
                return null;
            }// 종료후 게임 종료 or 재시작 로직 구현
            computer = getRandomNum();
        } else if (strike == 0 && ball == 0) {
            System.out.println("낫싱");
        } else if (strike > 0 && ball > 0) {
            System.out.println(ball + "볼 " + strike + "스트라이크");
        } else if (strike > 0) {
            System.out.println(strike + "스트라이크");
        } else if (ball > 0) {
            System.out.println(ball + "볼");
        }
        return computer;
    }

    private static List<Integer> inputUser() {
        List<Integer> user = new ArrayList<>();
        String input = Console.readLine();
        exception(input); //잘못된 값 입력 시 예외 처리
        for (int i = 0; i < NUMBER_SIZE; i++) {
            user.add(input.charAt(i) - '0');
        }
        return user;
    }

    private static void exception(String input) {
        if (input.length() != NUMBER_SIZE) {
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

    private static List<Integer> getRandomNum() {
        List<Integer> computer = new ArrayList<>();
        while (computer.size() < NUMBER_SIZE) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!computer.contains(randomNumber)) {
                computer.add(randomNumber);
            }
        }
        return computer;
    }


}
