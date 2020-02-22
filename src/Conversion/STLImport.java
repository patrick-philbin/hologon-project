package Conversion;

import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class STLImport extends HBox {

    private TextField path = new TextField("STL/WayfindersCoin.stl");
    private CheckBox enabled = new CheckBox();
    private TextField x = new TextField("0");
    private TextField y = new TextField("0");
    private TextField z = new TextField("0");
    private TextField color = new TextField("Orange");

    public STLImport(){
        //Setting baseline stuff
        this.setAlignment(Pos.CENTER);
        enabled.setSelected(true);
        path.setMaxWidth(100);
        x.setMaxWidth(20);
        y.setMaxWidth(20);
        z.setMaxWidth(20);
        color.setMaxWidth(75);
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
        return Color.web(color.getText());
    }
}
