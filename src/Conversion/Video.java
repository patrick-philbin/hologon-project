package Conversion;

import javafx.scene.Parent;
import javafx.scene.Scene;

public class Video extends Scene {

    public Video(int width, int height, int innerSize, String filePath) {
        super(new FourDisplay((int)width, (int)height, innerSize), width, height);
    }
}
