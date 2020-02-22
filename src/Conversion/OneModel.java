package Conversion;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

public class OneModel {

    public static SubScene MakeModel(TriangleMesh mesh, double yAngle, int width, int height, Rotate rotateX,
                                     Rotate rotateY, Rotate rotateZ, Translate translate) {
        MeshView coin = new MeshView(mesh);
        coin.setDrawMode(DrawMode.FILL);
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.GOLDENROD);
        material.setSpecularColor(Color.GOLD);
        coin.setCullFace(CullFace.BACK);

        coin.setMaterial(material);

        //Make camera
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.getTransforms().addAll (
                new Rotate(yAngle, Rotate.Y_AXIS),
                rotateY,
                rotateX,
                rotateZ,
                translate,
                new Translate(0, 0, -150));
        camera.setFarClip(2000);
        camera.setNearClip(.1);

        // Build the Scene Graph
        Group root = new Group();
        root.getChildren().add(camera);
        root.getChildren().add(coin);

        // Use a SubScene
        SubScene subScene = new SubScene(root, width,height,true, SceneAntialiasing.BALANCED);
        subScene.setFill(Color.BLACK);
        subScene.setCamera(camera);
        return subScene;
    }
}
