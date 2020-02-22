import Conversion.FourDisplay;
import Conversion.MediaHolder;
import Conversion.Viewer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
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

    private int screenWidth;
    private int screenHeight;
    private int baseSquare;
    private String path;

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

        VBox fileTypeHolder = new VBox();
        fileTypeHolder.setAlignment(Pos.CENTER);
        fileTypeHolder.getChildren().add(new Text("File Type"));
        ToggleButton videoChoice = new ToggleButton("Video");
        videoChoice.setUserData("Video");
        ToggleButton modelChoice = new ToggleButton("3D Model");
        modelChoice.setUserData("Model");
        ToggleGroup fileType = new ToggleGroup();
        videoChoice.setToggleGroup(fileType);
        modelChoice.setToggleGroup(fileType);
        HBox toggleHolder = new HBox();
        toggleHolder.setAlignment(Pos.CENTER);
        toggleHolder.getChildren().addAll(videoChoice, modelChoice);
        fileTypeHolder.getChildren().add(toggleHolder);

        VBox resolution = new VBox();
        resolution.getChildren().add(new Text("Resolution"));
        HBox resEntry = new HBox();
        resEntry.setAlignment(Pos.CENTER);
        TextField screenWidthPx = new TextField("1600");
        TextField screenHeightPx = new TextField("1200");
        screenWidthPx.setAlignment(Pos.BASELINE_RIGHT);
        resEntry.getChildren().addAll(screenWidthPx, new Text("x"), screenHeightPx, new Text("px"));
        resolution.getChildren().add(resEntry);
        resolution.setAlignment(Pos.CENTER);

        VBox innerBox = new VBox();
        innerBox.getChildren().add(new Text("Inner Box Size"));
        TextField innerBoxSize = new TextField("400");
        innerBoxSize.setMaxWidth(100);
        innerBoxSize.setAlignment(Pos.CENTER);
        innerBox.getChildren().add(innerBoxSize);
        innerBox.setAlignment(Pos.CENTER);

        VBox videoBox = new VBox();
        videoBox.getChildren().add(new Text("Filepath"));
        TextField filepath = new TextField("No File Type Selected!");
        filepath.setEditable(false);
        filepath.setMaxWidth(350);
        videoBox.getChildren().add(filepath);
        videoBox.setAlignment(Pos.CENTER);

        Button launch = new Button("Launch");

        menuElements.getChildren().addAll(fileTypeHolder, resolution, innerBox, videoBox, launch);
        base.setTop(toolbar);
        base.setCenter(menuElements);
        Scene menu = new Scene(base, 500, 500);

        //Setting toggle changes for filetype
        fileType.selectedToggleProperty().addListener((ov, toggle, new_toggle) -> {
            if(new_toggle == null){
                filepath.setEditable(false);
                filepath.setText("No File Type Selected!");
            }
            else if(new_toggle.getUserData().equals("Video")){
                filepath.setEditable(true);
                filepath.setText("videos/fishes.mp4");
            }
            else if(new_toggle.getUserData().equals("Model")){
                filepath.setEditable(true);
                filepath.setText("STL/WayfindersCoin.stl");
            }
        });

        //Creating the second stage for viewing the video
        launch.setOnAction(event -> {
            Stage secondaryStage = new Stage();
            screenWidth = Integer.parseInt(screenWidthPx.getText());
            screenHeight = Integer.parseInt(screenHeightPx.getText());
            baseSquare = Integer.parseInt(innerBoxSize.getText());

            Scene videoView = buildViewer((String)fileType.getSelectedToggle().getUserData(), filepath.getText());
            videoView.setOnKeyPressed(e -> {
                if(e.getCode().equals(KeyCode.ESCAPE)){
                    secondaryStage.close();
                    try {
                        this.stop();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    this.start(primaryStage);
                }
            });

            videoView.getRoot().requestFocus();

            secondaryStage.setScene(videoView);
            secondaryStage.setFullScreen(true);
            secondaryStage.show();
        });

        primaryStage.setScene(menu);
        primaryStage.show();

    }

    private Scene buildViewer(String type, String path){
        Viewer.setScreenWidth(screenWidth);
        Viewer.setScreenHeight(screenHeight);
        Viewer.setBase(baseSquare);
        if(type.equals("Video")){
            return Viewer.Video(path);
        }
        else if(type.equals("Model")){
            return Viewer.Model(path);
        }
        else{
            return null;
        }
    }

}
