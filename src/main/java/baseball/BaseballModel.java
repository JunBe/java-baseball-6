package baseball;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

public class BaseballModel {
    private List<Integer> computer;
    public BaseballModel() {
        this.computer = generateRandomNum();
    }

    public List<Integer> getNewComputerNum() {
        return this.computer = generateRandomNum();
    }

    public int[] calculateStrikeAndBall(List<Integer> user) {
        int strike = 0;
        int ball = 0;
        for (int i = 0; i < Constants.NUMBER_SIZE; i++) {
            if (user.get(i).equals(computer.get(i))) {
                strike++;
            } else if (user.contains(computer.get(i))) {
                ball++;
            }
        }
        return new int[]{strike, ball};
    }


    public List<Integer> generateRandomNum() {
        List<Integer> number = new ArrayList<>();
        while (number.size() < Constants.NUMBER_SIZE) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!number.contains(randomNumber)) {
                number.add(randomNumber);
            }
        }
        return number;
    }
}
