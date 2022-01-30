package Kalah;

public class Hole {
    private int stones;
    private final int id;
    private final int team;
    private final int oppositeId;

    public Hole(int stones, int id, int team, int oppositeId) {
        this.stones = stones;
        this.id = id;
        this.team = team;
        this.oppositeId = oppositeId;
    }

    public Hole(Hole anotherNode) {
        this.stones = anotherNode.stones;
        this.id = anotherNode.id;
        this.team = anotherNode.team;
        this.oppositeId = anotherNode.oppositeId;
    }


    public int getOppositeId() {
        return oppositeId;
    }

    public int getStones() {
        return stones;
    }

    public void setStones(int stones) {
        this.stones = stones;
    }

    @Override
    public String toString() {
        return id + "[" + stones + "]";
    }
}
