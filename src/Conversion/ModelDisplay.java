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

    private boolean dragging = false;
    private double dragX = 0;
    private double dragY = 0;
    private double originalTranslateX = 0;
    private double originalTranslateY = 0;

    private double rotateXMomentum, rotateYMomentum, panXMomentum, panYMomentum, zoomMomentum;

    private double friction = .98;

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

        Rotate rotateY = new Rotate(0, Rotate.Y_AXIS);
        Rotate rotateX = new Rotate(0, Rotate.X_AXIS);
        Rotate rotateZ = new Rotate(0, Rotate.Z_AXIS);
        Translate translate = new Translate(0,0,0);

        FourDisplay fourDisplay = new FourDisplay(width, height, innerSize);
        fourDisplay.setTopNode(OneModel.MakeModel(stlImports, 0., innerSize, trueHeight, rotateX, rotateY, rotateZ, translate));
        fourDisplay.setRightNode(OneModel.MakeModel(stlImports, 90., innerSize, trueHeight, rotateX, rotateY, rotateZ, translate));
        fourDisplay.setBottomNode(OneModel.MakeModel(stlImports, 180., innerSize, trueHeight, rotateX, rotateY, rotateZ, translate));
        fourDisplay.setLeftNode(OneModel.MakeModel(stlImports, 270., innerSize, trueHeight, rotateX, rotateY, rotateZ, translate));

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
            if(rotateX.getAngle() > 90) {
                rotateX.setAngle(90);
            }

            if(rotateX.getAngle() < -90) {
                rotateX.setAngle(-90);
            }

            rotateY.setAngle(rotateY.getAngle() + rotateYMomentum / 60.);
            rotateX.setAngle(rotateX.getAngle() + rotateXMomentum / 60.);

            translate.setX(translate.getX() + panXMomentum / 60.);
            translate.setY(translate.getY() + panYMomentum / 60.);
            translate.setZ(translate.getZ() + zoomMomentum / 60.);

            rotateXMomentum *= friction;
            rotateYMomentum *= friction;
            panXMomentum *= friction;
            panYMomentum *= friction;
            zoomMomentum *= friction;
        }));

        this.setOnKeyPressed(event -> {
            System.out.println(event.getCode());
            if(event.getCode() == KeyCode.SPACE) {
                if(play) {
                    automatic.stop();
                    manual.play();
                } else {
                    rotateY.setAngle(0);
                    rotateX.setAngle(0);
                    rotateZ.setAngle(0);
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
                    panXMomentum += magnitude;
                } else if (!p && !z) {
                    panYMomentum += magnitude;
                }
            }

            if(event.getDeltaY() != 0) {
                double magnitude = event.getDeltaY() / Math.abs(event.getDeltaY()) * 50.;
                if(z && !p) {
                    zoomMomentum += magnitude;
                } else if(p && !z) {
                    panYMomentum += magnitude;
                } else if (!p && !z) {
                    rotateYMomentum += magnitude;
                }
            }

            /*
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
            }*/
        });

        this.setOnMouseDragged(event -> {
            if(!dragging) {
                dragX = event.getX();
                dragY = event.getY();
                originalTranslateX = translate.getX();
                originalTranslateX = translate.getY();
                dragging = true;
            }
            translate.setX(originalTranslateX + event.getX() - dragX);
            translate.setY(originalTranslateY + event.getY() - dragY);
        });

        this.setOnMouseReleased(event -> {
            if(dragging) {
                dragging = false;
            }
        });
    }
}
