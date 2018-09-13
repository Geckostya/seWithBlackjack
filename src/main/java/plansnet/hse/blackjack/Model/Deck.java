package plansnet.hse.blackjack.Model;

import java.util.*;

public class Deck {
    Deque<Card> cards = new ArrayDeque<>();

    public Deck() {
        List<Card> ordered = new ArrayList<>();
        for (int i = 1; i < 14; i++) {
            ordered.add(new Card(i));
            ordered.add(new Card(i));
            ordered.add(new Card(i));
            ordered.add(new Card(i));
        }
        Collections.shuffle(ordered);
        cards.addAll(ordered);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public Card top() {
        return cards.removeFirst();
    }
}
