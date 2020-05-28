package control;

import static org.lwjgl.glfw.GLFW.*;

import java.util.Arrays;

public class MouseInput {
    public static final int LEFT_KEY = 0;
    public static final int RIGHT_KEY = 1;

    private final Window window;
    private boolean keyPressed[] = new boolean[2];

    public MouseInput(Window window){
        this.window = window;
        Arrays.fill(keyPressed, false);
    }

    public void init(){
        glfwSetMouseButtonCallback(window.getHandle(), (handle, button, action, mode) -> {
            keyPressed[0] = button == GLFW_MOUSE_BUTTON_1 && action == GLFW_PRESS;
            keyPressed[1] = button == GLFW_MOUSE_BUTTON_2 && action == GLFW_PRESS;
        });
    }

    public void input(){

    }

    public boolean isKeyPressed(int keyCode){
        return keyPressed[keyCode];
    }
}