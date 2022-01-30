package Kalah;

public class SimpleComputer implements Player {
    @Override
    public int chooseAMove(int team, Hole[] actualBoard, int secondsPerMove) {
        int number = -1;
        while (true) {
            number = calculateRandomResult();
            if (number < 6 && number >= 0 && team == 1 && actualBoard[number].getStones() != 0) {
                return number;
            }
            if (number < 6 && number >= 0 && team == 2 && actualBoard[number + 7].getStones() != 0) {
                return number;
            }
        }

    }

    public static int calculateRandomResult() {
        return (int) (Math.random() * 6);
    }
}
