package Conversion;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;

public class Video extends Scene {

    private int innerSize;

    public Video(int width, int height, int innerSize, String filePath) {
        super(new FourDisplay(width, height, innerSize), width, height);

        this.innerSize = innerSize;

        ((FourDisplay)this.getRoot()).setTop(makeMediaView(filePath));
        ((FourDisplay)this.getRoot()).setBottom(makeMediaView(filePath));
        ((FourDisplay)this.getRoot()).setLeft(makeMediaView(filePath));
        ((FourDisplay)this.getRoot()).setRight(makeMediaView(filePath));

        ((FourDisplay)this.getRoot()).Construct();


    }

    private MediaView makeMediaView(String filePath) {

        Media media = new Media(new File(filePath).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);

        mediaView.setFitWidth(innerSize);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));

        return mediaView;
    }
}
