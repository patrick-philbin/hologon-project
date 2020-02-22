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
        resEntry.setAlignment(Pos.CENTER);
        TextField screenWidthPx = new TextField();
        TextField screenHeightPx = new TextField();
        screenWidthPx.setAlignment(Pos.BASELINE_RIGHT);
        resEntry.getChildren().addAll(screenWidthPx, new Text("x"), screenHeightPx, new Text("px"));
        resolution.getChildren().add(resEntry);
        resolution.setAlignment(Pos.CENTER);

        VBox innerBox = new VBox();
        innerBox.getChildren().add(new Text("Inner Box Size"));
        TextField innerBoxSize = new TextField("300");
        innerBoxSize.setMaxWidth(100);
        innerBoxSize.setAlignment(Pos.CENTER);
        innerBox.getChildren().add(innerBoxSize);
        innerBox.setAlignment(Pos.CENTER);

        VBox videoBox = new VBox();
        videoBox.getChildren().add(new Text("Video Filepath"));
        TextField videopath = new TextField("videos/fishes.mp4");
        videopath.setMaxWidth(350);
        videoBox.getChildren().add(videopath);
        videoBox.setAlignment(Pos.CENTER);

        Button launch = new Button("Launch");

        menuElements.getChildren().addAll(resolution, innerBox, videoBox, launch);
        base.setTop(toolbar);
        base.setCenter(menuElements);
        Scene menu = new Scene(base, 500, 500);

        //Creating the second stage for viewing the video
        launch.setOnAction(event -> {
            Stage secondaryStage = new Stage();
            Viewer.setScreenWidth(Integer.parseInt(screenWidthPx.getText()));
            Viewer.setScreenHeight(Integer.parseInt(screenHeightPx.getText()));
            Viewer.setBase(Integer.parseInt(innerBoxSize.getText()));
            secondaryStage.setScene(Viewer.Video(videopath.getText()));
            secondaryStage.setFullScreen(true);
            secondaryStage.show();
        });

        primaryStage.setScene(menu);
        primaryStage.show();

    }
}
