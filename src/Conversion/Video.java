package Conversion;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;

public class Video extends Scene {

    public Video(int width, int height, int innerSize, String filePath) {
        super(new FourDisplay((int)width, (int)height, innerSize), width, height);

        Media media = new Media(new File(filePath).toURI().toString());

        MediaPlayer mediaPlayer = new MediaPlayer(media);

        MediaView mediaView = new MediaView(mediaPlayer);

        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));

        ((FourDisplay) this.getRoot()).setTop(mediaView);
        ((FourDisplay) this.getRoot()).setBottom(mediaView);
        ((FourDisplay) this.getRoot()).setLeft(mediaView);
        ((FourDisplay) this.getRoot()).setRight(mediaView);
    }
}
