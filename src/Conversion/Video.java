package Conversion;

import javafx.scene.control.ScrollBar;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;

public class Video extends Pane {

    ScrollBar scrollBar;

    private int innerSize;

    MediaView[] mediaViews;

    boolean play = true;
    boolean shiftDown = false;

    public Video(int width, int height, int innerSize, String filePath) {
        this.innerSize = innerSize;

        this.setMaxSize(Math.min(width, height), Math.min(width, height));

        FourDisplay fourDisplay = new FourDisplay(width, height, innerSize);

        mediaViews = new MediaView[]{makeMediaView(filePath), makeMediaView(filePath), makeMediaView(filePath), makeMediaView(filePath)};

        fourDisplay.setTopNode(mediaViews[0]);
        fourDisplay.setBottomNode(mediaViews[1]);
        fourDisplay.setLeftNode(mediaViews[2]);
        fourDisplay.setRightNode(mediaViews[3]);

        fourDisplay.Construct();

        this.getChildren().add(fourDisplay);

        fourDisplay.layoutXProperty().bind(this.widthProperty().divide(2).subtract(fourDisplay.widthProperty().divide(2)));
        fourDisplay.layoutYProperty().bind(this.heightProperty().divide(2).subtract(fourDisplay.heightProperty().divide(2)));



        this.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case F11:
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
                    break;
                /*case SHIFT:
                    shiftDown = true;
                    break;*/
            }
        });

        /*this.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case SHIFT:
                    shiftDown = false;
                    break;
            }
        });*/

        /*this.setOnScroll(event -> {
            if(shiftDown) {
                double totalSeconds = mediaViews[0].getMediaPlayer().getTotalDuration().toSeconds();
                double currentSeconds = mediaViews[0].getMediaPlayer().getCurrentTime().toSeconds();

                scrubMedia(currentSeconds + (event.getDeltaX() * totalSeconds / 100.));
            }
        });*/
    }

    private void scrubMedia(double seconds) {
        for(int i = 0; i < 3; i++) {
            mediaViews[i].getMediaPlayer().seek(new Duration(seconds * 1000));
        }
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
