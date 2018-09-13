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
    public List<Card> getDealerHand() {
        return dealerHand;
    }

    public int getDealerHandSize() {
        return dealerHand.size();
    }

    public static class CardEvaluator {
        public static int getHandScore (List<Card> cards) {
            int aces = 0;
            int result = 0;
            for (Card c : cards) {
                int k = getCardScore(c);

                if (k == 1) {
                    aces++;
                }

                result += k;
            }

            while (aces > 0 && result + 10 <= 21) {
                result += 10;
                aces--;
            }

            return result;
        }

        private static int getCardScore(Card c) {
            if (c.getId() > 10) {
                return 10;
            }
            return c.getId();
        }
    }

}
