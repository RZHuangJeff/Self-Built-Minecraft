import control.*;
import display.layer.StartLayer;

public class Main {
    public static void main(String[] args) {
        try {
            Window window = new Window("Minecraft", 0, 0, true);
            
            LayerController lController = new LayerController();
            StartLayer start = new StartLayer(lController);
            lController.addLayer(start);

            GameLooper controller = new GameLooper(window, lController);
            controller.run();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}