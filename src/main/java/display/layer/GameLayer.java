package display.layer;

import static org.lwjgl.glfw.GLFW.*;

import java.util.ArrayList;

import org.joml.Vector2f;

import control.KeyboardInput;
import control.LayerController;
import control.MouseInput;
import control.Window;
import display.Controller;
import display.Cube;
import display.panel.BackpackPanel;
import display.panel.HudPanel;

import graphic.Renderer;

public class GameLayer extends Layer{
    private Renderer renderer;
    private ArrayList<Controller> controllers;

    private HudPanel hud;
    private BackpackPanel backpack;
    private Cube cube;

    public GameLayer(LayerController layerController, Renderer renderer){
        super(layerController);
        this.renderer = renderer;

        controllers = new ArrayList<>();
    }

    public void init() throws Exception{
        hud = new HudPanel();
        hud.setPosition(510, 980);
        controllers.add(hud);

        backpack = new BackpackPanel();
        backpack.setPosition(510, 100);
        backpack.visible = false;
        controllers.add(backpack);

        cube = new Cube();
        cube.setPosition(0, 0, -2);
    }

    public void input(KeyboardInput keyboard, MouseInput mouse){
        Vector2f cursorPos = new Vector2f(mouse.getPosition());
        cursorPos.x *= 1920;
        cursorPos.y *= 1080;

        if(keyboard.isKeyPressed(GLFW_KEY_E)){
            backpack.visible = !backpack.visible;
            hud.visible = !hud.visible;
        }

        for(int i = 0; i < 9; i++){
            if(keyboard.isKeyPressed(GLFW_KEY_1 + i))
                hud.setFocus(i);
        }

        if(keyboard.isKeyPressed(GLFW_KEY_ESCAPE))
            controller.removeLastLayer();
    }

    public void update(float interval){
        cube.setRotation(cube.getRotation().z + 0.05f, cube.getRotation().y + 0.05f, cube.getRotation().z + 0.05f);
    }

    public void render(Window window){
        window.clear();
        renderer.render(window, null, cube, controllers);
    }
}