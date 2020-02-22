package Conversion;

import javafx.scene.Scene;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.swing.text.View;

public class Viewer {
    private static int screenHeight, screenWidth, size;

    public static Scene Mathematica() {
        throw new NotImplementedException();
    }

    public static Scene Video() {
        return null;
    }

    public static void setScreenWidth(int width) {
        Viewer.screenWidth = width;
    }

    public static void setScreenHeight(int height) {
        Viewer.screenHeight = height;
    }

    public static void setBase(int size) {
        Viewer.size = size;
    }
}
