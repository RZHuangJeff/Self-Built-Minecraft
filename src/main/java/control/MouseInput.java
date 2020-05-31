package control;

import static org.lwjgl.glfw.GLFW.*;

import org.joml.Vector2f;

public class MouseInput {
    public static final int LEFT_KEY = 0;
    public static final int RIGHT_KEY = 1;

    private final Window window;
    private int lastActiveButton;
    private int lastAction;
    private Vector2f cursorPos;

    public MouseInput(Window window){
        this.window = window;
        cursorPos = new Vector2f();
    }

    public void init(){
        glfwSetMouseButtonCallback(window.getHandle(), (handle, button, action, mode) -> {
            lastActiveButton = button;
            lastAction = action;
        });

        glfwSetCursorPosCallback(window.getHandle(), (handle, x, y) -> {
            cursorPos.x = (float)x/window.getWidth();
            cursorPos.y = (float)y/window.getHeight();
        });
    }

    public void input(){
    }

    public Vector2f getPosition(){
        return cursorPos;
    }

    public boolean isKeyPressed(int keyCode){
        if(lastActiveButton == keyCode && lastAction == GLFW_PRESS){
            lastAction = -1;
            return true;
        }
        return false;
    }
}