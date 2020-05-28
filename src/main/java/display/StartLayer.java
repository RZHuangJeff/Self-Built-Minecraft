package display;

import static org.lwjgl.glfw.GLFW.*;

import control.KeyboardInput;
import control.MouseInput;
import control.Window;

public class StartLayer extends Layer{
    public void init() throws Exception{

    }

    public void input(KeyboardInput keyboard, MouseInput mouse){
        if(keyboard.isKeyPressed(GLFW_KEY_ESCAPE))
            controller.removeLastLayer();
    }

    public void update(float interval){

    }

    public void render(Window window){
        window.clear();
        window.setClearColor(0.0f, 1.0f, 0.0f, 1.0f);
    }
}