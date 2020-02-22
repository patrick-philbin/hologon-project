package Conversion;

import javafx.scene.layout.Pane;
import javafx.scene.media.Media;

public class FourDisplay extends Pane {

    public FourDisplay(Media media, int width, int height) {
        MediaHolder left = new MediaHolder(media.getSource(), -90, width/3, height/3);
        MediaHolder right = new MediaHolder(media.getSource(), 90, width/3, height/3);
        MediaHolder top = new MediaHolder(media.getSource(), 180, width/3, height/3);
        MediaHolder bottom = new MediaHolder(media.getSource(), 0, width/3, height/3);

        this.getChildren().add(left);
        this.getChildren().add(right);
        this.getChildren().add(top);
        this.getChildren().add(bottom);
        left.setLayoutX(0);
        left.setLayoutY(height/3.);

        right.setLayoutX(2.*width/3.);
        right.setLayoutY(height/3.);

        top.setLayoutX(width/3.);
        top.setLayoutY(0);

        bottom.setLayoutX(width/3.);
        bottom.setLayoutY(2.*height/3.);
    }
}
