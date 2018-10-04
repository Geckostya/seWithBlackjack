package plansnet.hse.blackjack.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    private Deck deck = new Deck();
    private boolean who;
    private boolean selfPass;
    private boolean otherPass;
    private List<Card> selfHand = new ArrayList<>();
    private List<Card> otherHand = new ArrayList<>();

    public Game() {
        otherHand.add(deck.top());
        selfHand.add(deck.top());

        otherHand.add(deck.top());
        selfHand.add(deck.top());

        who = new Random().nextBoolean();
        selfPass = false;
        otherPass = false;
    }

    public List<Card> getOtherHand() {
        return otherHand;
    }

    public List<Card> getSelfHand() {
        return selfHand;
    }

    public static class CardEvaluator {
        public static int getHandScore (List<Card> cards) {
            int aces = 0;
            int result = 0;
            for (Card c : cards) {
                int k = getCardScore(c);
                result += k;

                if (k == 1) {
                    aces++;
                }
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


    private int getSelfHandScore() {
        return CardEvaluator.getHandScore(otherHand);
    }
    private int getOtherHandScore() {
        return CardEvaluator.getHandScore(selfHand);
    }

    public void getCard() {
        List<Card> cards = (who ? otherHand : selfHand);

        cards.add(deck.top());

        if ((who && !selfPass) || (!who && !otherPass)) {
            who = !who;
        }
    }

    public void pass() {
        if (who) {
            otherPass = true;
        } else {
            selfPass = true;
        }
        who = !who;
    }

    public boolean who() {
        return who;
    }

    public boolean isOver() {
        return getSelfHandScore() > 21 || getOtherHandScore() > 21 || (selfPass && otherPass);
    }

    public boolean getWinner() {
        if (getSelfHandScore() > 21) {
            return true;
        }
        if (getOtherHandScore() > 21) {
            return false;
        }
        return getSelfHandScore() >= getOtherHandScore();
    }

}
