package trivia;

public class Player {
    private final String name;
    private int place = 1;
    private int purse = 0;
    private boolean inPenaltyBox = false;

    public Player(String name) {
        this.name = name;
    }

    public void move(int roll, int boardSize) {
        this.place = ((this.place + roll - 1) % boardSize) + 1;
    }

    public void addGold() {
        this.purse++;
    }

    public String getName() {
        return name;
    }

    public int getPlace() {
        return place;
    }

    public int getPurse() {
        return purse;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public void setInPenaltyBox(boolean inPenaltyBox) {
        this.inPenaltyBox = inPenaltyBox;
    }

    @Override
    public String toString() {
        return name;
    }
}