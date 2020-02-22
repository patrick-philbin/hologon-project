package Conversion;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

public class OneModel {

    public static SubScene MakeModel(TriangleMesh mesh, double yAngle, int width, int height) {
        MeshView coin = new MeshView(mesh);
        coin.setDrawMode(DrawMode.FILL);
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.GOLD);
        material.setSpecularColor(Color.GOLD);
        coin.setMaterial(material);

        //Setup animation
        Rotate rotate = new Rotate(0, Rotate.Y_AXIS);

        //Make camera
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.getTransforms().addAll (
                new Rotate(yAngle, Rotate.Y_AXIS),
                rotate,
                new Translate(0, 0, -150));
        camera.setFarClip(2000);

        Timeline timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(5000),
                new KeyValue(rotate.angleProperty(), rotate.angleProperty().doubleValue() + 360.))
        );
        timeline.play();

        // Build the Scene Graph
        Group root = new Group();
        root.getChildren().add(camera);
        root.getChildren().add(coin);

        // Use a SubScene
        SubScene subScene = new SubScene(root, width,height);
        subScene.setFill(Color.BLACK);
        subScene.setCamera(camera);

        return subScene;
    }
}
