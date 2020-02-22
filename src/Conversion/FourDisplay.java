package Conversion;

import javafx.scene.layout.Pane;
import javafx.scene.media.Media;

public class FourDisplay extends Pane {

    public FourDisplay(Media media) {
        MediaHolder left = new MediaHolder(media.getSource(), -90, 300, 300);
        MediaHolder right = new MediaHolder(media.getSource(), 90, 300, 300);
        MediaHolder top = new MediaHolder(media.getSource(), 180, 300, 300);
        MediaHolder bottom = new MediaHolder(media.getSource(), 0, 300, 300);

        this.getChildren().add(left);
        this.getChildren().add(right);
        this.getChildren().add(top);
        this.getChildren().add(bottom);
        left.setLayoutX(0);
        left.setLayoutY(300);

        right.setLayoutX(600);
        right.setLayoutY(300);

        top.setLayoutX(300);
        top.setLayoutY(0);

        bottom.setLayoutX(300);
        bottom.setLayoutY(600);
    }
}
