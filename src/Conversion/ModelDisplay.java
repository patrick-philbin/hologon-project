package Conversion;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import com.interactivemesh.jfx.importer.stl.StlMeshImporter;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.Paint;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Mesh;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

import java.io.File;

public class ModelDisplay extends Pane {

    private boolean play = true;
    private boolean ctrl = false;
    private boolean z = false;

    public ModelDisplay(int width, int height, int innerSize, String modelLocation) {
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        StlMeshImporter meshImporter = new StlMeshImporter();
        meshImporter.read(new File(modelLocation));
        TriangleMesh mesh = meshImporter.getImport();

        int size = Math.min(width, height);
        int trueHeight = (size - innerSize)/2;

        Rotate rotateY = new Rotate(0, Rotate.Y_AXIS);
        Rotate rotateX = new Rotate(0, Rotate.X_AXIS);
        Rotate rotateZ = new Rotate(0, Rotate.Z_AXIS);
        Translate translate = new Translate(0,0,0);

        FourDisplay fourDisplay = new FourDisplay(width, height, innerSize);
        fourDisplay.setTopNode(OneModel.MakeModel(mesh, 0., innerSize, trueHeight, rotateX, rotateY, rotateZ, translate));
        fourDisplay.setRightNode(OneModel.MakeModel(mesh, 90., innerSize, trueHeight, rotateX, rotateY, rotateZ, translate));
        fourDisplay.setBottomNode(OneModel.MakeModel(mesh, 180., innerSize, trueHeight, rotateX, rotateY, rotateZ, translate));
        fourDisplay.setLeftNode(OneModel.MakeModel(mesh, 270., innerSize, trueHeight, rotateX, rotateY, rotateZ, translate));

        fourDisplay.Construct();
        this.getChildren().add(fourDisplay);
        fourDisplay.layoutXProperty().bind(this.widthProperty().divide(2).subtract(fourDisplay.widthProperty().divide(2)));
        fourDisplay.layoutYProperty().bind(this.heightProperty().divide(2).subtract(fourDisplay.heightProperty().divide(2)));

        Timeline timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(5000),
                new KeyValue(rotateY.angleProperty(), rotateY.angleProperty().doubleValue() + 360.))
        );
        timeline.play();

        this.setOnKeyPressed(event -> {
            System.out.println(event.getCode());
            if(event.getCode() == KeyCode.SPACE) {
                if(play) {
                    timeline.stop();
                } else {
                    rotateY.setAngle(0);
                    rotateX.setAngle(0);
                    rotateZ.setAngle(0);
                    timeline.play();
                }
                play = !play;
            } else if (event.getCode() == KeyCode.CONTROL) {
                ctrl = true;
            } else if (event.getCode() == KeyCode.Z) {
                z = true;
            }
        });

        this.setOnKeyReleased(event -> {
            if(event.getCode() == KeyCode.CONTROL) {
                ctrl = false;
            } else if (event.getCode() == KeyCode.Z) {
                z = false;
            }
        });

        this.setOnScroll(event -> {
            System.out.println("Delta X: " + event.getDeltaX()/10);
            System.out.println("Delta Y: " + event.getDeltaY());
            if(ctrl && !z) {
                if(event.getDeltaY() != 0) {
                    rotateZ.setAngle(rotateZ.getAngle() + event.getDeltaY() / Math.abs(event.getDeltaY()) * 10);
                }
            } else if (z && ! ctrl) {
                if(event.getDeltaY() != 0) {
                    translate.setZ(translate.getZ() + event.getDeltaY() / Math.abs(event.getDeltaY()) * 10);
                }
            } else {
                if(event.getDeltaY() != 0) {
                    rotateY.setAngle(rotateY.getAngle() + event.getDeltaY() / Math.abs(event.getDeltaY()) * 10.);
                }
                if(event.getDeltaX() != 0) {
                    rotateX.setAngle(rotateX.getAngle() + event.getDeltaX() / Math.abs(event.getDeltaX()) * 10.);
                }
            }
        });
    }
}
