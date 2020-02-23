package Conversion;

import com.interactivemesh.jfx.importer.stl.StlImportOption;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import com.interactivemesh.jfx.importer.stl.StlMeshImporter;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.Paint;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;

public class ModelDisplay extends Pane {

    private boolean play = true;
    private boolean p = false;
    private boolean z = false;

    private double rotateXMomentum, rotateYMomentum;

    private double friction = .98;

    private Rotate rotateLeftZ, rotateRightZ, rotateTopZ, rotateBottomZ, rotateY;
    private Rotate rotateLeftX, rotateRightX, rotateTopX, rotateBottomX;

    private Transform transformRight, transformLeft, transformTop, transformBottom;

    public ModelDisplay(int width, int height, int innerSize, ArrayList<STLImport> stlImports) {
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        for(STLImport stlImport : stlImports) {
            StlMeshImporter meshImporter = new StlMeshImporter();
            meshImporter.read(new File(stlImport.getPath()));
            TriangleMesh mesh = meshImporter.getImport();
            mesh.setVertexFormat(VertexFormat.POINT_TEXCOORD);
            stlImport.setMesh(mesh);
        }


        int size = Math.min(width, height);
        int trueHeight = (size - innerSize)/2;

        rotateY = new Rotate(0, Rotate.Y_AXIS);
        //Rotate rotateX = new Rotate(0, Rotate.X_AXIS);
        //Rotate rotateZ = new Rotate(0, Rotate.Z_AXIS);

        rotateBottomX = new Rotate(0, Rotate.X_AXIS);
        rotateBottomZ = new Rotate(0, Rotate.Z_AXIS);

        rotateTopX = new Rotate(0, Rotate.X_AXIS);
        rotateTopZ = new Rotate(0, Rotate.Z_AXIS);

        rotateLeftX = new Rotate(0, Rotate.X_AXIS);
        rotateLeftZ = new Rotate(0, Rotate.Z_AXIS);

        rotateRightX = new Rotate(0, Rotate.X_AXIS);
        rotateRightZ = new Rotate(0, Rotate.Z_AXIS);



        Translate translate = new Translate(0,0,0);

        FourDisplay fourDisplay = new FourDisplay(width, height, innerSize);
        fourDisplay.setTopNode(OneModel.MakeModel(stlImports, 0., innerSize, trueHeight, rotateTopX, rotateY, rotateTopZ, translate));
        fourDisplay.setRightNode(OneModel.MakeModel(stlImports, 90., innerSize, trueHeight, rotateRightZ, rotateY, rotateRightZ, translate));
        fourDisplay.setBottomNode(OneModel.MakeModel(stlImports, 180., innerSize, trueHeight, rotateBottomX, rotateY, rotateBottomZ, translate));
        fourDisplay.setLeftNode(OneModel.MakeModel(stlImports, 270., innerSize, trueHeight, rotateLeftX, rotateY, rotateLeftZ, translate));

        fourDisplay.Construct();
        this.getChildren().add(fourDisplay);
        fourDisplay.layoutXProperty().bind(this.widthProperty().divide(2).subtract(fourDisplay.widthProperty().divide(2)));
        fourDisplay.layoutYProperty().bind(this.heightProperty().divide(2).subtract(fourDisplay.heightProperty().divide(2)));

        Timeline automatic = new Timeline();
        automatic.setCycleCount(Animation.INDEFINITE);
        automatic.getKeyFrames().add(new KeyFrame(Duration.millis(5000),
                new KeyValue(rotateY.angleProperty(), rotateY.angleProperty().doubleValue() + 360.))
        );
        automatic.play();

        Timeline manual = new Timeline();
        manual.setCycleCount(Animation.INDEFINITE);
        manual.getKeyFrames().add(new KeyFrame(Duration.millis(17), event -> {
            /*if(rotateX.getAngle() > 90) {
                rotateX.setAngle(90);
            }

            if(rotateX.getAngle() < -90) {
                rotateX.setAngle(-90);
            }*/

            setYAxis(rotateY.getAngle() + rotateYMomentum / 1200.);
            setXAxis(rotateTopX.getAngle() + rotateXMomentum / 600.);

            rotateXMomentum *= friction;
            rotateYMomentum *= friction;
        }));

        this.setOnKeyPressed(event -> {
            System.out.println(event.getCode());
            if(event.getCode() == KeyCode.SPACE) {
                if(play) {
                    automatic.stop();
                    manual.play();
                } else {
                    setYAxis(0);
                    setZAxis(0);
                    setXAxis(0);
                    translate.setX(0);
                    translate.setY(0);
                    manual.stop();
                    automatic.play();
                }
                play = !play;
            } else if (event.getCode() == KeyCode.P) {
                p = true;
            } else if (event.getCode() == KeyCode.Z) {
                z = true;
            }
        });

        this.setOnKeyReleased(event -> {
            if(event.getCode() == KeyCode.P) {
                p = false;
            } else if (event.getCode() == KeyCode.Z) {
                z = false;
            }
        });

        this.setOnScroll(event -> {
            if(event.getDeltaX() != 0) {
                double magnitude = event.getDeltaX() / Math.abs(event.getDeltaX()) * 50.;
                if(p && !z) {
                    translate.setX(translate.getX() + magnitude/900.);
                } else if (!p && !z) {
                    rotateYMomentum += magnitude;
                }
            }

            if(event.getDeltaY() != 0) {
                double magnitude = event.getDeltaY() / Math.abs(event.getDeltaY()) * 50.;
                if(z && !p) {
                    translate.setZ(translate.getZ() + magnitude);
                } else if(p && !z) {
                    //panYMomentum += magnitude;
                    translate.setY(translate.getY() + magnitude/900.);
                } else if (!p && !z) {
                    rotateXMomentum += magnitude;
                }
            }

        });


    }

    public void setYAxis(double angle) {
        rotateY.setAngle(angle);
    }

    public void setZAxis(double angle) {
        rotateTopZ.setAngle(angle);
        rotateBottomZ.setAngle(-angle);
        rotateRightX.setAngle(angle);
        rotateLeftX.setAngle(-angle);
    }

    public void setXAxis(double angle) {
        rotateTopX.setAngle(angle);
        rotateBottomX.setAngle(-angle);
        rotateRightZ.setAngle(-angle);
        rotateLeftZ.setAngle(angle);
    }
}
