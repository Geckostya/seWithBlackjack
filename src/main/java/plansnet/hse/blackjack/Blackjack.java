package plansnet.hse.blackjack;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import plansnet.hse.blackjack.Model.Card;
import plansnet.hse.blackjack.Model.Game;

import java.util.List;
import java.util.stream.Collectors;

public class Blackjack extends Application {
    private static final String pathToUI = "/plansnet/hse/blackjack/";

    @FXML
    private Label playerScore;
    @FXML
    private Label dealerScore;

    @FXML
    private Label playerDeck;
    @FXML
    private Label dealerDeck;

    private Game game = new Game();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage stage = primaryStage;
        Scene scene = openScene("Blackjack");
        stage.setScene(scene);
        stage.show();
    }

    private Scene openScene(String sceneName) throws Exception {
        Parent root = FXMLLoader
                .load(getClass().getResource(pathToUI + sceneName + ".fxml"));
        return new Scene(root);
    }

    @FXML
    private void initialize() {
        initScene();
    }

    private void initScene() {
        playerScore.setText(scores(game.getPlayerHand()));
        dealerScore.setText(scores(game.getDealerHand()));

        playerDeck.setText(deck(game.getPlayerHand()));
        dealerDeck.setText(deck(game.getDealerHand()));
    }

    private String scores(List<Card> hand) {
        return "Score: " + Game.CardEvaluator.getHandScore(hand);
    }

    private String deck(List<Card> hand) {
        return  "Deck: " + String.join(" ", hand.stream().map(Card::toString).collect(Collectors.toList()));
    }
}
