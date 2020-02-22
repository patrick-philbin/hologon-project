package Conversion;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.transform.Rotate;

public class FourDisplay extends BorderPane {

    private Node top, bottom, left, right;
    private StackPane topPane, bottomPane, leftPane, rightPane;

    private int width, height, innerSize, size;

    private Pane pane;

    public FourDisplay(int width, int height, int innerSize) {
        this.height = height;
        this.width = width;
        this.innerSize = innerSize;
        this.size = Math.min(height, width);
        this.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        pane = new Pane();
        pane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setCenter(pane);
        BorderPane.setAlignment(pane, Pos.CENTER);
        pane.setMaxWidth(size);
        pane.setMaxHeight(size);
        this.setPrefSize(width, height);
    }

    public void setBottomNode(Node node) {
        this.bottom = node;
    }

    public void setTopNode(Node node) {
        this.top = node;
    }

    public void setRightNode(Node node) {
        this.right = node;
    }

    public void setLeftNode(Node node) {
        this.left = node;
    }

    public void Construct() {

        topPane = new StackPane();
        bottomPane = new StackPane();
        leftPane = new StackPane();
        rightPane = new StackPane();

        //Background background = new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY));

        //topPane.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        //leftPane.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        //rightPane.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        //bottomPane.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));

        pane.setPrefSize(size, size);

        pane.getChildren().add(topPane);
        pane.getChildren().add(bottomPane);
        pane.getChildren().add(rightPane);
        pane.getChildren().add(leftPane);


        top.prefWidth(innerSize);
        bottom.prefWidth(innerSize);
        right.prefWidth(innerSize);
        left.prefWidth(innerSize);

        int actualHeight = ((size - innerSize)/2);

        //top.prefHeight(actualHeight);
        //bottom.prefHeight(actualHeight);
        //right.prefHeight(actualHeight);
        //left.prefHeight(actualHeight);

        topPane.setPrefSize(innerSize, actualHeight);
        bottomPane.setPrefSize(innerSize, actualHeight);
        leftPane.setPrefSize(innerSize, actualHeight);
        rightPane.setPrefSize(innerSize, actualHeight);

        topPane.getChildren().add(top);
        bottomPane.getChildren().add(bottom);
        leftPane.getChildren().add(left);
        rightPane.getChildren().add(right);

        StackPane.setAlignment(top, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(bottom, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(right, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(left, Pos.BOTTOM_CENTER);


//        top.setLayoutY(topPane.getHeight() - top.getViewport().getHeight());
//        bottom.setLayoutY(bottomPane.getHeight() - bottom.getViewport().getHeight());
//        left.setLayoutY(leftPane.getHeight() - left.getViewport().getHeight());
//        right.setLayoutY(rightPane.getHeight() - right.getViewport().getHeight());

        //Rotate and transform each
        topPane.setLayoutY(0);
        topPane.setLayoutX(actualHeight);

        bottomPane.setLayoutY(innerSize);
        bottomPane.setLayoutX(actualHeight - innerSize);
        bottomPane.getTransforms().add(new Rotate(180, innerSize, actualHeight));

        leftPane.setLayoutX(actualHeight - innerSize);
        leftPane.getTransforms().add(new Rotate(-90, innerSize, actualHeight));

        rightPane.setLayoutX(actualHeight);
        rightPane.setLayoutY(innerSize);
        rightPane.getTransforms().add(new Rotate(90, innerSize, actualHeight));
    }
}
