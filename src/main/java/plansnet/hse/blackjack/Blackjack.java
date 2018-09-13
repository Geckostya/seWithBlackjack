package plansnet.hse.blackjack;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Blackjack extends Application {
    private static final String pathToUI = "/plansnet/hse/blackjack/";

    @Override
    public void start(Stage primaryStage) throws Exception {

        Stage stage = primaryStage;
        Scene scene = openScene("Blackjack");
        stage.setScene(scene);
//        stage.setTitle("Tic-Tac-Toe");
//        stage.setMinHeight(300);
//        stage.setMinWidth(310);
//        stage.setHeight(appHeight);
//        stage.setWidth(appWidth);
        stage.show();
    }

    private Scene openScene(String sceneName) throws Exception {
        Parent root = FXMLLoader
                .load(getClass().getResource(pathToUI + sceneName + ".fxml"));
        return new Scene(root);
    }
}
