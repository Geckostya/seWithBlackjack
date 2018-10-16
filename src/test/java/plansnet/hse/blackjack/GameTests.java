package plansnet.hse.blackjack;

import org.junit.Test;
import plansnet.hse.blackjack.Model.Card;
import plansnet.hse.blackjack.Model.Game;
import plansnet.hse.blackjack.Model.Game.CardEvaluator;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class GameTests {
    @Test
    public void gameInit() {
        Game game = new Game();
        List<Card> playerHand = game.getOtherHand();
        List<Card> otherHand = game.getOtherHand();

        assertThat(playerHand.size(), is(2));
        assertThat(otherHand.size(), is(2));

        assertNotNull(playerHand.get(0));
        assertNotNull(playerHand.get(1));

        assertNotNull(otherHand.get(0));
        assertNotNull(otherHand.get(1));
    }

    @Test
    public void testWho() {
        Game game = new Game();
        boolean who = game.who();
        game.getCard();
        assertNotEquals(who, game.who());
        who = game.who();
        game.getCard();
        assertNotEquals(who, game.who());
    }

    @Test
    public void testGetCard() {
        Game game = new Game();
        boolean who = game.who();
        game.getCard();
        if (!who) {
            assertEquals(game.getSelfHand().size(), 3);
            assertEquals(game.getOtherHand().size(), 2);
        } else {
            assertEquals(game.getSelfHand().size(), 2);
            assertEquals(game.getOtherHand().size(), 3);
        }
    }

    @Test
    public void testGameIsOver() {
        Game game = new Game();
        for (int i = 0; i < 24; i++) {
            game.getCard();
        }
        assertTrue(game.isOver());
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
