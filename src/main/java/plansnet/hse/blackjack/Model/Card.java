package plansnet.hse.blackjack.Model;

public class Card {
    private int id;

    public Card(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        if (id == 1) return "A";
        if (id == 11) return "J";
        if (id == 12) return "Q";
        if (id == 13) return "K";
        return Integer.toString(id);
    }
}
