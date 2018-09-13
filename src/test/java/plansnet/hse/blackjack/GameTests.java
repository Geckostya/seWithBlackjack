package plansnet.hse.blackjack;

import org.junit.Test;
import plansnet.hse.blackjack.Model.Card;
import plansnet.hse.blackjack.Model.Deck;
import plansnet.hse.blackjack.Model.Game;

import java.util.Arrays;
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


}
