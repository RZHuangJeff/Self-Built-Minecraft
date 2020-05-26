import control.*;
import display.*;
import layout.*;

public class Main {
    public static void main(String[] args) {
        try {
            Window window = new Window("Minecraft", 600, 480, true);
            GameLoopingController controller = new GameLoopingController(window);
            Frame startFrame = new StartFrame(window, controller);
            controller.addFrame(startFrame);
            controller.run();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}