package plansnet.hse.blackjack;

import org.junit.Test;
import plansnet.hse.blackjack.Model.Card;
import plansnet.hse.blackjack.Model.Deck;
import plansnet.hse.blackjack.Model.Game;
import plansnet.hse.blackjack.Model.Game.CardEvaluator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class GameTests {
    @Test
    public void gameInit() {
        Game game = new Game();
        List<Card> playerHand = game.getPlayerHand();
        int dealerHandSize = game.getDealerHandSize();

        assertThat(playerHand.size(), is(2));
        assertThat(dealerHandSize, is(2));

        assertNotNull(playerHand.get(0));
        assertNotNull(playerHand.get(1));
    }

    @Test
    public void testScore() {
        List<Card> cards = Arrays.asList(new Card(2), new Card(3), new Card(4));
        assertThat(CardEvaluator.getHandScore(cards), is(9));

        cards = Arrays.asList(new Card(11), new Card(13));
        assertThat(CardEvaluator.getHandScore(cards), is(20));

        cards = Arrays.asList(new Card(1), new Card(11));
        assertThat(CardEvaluator.getHandScore(cards), is(21));

        cards = Arrays.asList(new Card(1), new Card(6), new Card(7));
        assertThat(CardEvaluator.getHandScore(cards), is(14));

        cards = Arrays.asList(new Card(1), new Card(1));
        assertThat(CardEvaluator.getHandScore(cards), is(12));
    }
}
