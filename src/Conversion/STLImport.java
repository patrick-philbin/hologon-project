package Conversion;

import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.text.Text;


public class STLImport extends HBox {

    private TextField path = new TextField("STL/WayfindersCoin.stl");
    private CheckBox enabled = new CheckBox();
    private TextField x = new TextField("0");
    private TextField y = new TextField("0");
    private TextField z = new TextField("0");
    private TextField color = new TextField("Orange");
    private TriangleMesh mesh;

    public STLImport(){
        //Setting baseline stuff
        this.setAlignment(Pos.CENTER);
        enabled.setSelected(true);
        path.setMaxWidth(150);
        x.setMaxWidth(30);
        y.setMaxWidth(30);
        z.setMaxWidth(30);
        color.setMaxWidth(60);
        this.setSpacing(10);

        HBox pathContainer = new HBox();
        pathContainer.getChildren().addAll(new Text("Path:"), path);

        HBox xContainer = new HBox();
        xContainer.getChildren().addAll(new Text("X:"), x);

        HBox yContainer = new HBox();
        yContainer.getChildren().addAll(new Text("Y:"), y);

        HBox zContainer = new HBox();
        zContainer.getChildren().addAll(new Text("Z:"), z);

        HBox colorContainer = new HBox();
        colorContainer.getChildren().addAll(new Text("Color:"), color);

        this.getChildren().addAll(enabled, pathContainer, xContainer, yContainer, zContainer, colorContainer);
    }

    public String getPath(){
        return path.getText();
    }

    public boolean getEnabled(){
        return enabled.isSelected();
    }

    public int getX(){
        return Integer.parseInt(x.getText());
    }

    public int getY(){
        return Integer.parseInt(y.getText());
    }

    public int getZ(){
        return Integer.parseInt(z.getText());
    }

    public Color getColor(){
        String c = color.getText();
        c = c.replaceAll(" ", "");
        return Color.web(c);
    }

    public TriangleMesh getMesh() {
        return this.mesh;
    }

    public void setMesh(TriangleMesh mesh) {
        this.mesh = mesh;
    }
}
