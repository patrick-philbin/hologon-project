package Conversion;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;


public class MediaHolder extends GridPane {
    public MediaHolder(String source, int rotate, int width, int height) {
        Media media = new Media(source);

        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        this.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        mediaPlayer.setAutoPlay(true);
        mediaView.setFitHeight(height);
        mediaView.setFitWidth(width);
        mediaView.setX(0);
        mediaView.setY(0);
        this.getChildren().add(mediaView);
        mediaView.setRotate(rotate);

        this.setPrefSize(width, height);
    }
}
