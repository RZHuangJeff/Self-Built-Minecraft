import control.*;
import display.*;

public class Main {
    public static void main(String[] args) {
        try {
            Window window = new Window("Minecraft", 600, 480, true);
            
            Layer start = new StartLayer();
            LayerController lController = new LayerController(start);
            start.setLayerController(lController);

            GameLooper controller = new GameLooper(window, lController);
            controller.run();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}