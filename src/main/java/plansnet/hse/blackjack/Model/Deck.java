package plansnet.hse.blackjack.Model;

import java.util.ArrayDeque;
import java.util.Deque;

public class Deck {
    Deque<Card> cards = new ArrayDeque<>();

    public Deck() {
        for (int i = 1; i < 14; i++) {
            cards.add(new Card(i));
            cards.add(new Card(i));
            cards.add(new Card(i));
            cards.add(new Card(i));
        }
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public Card top() {
        return cards.removeFirst();
    }
}
