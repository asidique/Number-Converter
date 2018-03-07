package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        Converter.IEEEToBinary("11000000110110011001100110011010");

    }


    public static void main(String[] args) {
        launch(args);
    }
}
