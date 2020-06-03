package display.layer;

import static org.lwjgl.glfw.GLFW.*;

import java.util.ArrayList;
import java.util.Random;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import control.CameraPicker;
import control.ItemInfo;
import control.KeyboardInput;
import control.LayerController;
import control.MouseInput;
import control.TextureInfo;
import control.Window;
import display.Controller;
import display.Cube;
import display.Scene;
import display.panel.BackpackPanel;
import display.panel.HudPanel;
import graphic.Camera;
import graphic.Renderer;

public class GameLayer extends Layer{
    private Renderer renderer;
    private ArrayList<Controller> controllers;

    private HudPanel hud;
    private BackpackPanel backpack; 

    private Scene scene;
    private Camera camera;
    private Cube pick;

    public GameLayer(LayerController layerController, Renderer renderer){
        super(layerController);
        this.renderer = renderer;

        controllers = new ArrayList<>();
    }

    public void init() throws Exception{
        scene = new Scene();
        scene.setTexture("unit1");
        camera = new Camera();

        hud = new HudPanel();
        hud.setPosition(510, 980);
        controllers.add(hud);

        backpack = new BackpackPanel();
        backpack.setPosition(510, 100);
        backpack.visible = false;
        controllers.add(backpack);

        Random random = new Random();
        for(int i = 0; i < 100; i++)
            for(int j = 0; j < 100; j++){
                Cube cube = new Cube();
                cube.setPosition(-2 + 2*i, -3, 2 - 2*j);
                Vector2f toff = TextureInfo.getCubeTextureOffset(random.nextInt(59));
                cube.setTextOffset(toff.x, toff.y);
                scene.addCube(cube);
            }

        pick = new Cube();
        pick.setPosition(0, 0, 0);
        pick.setTextOffset(3, 2);
        pick.setScale(1.01f, 1.01f, 1.01f);
        scene.addCube(pick);
    }

    public void input(KeyboardInput keyboard, MouseInput mouse){
        Vector2f cursorPos = new Vector2f(mouse.getPosition());
        cursorPos.x *= 1920;
        cursorPos.y *= 1080;

        if(keyboard.isKeyPressed(GLFW_KEY_E)){
            backpack.visible = !backpack.visible;
            hud.visible = !hud.visible;
            if(backpack.visible)
                mouse.enableCursor();
            else
                mouse.disableCursor();
        }

        if(backpack.visible){
            Vector4f result = backpack.input(mouse);
            //you can get the result of click here.
        }else{
            for(int i = 0; i < 9; i++){
                if(keyboard.isKeyPressed(GLFW_KEY_1 + i))
                    hud.setFocus(i);
            }
    
            if(keyboard.isKeyPressed(GLFW_KEY_ESCAPE)){
                mouse.enableCursor();
                controller.removeLastLayer();
            }
    
            if(keyboard.isKeyHold(GLFW_KEY_W)){
                Vector3f pos = camera.getPosition();
                camera.setPostion(pos.x, pos.y, pos.z - 0.1f);
            }
            if(keyboard.isKeyHold(GLFW_KEY_S)){
                Vector3f pos = camera.getPosition();
                camera.setPostion(pos.x, pos.y, pos.z + 0.1f);
            }
    
            if(keyboard.isKeyHold(GLFW_KEY_A)){
                Vector3f pos = camera.getPosition();
                camera.setPostion(pos.x - 0.1f, pos.y, pos.z);
            }
    
            if(keyboard.isKeyHold(GLFW_KEY_D)){
                Vector3f pos = camera.getPosition();
                camera.setPostion(pos.x + 0.1f, pos.y, pos.z);
            }

            if(keyboard.isKeyHold(GLFW_KEY_SPACE)){
                Vector3f pos = camera.getPosition();
                pos.y += 0.1f;
            }

            if(keyboard.isKeyHold(GLFW_KEY_Z)){
                Vector3f pos = camera.getPosition();
                pos.y -= 0.1f;
            }
    
            Vector2f cursorDelta = mouse.getCursorDelta();
            Vector2f rot = camera.getRotation();
            rot.x = (rot.x + cursorDelta.y*100)%360;
            rot.y = (rot.y + cursorDelta.x*100)%360;
        }
    }

    public void update(float interval){
        ArrayList<ItemInfo> items = new ArrayList<>();
        // make items as list of items in hud.

        if(backpack.visible)
            backpack.update(items);

        if(hud.visible)
            hud.update(items);

        Cube picked = CameraPicker.cameraPick(scene.getCubeList(), camera);
        if(picked == null)
            picked = CameraPicker.cameraPick(scene.getGlasses(), camera);

        if(picked != null){
            Vector3f pos = picked.getPosition();
            pick.setPosition(pos.x, pos.y, pos.z);
        }
    }

    public void render(Window window){
        window.clear();

        window.enableDepthTest();
        renderer.render(scene, camera);
        window.disableDepthTest();

        renderer.render(controllers);
    }
}