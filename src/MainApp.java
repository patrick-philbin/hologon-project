import Conversion.FourDisplay;
import Conversion.MediaHolder;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.File;


public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    String path = "videos/fishes.mp4";

    @Override
    public void start(Stage primaryStage) {
        Media media = new Media(new File(path).toURI().toString());
        FourDisplay root = new FourDisplay(media, 900, 900);
        Scene scene = new Scene(root, 900, 900);
        scene.setFill(Color.WHITE);

        scene.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.F11)) {
                primaryStage.setFullScreen(!primaryStage.isFullScreen());
            }
        });

        primaryStage.setScene(scene);

        primaryStage.show();
        primaryStage.setFullScreen(true);

    }
}
