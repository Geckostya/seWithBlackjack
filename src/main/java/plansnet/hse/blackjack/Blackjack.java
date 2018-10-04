package plansnet.hse.blackjack;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import plansnet.hse.blackjack.Model.Card;
import plansnet.hse.blackjack.Model.Game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Blackjack extends Application {
    private static final String pathToUI = "/plansnet/hse/blackjack/";

    @FXML
    private Button serverButton;
    @FXML
    private Button clientButton;

    @FXML
    private TextField otherIp;
    @FXML
    private Label myIp;

    @FXML
    private Label selfScore;
    @FXML
    private Label otherScore;

    @FXML
    private Label selfDeck;
    @FXML
    private Label otherDeck;

    private Game game = new Game();

    enum Turn {
        GET,
        PASS
    }

    enum Result {
        SERVER_WON,
        CLIENT_WON,
        IN_PROGRESS
    }

    static private class State {
        public Result result;
        public ArrayList<Card> clientHand;
        public ArrayList<Card> serverHand;
    }

    interface Player {
        State getStateAfterNewTurn(Turn turn) throws IOException;
    }

    private Player player;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = openScene();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Scene openScene() throws Exception {
        Parent root = FXMLLoader
                .load(getClass().getResource(pathToUI + "Blackjack.fxml"));
        return new Scene(root);
    }

    @FXML
    private void initialize() {
        updateScene();
    }

    private void updateScene() {
        selfScore.setText("Self " + scores(game.getSelfHand()));
        otherScore.setText("Other " + scores(game.getOtherHand()));

        selfDeck.setText(deck(game.getSelfHand()));
        otherDeck.setText(deck(game.getOtherHand()));
    }

    private String scores(List<Card> hand) {
        return "score: " + Game.CardEvaluator.getHandScore(hand);
    }

    private String deck(List<Card> hand) {
        return "Deck: " + String.join(" ", hand.stream().map(Card::toString).collect(Collectors.toList()));
    }

    private void endGame(int result) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(
                (result == 0) ? "Draw!" : (result == 1) ? "You won!" : "Other won!"
        );
        alert.showAndWait();
        restartGame();
    }

    private void restartGame() {
        game = new Game();
        updateScene();
    }

    @FXML
    private void get() {
        game.getCard();
        updateScene();
        if (!game.isOver()) {
            endGame(game.getWinner() ? 1 : 2);
        }
    }

    @FXML
    private void pass() {
        game.pass();
    }

    @FXML
    private void connect() throws IOException {
        serverButton.setDisable(true);
        clientButton.setDisable(true);
        player = new Player() {

            private Socket socket = new Socket(otherIp.getText(), 30239);

            @Override
            public State getStateAfterNewTurn(Turn turn) throws IOException {
                socket.getOutputStream().write(0);
                int read = socket.getInputStream().read();
                System.out.println("client read = " + read);
                return new State();
            }
        };
    }

    @FXML
    private void startServer() throws IOException {
        serverButton.setDisable(true);
        clientButton.setDisable(true);
        player = new Player() {

            private ServerSocket socket = new ServerSocket( 30239);

            @Override
            public State getStateAfterNewTurn(Turn turn) throws IOException {
                Socket client = socket.accept();
                client.getOutputStream().write(1);
                int read = client.getInputStream().read();
                System.out.println("server read = " + read);
                return new State();
            }
        };
    }
}
