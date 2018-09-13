package plansnet.hse.blackjack;

import org.junit.*;
import plansnet.hse.blackjack.Model.Card;
import plansnet.hse.blackjack.Model.Deck;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

public class DeckTests {
    @Test
    public void deckInit() {
        Deck deck = new Deck();
        int[] deckCheckArray = new int[13];
        Arrays.fill(deckCheckArray, 4);
        while (!deck.isEmpty()) {
            Card card = deck.top();
            int id = card.getId() - 1;
            deckCheckArray[id]--;
        }
        for (int a : deckCheckArray) {
            assertThat(a, is(0));
        }
    }

    @Test
    public void deckEmpty() {
        Deck deck = new Deck();
        for (int i = 0; i < 52; i++)
            deck.top();
        assertTrue(deck.isEmpty());
    }
}
