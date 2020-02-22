package Conversion;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;

public class Video extends Scene {

    private int innerSize;

    MediaView[] mediaViews;

    boolean play = true;

    public Video(int width, int height, int innerSize, String filePath) {
        super(new FourDisplay(width, height, innerSize), width, height);

        this.innerSize = innerSize;

        mediaViews = new MediaView[]{makeMediaView(filePath), makeMediaView(filePath), makeMediaView(filePath), makeMediaView(filePath)};

        ((FourDisplay)this.getRoot()).setTop(mediaViews[0]);
        ((FourDisplay)this.getRoot()).setBottom(mediaViews[1]);
        ((FourDisplay)this.getRoot()).setLeft(mediaViews[2]);
        ((FourDisplay)this.getRoot()).setRight(mediaViews[3]);

        ((FourDisplay)this.getRoot()).Construct();

        this.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.SPACE) {
                if (play) {
                    for (int i = 0; i < 4; i++) {
                        mediaViews[i].getMediaPlayer().pause();
                    }
                } else {
                    for (int i = 0; i < 4; i++) {
                        mediaViews[i].getMediaPlayer().play();
                    }
                }
                play = !play;
            }
        });
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
