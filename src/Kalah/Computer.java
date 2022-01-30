package Kalah;

public class Computer implements Player {
    private int levelOfDifficulty;

    public Computer(int levelOfDifficulty) {
        this.levelOfDifficulty = levelOfDifficulty * 3;
    }

    @Override
    public int chooseAMove(int team, Hole[] actualBoard, int secondsPerMove) {
        return calculateBestResult(actualBoard, levelOfDifficulty, team, team);
    }

    public static int calculateBestResult(Hole[] actualBoard, int depth, int actualTeam, int rootTeam) {
        Board newBoard = new Board(Board.copyBoard(actualBoard));
        if (depth == 0 || newBoard.isOver()) {
            return newBoard.calculateTheDifference(actualTeam, newBoard.getBoard());
        } else {
            if (actualTeam == rootTeam) {
                int max = Integer.MIN_VALUE;
                int maxIndex = -1;
                for (int i = 0; i < 6; i++) {
                    Board insideBoard = new Board(Board.copyBoard(newBoard.getBoard()));
                    if ((actualTeam == 1 && insideBoard.getBoard()[i].getStones() != 0) || (actualTeam == 2 && insideBoard.getBoard()[i + 7].getStones() != 0)) {
                        insideBoard.moveStones(i, actualTeam);
                        int currentResult = calculateBestResult(insideBoard.getBoard(), depth - 1, insideBoard.nextTeam(actualTeam), rootTeam);
                        if (currentResult > max) {
                            max = currentResult;
                            maxIndex = i;
                        }
                    }
                }
                return maxIndex;
            } else {
                int min = Integer.MAX_VALUE;
                int minIndex = -1;
                for (int i = 0; i < 6; i++) {
                    Board insideBoard = new Board(Board.copyBoard(newBoard.getBoard()));
                    if ((actualTeam == 1 && insideBoard.getBoard()[i].getStones() != 0) || (actualTeam == 2 && insideBoard.getBoard()[i + 7].getStones() != 0)) {
                        insideBoard.moveStones(i, actualTeam);
                        int currentResult = calculateBestResult(insideBoard.getBoard(), depth - 1, insideBoard.nextTeam(actualTeam), rootTeam);
                        if (currentResult < min) {
                            min = currentResult;
                            minIndex = i;
                        }
                    }
                }
                return minIndex;
            }
        }
    }
}
