package display.layer;

import static org.lwjgl.glfw.GLFW.*;

import java.util.ArrayList;

import org.joml.Vector2f;

import control.KeyboardInput;
import control.LayerController;
import control.MouseInput;
import control.Window;

import display.Button;
import display.Controller;
import display.Image;
import display.Skybox;

import graphic.Renderer;

public class StartLayer extends Layer{
    private Renderer renderer;

    private ArrayList<Controller> controllers;
    private Button[] btns = new Button[2];
    private Button startBtn;
    private Button exitBtn;
    private Image[] title = new Image[2];

    private Skybox skybox;
    private float rotate = 0;

    public StartLayer(LayerController layerController){
        super(layerController);

        controllers = new ArrayList<>();
        renderer = new Renderer();
    }

    public void init() throws Exception{
        renderer.init();

        skybox = new Skybox();
        for(int i = 0; i < 2; i++){
            title[i] = new Image("minecraft");
            title[i].setPosition(510 + 500*i, 250);
            title[i].setSize(500, 200);
            controllers.add(title[i]);
        }
        title[0].setTextCoord(0.0f, 0.0f, 0.605f, 0.174f);
        title[1].setTextCoord(0.0f, 0.175f, 0.605f, 0.349f);

        for(int i = 0; i < 2; i++){
            btns[i] = new Button();
            btns[i].setPosition(480, 675 + 100*i);
            btns[i].setSize(960, 80);
            controllers.add(btns[i]);

            btns[i].setText("");
            btns[i].setTextSize(30);
        }

        startBtn = btns[0];
        exitBtn = btns[1];

        exitBtn.setText("Exit");
        startBtn.setText("Game Start");

        exitBtn.setTextPosition((960 - exitBtn.getLabelWidth())/2, 25);
        startBtn.setTextPosition((960 - startBtn.getLabelWidth())/2, 25);
    }

    public void input(KeyboardInput keyboard, MouseInput mouse){
        Vector2f cursorPos = new Vector2f(mouse.getPosition());
        cursorPos.x *= 1920;
        cursorPos.y *= 1080;

        for(int i = 0; i < 2; i++){
            btns[i].unSelect();
            btns[i].setTextColour(0.6f, 0.6f, 0.6f, 1.0f);

            if(btns[i].isHover(cursorPos)){
                btns[i].select();
                btns[i].setTextColour(1.0f, 1.0f, 1.0f, 1.0f);
            }
        }

        if(startBtn.isHover(cursorPos) && mouse.isKeyPressed(MouseInput.LEFT_KEY)){
            Layer nextLayer;
            try {
                nextLayer = new MapSelectionLayer(controller, renderer);
                nextLayer.init();

                controller.addLayer(nextLayer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(exitBtn.isHover(cursorPos) && mouse.isKeyPressed(MouseInput.LEFT_KEY))
            controller.removeLastLayer();

        if(keyboard.isKeyPressed(GLFW_KEY_ESCAPE))
            controller.removeLastLayer();
    }

    public void update(float interval){
        skybox.setRotation(0.2f, rotate, 0.0f);
        rotate += 0.005f;
    }

    public void render(Window window){
        window.clear();

        window.setClearColor(0.6f, 0.298f, 0.0f, 1.0f);
        renderer.render(skybox, null, controllers);
    }
}