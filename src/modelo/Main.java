package modelo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/vista/vistaPrincipal.fxml"));
        primaryStage.setTitle("Formato FMS");
        primaryStage.setScene(new Scene(root, 800, 400));
        primaryStage.show();
        root.requestFocus();


    }
}
