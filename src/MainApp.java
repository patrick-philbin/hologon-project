import Conversion.FourDisplay;
import Conversion.MediaHolder;
import Conversion.Viewer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;


public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    String path = "videos/fishes.mp4";

    @Override
    public void start(Stage primaryStage) {

        //Creating the primary "menu"
        BorderPane base = new BorderPane();

        VBox menuElements = new VBox();
        menuElements.setSpacing(50.0);
        menuElements.setAlignment(Pos.CENTER);

        HBox toolbar = new HBox();
        toolbar.getChildren().add(new Text("This is a toolbar, lol"));

        VBox resolution = new VBox();
        resolution.getChildren().add(new Text("Resolution"));
        HBox resEntry = new HBox();
        TextField screenWidthPx = new TextField();
        TextField screenHeightPx = new TextField();
        resEntry.getChildren().addAll(screenWidthPx, new Text("x"), screenHeightPx, new Text("px"));
        resolution.getChildren().add(resEntry);

        VBox videoBox = new VBox();
        videoBox.getChildren().add(new Text("Video Filepath"));
        TextField videopath = new TextField("videos/fishes.mp4");
        videoBox.getChildren().add(videopath);

        Button launch = new Button("Launch");

        menuElements.getChildren().addAll(resolution, videoBox, launch);
        base.setTop(toolbar);
        base.setCenter(menuElements);
        Scene menu = new Scene(base, 500, 500);

        //Creating the second stage for viewing the video


        launch.setOnAction(event -> {
            Stage secondaryStage = new Stage();
            secondaryStage.setScene(Viewer.Video());
            secondaryStage.setFullScreen(true);
            secondaryStage.show();
        });

        primaryStage.setScene(menu);
        primaryStage.show();

    }
}
