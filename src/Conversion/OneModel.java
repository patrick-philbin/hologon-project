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

import java.util.ArrayList;

public class OneModel {

    public static SubScene MakeModel(ArrayList<STLImport> imports, double yAngle, int width, int height, Rotate rotateX,
                                     Rotate rotateY, Rotate rotateZ, Translate translate) {

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

        for(STLImport stlImport: imports) {
            if(stlImport.getEnabled()) {

                MeshView coin = new MeshView(stlImport.getMesh());
                coin.setDrawMode(DrawMode.FILL);
                PhongMaterial material = new PhongMaterial();
                material.setDiffuseColor(stlImport.getColor());
                material.setSpecularColor(stlImport.getColor());
                coin.setCullFace(CullFace.BACK);
                coin.setMaterial(material);

                root.getChildren().add(coin);
            }
        }

        // Use a SubScene
        SubScene subScene = new SubScene(root, width,height,true, SceneAntialiasing.BALANCED);
        subScene.setFill(Color.BLACK);
        subScene.setCamera(camera);
        return subScene;
    }
}
