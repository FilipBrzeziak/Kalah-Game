package Kalah;

public class Main {
    public static void main(String[] args) {
        Server server = new Server(new Board());
        server.prepareGame();
    }
}
