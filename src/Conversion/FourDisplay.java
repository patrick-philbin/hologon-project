package Conversion;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

public class FourDisplay extends Pane {

    private Node top, bottom, left, right;
    private Pane topPane, bottomPane, leftPane, rightPane;

    private int width, height, innerSize;

    public FourDisplay(int width, int height, int innerSize) {
        this.height = height;
        this.width = width;
        this.innerSize = innerSize;
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void setBottom(Node node) {
        this.bottom = node;
    }

    public void setTop(Node node) {
        this.top = node;
    }

    public void setRight(Node node) {
        this.right = node;
    }

    public void setLeft(Node node) {
        this.left = node;
    }

    public void Construct() {

        topPane = new Pane();
        bottomPane = new Pane();
        leftPane = new Pane();
        rightPane = new Pane();

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

        topPane.getChildren().add(top);
        bottomPane.getChildren().add(bottom);
        leftPane.getChildren().add(left);
        rightPane.getChildren().add(right);

        //Rotate and transform each
        top.setLayoutY(0);
        top.setLayoutX(actualHeight);

        bottom.setLayoutY(innerSize);
        bottom.setLayoutX(0);
        bottom.getTransforms().add(new Rotate(180, innerSize, actualHeight));

        left.getTransforms().add(new Rotate(-90, innerSize, actualHeight));

        right.setLayoutX(actualHeight);
        right.setLayoutY(innerSize);
        right.getTransforms().add(new Rotate(90, innerSize, actualHeight));
    }
}
