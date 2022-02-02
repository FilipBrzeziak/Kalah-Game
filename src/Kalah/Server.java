package Kalah;

import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Server {
    Board board;

    public Server(Board board) {
        this.board = board;
    }


    public void game(Player player1, Player player2, int secondsPerMove) {
        SimpleComputer bot = new SimpleComputer();
        boolean anotherMove = true;
        while (true) {
            while (anotherMove) {
                board.showBoard();
                if (board.isOver()) {
                    return;
                }
                final int[] move = new int[1];
                System.out.print("PLAYER 1 ");
                CompletableFuture<Void> future = CompletableFuture.runAsync(() -> move[0] = player1.chooseAMove(1, Board.copyBoard(board.getBoard()), secondsPerMove));
                try {
                    future.get(secondsPerMove, TimeUnit.SECONDS);
                } catch (Exception e) {
                    // Operation timed out
                    future.cancel(true);
                    System.out.println("no data\nWe lost connection with player 1 \nBot will take over his role!");
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    move[0] = bot.chooseAMove(1, Board.copyBoard(board.getBoard()), secondsPerMove);
                }
                anotherMove = board.moveStones(move[0], 1);
            }
            anotherMove = true;
            while (anotherMove) {
                board.showBoard();
                if (board.isOver()) {
                    return;
                }
                final int[] move = new int[1];
                System.out.print("PLAYER 2 ");
                CompletableFuture<Void> future = CompletableFuture.runAsync(() -> move[0] = player2.chooseAMove(2, Board.copyBoard(board.getBoard()), secondsPerMove));
                try {
                    future.get(secondsPerMove, TimeUnit.SECONDS);
                } catch (Exception e) {
                    // Operation timed out
                    future.cancel(true);
                    System.out.println("no data\nWe lost connection with player 2 \nBot will take over his role!");
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    move[0] = bot.chooseAMove(2, Board.copyBoard(board.getBoard()), secondsPerMove);
                }
                anotherMove = board.moveStones(move[0], 2);
            }
            anotherMove = true;


        }
    }

    public void play(Player player1, Player player2, int secondsPerMove) {
        game(player1, player2, secondsPerMove);
        board.showBoard();
        System.out.println("\n\n\nGAME OVER");
        if (board.whoWon() == 0) {
            System.out.println("Draw! ");
        } else {
            System.out.println("Player numer " + board.whoWon() + " is the winner!");
        }
    }

    public void prepareGame() {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("KALAH GAME by Filip Brzeziak\n");
                System.out.print("Enter the time limit for movement in seconds: ");
                int seconds = scanner.nextInt();
                System.out.println("ATTENTION!\nAfter " + seconds + "s of inactivity, bot will take your place!\n");
                System.out.println("Select a game mode");
                System.out.println("\tPLAYER 1\tPLAYER 2");
                System.out.println("1. Computer\tvs\tComputer");
                System.out.println("2. Computer\tvs\tHuman");
                System.out.println("3. Human\tvs\tComputer");
                System.out.println("4. Human\tvs\tHuman");
                System.out.print("Choice:");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1: {
                        System.out.print("Select the level of player 1:\n1. Easy\n2. Medium\n3. Hard\nChoice:");
                        int level1 = scanner.nextInt();
                        System.out.print("Select the level of player 2:\n1. Easy\n2. Medium\n3. Hard\nChoice:");
                        int level2 = scanner.nextInt();
                        play(new Computer(level1), new Computer(level2), seconds);
                        break;
                    }
                    case 2: {
                        System.out.print("Select the level of player 1:\n1. Easy\n2. Medium\n3. Hard\nChoice:");
                        int level1 = scanner.nextInt();
                        play(new Computer(level1), new Human(), seconds);
                        break;
                    }
                    case 3: {
                        System.out.print("Select the level of player 2:\n1. Easy\n2. Medium\n3. Hard\nChoice:");
                        int level2 = scanner.nextInt();
                        play(new Human(), new Computer(level2), seconds);
                        break;
                    }
                    case 4: {
                        play(new Human(), new Human(), seconds);
                        break;
                    }
                }
                System.out.print("If you want to exit press any number, if you want to play again press 0:");
                int number = scanner.nextInt();
                if (number != 0) {
                    return;
                }
                this.board=new Board();
            } catch (Exception e) {
                System.out.println("Wrong input!");
            }
        }
    }
}
