package Conversion;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.transform.Rotate;

public class FourDisplay extends Pane {

    Node top, bottom, left, right;
    Pane topPane, bottomPane, leftPane, rightPane;

    public FourDisplay(int width, int height, int innerSize) {
        this.setPrefSize(width, height);

        this.getChildren().add(top);
        top.prefWidth(innerSize);
        bottom.prefWidth(innerSize);
        right.prefWidth(innerSize);
        left.prefWidth(innerSize);

        int actualHeight = ((Math.min(width, height) - innerSize)/2);

        top.prefHeight(actualHeight);
        bottom.prefHeight(actualHeight);
        right.prefHeight(actualHeight);
        left.prefHeight(actualHeight);

        topPane.setPrefSize(innerSize, actualHeight);
        bottomPane.setPrefSize(innerSize, actualHeight);
        leftPane.setPrefSize(innerSize, actualHeight);
        rightPane.setPrefSize(innerSize, actualHeight);



        top.getTransforms().add(new Rotate(90));

        top.getTransforms();
    }
}
