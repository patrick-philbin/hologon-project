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
        this.setSpacing(10);

        HBox pathContainer = new HBox();
        pathContainer.getChildren().addAll(new Text("Path:"), path);

        HBox xContainer = new HBox();
        xContainer.getChildren().addAll(new Text("X:"), x);
    }
}
