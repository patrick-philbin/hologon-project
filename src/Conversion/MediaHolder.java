package Conversion;

import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class MediaHolder extends Pane {
    public MediaHolder(String source, int rotate, int width, int height) {
        Media media = new Media(source);

        MediaPlayer mediaPlayer = new MediaPlayer(media);

        MediaView mediaView = new MediaView(mediaPlayer);

        mediaPlayer.setAutoPlay(true);
        mediaView.setFitHeight(height);
        mediaView.setFitWidth(width);
        this.getChildren().add(mediaView);
        mediaView.setRotate(rotate);

        this.setPrefSize(width, height);
    }
}
