package Conversion;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Point3D;
import javafx.scene.*;
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
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

import java.io.File;

public class ModelDisplay extends Pane {

    public ModelDisplay(int width, int height, int innerSize, String modelLocation) {
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        StlMeshImporter meshImporter = new StlMeshImporter();
        meshImporter.read(new File(modelLocation));
        TriangleMesh mesh = meshImporter.getImport();

        int size = Math.min(width, height);
        int trueHeight = (size - innerSize)/2;

        FourDisplay fourDisplay = new FourDisplay(width, height, innerSize);
        fourDisplay.setTopNode(OneModel.MakeModel(mesh, 0., innerSize, trueHeight));
        fourDisplay.setRightNode(OneModel.MakeModel(mesh, 90., innerSize, trueHeight));
        fourDisplay.setBottomNode(OneModel.MakeModel(mesh, 180., innerSize, trueHeight));
        fourDisplay.setLeftNode(OneModel.MakeModel(mesh, 270., innerSize, trueHeight));

        fourDisplay.Construct();
        this.getChildren().add(fourDisplay);
        fourDisplay.layoutXProperty().bind(this.widthProperty().divide(2).subtract(fourDisplay.widthProperty().divide(2)));
        fourDisplay.layoutYProperty().bind(this.heightProperty().divide(2).subtract(fourDisplay.heightProperty().divide(2)));
    }
}
