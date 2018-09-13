package plansnet.hse.blackjack.Model;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private Deck deck = new Deck();
    private List<Card> dealerHand = new ArrayList<>();
    private List<Card> playerHand = new ArrayList<>();

    public Game() {
        playerHand.add(deck.top());
        dealerHand.add(deck.top());

        playerHand.add(deck.top());
        dealerHand.add(deck.top());
    }

    public List<Card> getPlayerHand() {
        return playerHand;
    }

    public int getDealerHandSize() {
        return dealerHand.size();
    }


}
