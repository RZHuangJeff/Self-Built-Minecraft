package control;

import static org.lwjgl.glfw.GLFW.*;

import java.nio.DoubleBuffer;

import org.joml.Vector2f;
import org.lwjgl.system.MemoryUtil;

public class MouseInput {
    public static final int LEFT_KEY = 0;
    public static final int RIGHT_KEY = 1;

    private final Window window;
    private int lastActiveButton;
    private int lastAction;
    private Vector2f cursorPos;
    private Vector2f cursorDel;
    private Vector2f newPos;

    public MouseInput(Window window){
        this.window = window;
        cursorPos = new Vector2f();
        cursorDel = new Vector2f();
        newPos = new Vector2f();
    }

    public void init(){
        glfwSetMouseButtonCallback(window.getHandle(), (handle, button, action, mode) -> {
            lastActiveButton = button;
            lastAction = action;
        });

        glfwSetCursorPosCallback(window.getHandle(), (handle, x, y) -> {
            x /= window.getWidth();
            y /= window.getHeight();

            newPos.x = (float)x;
            newPos.y = (float)y;
        });
    }

    public void input(){
        cursorDel.x = newPos.x - cursorPos.x;
        cursorDel.y = newPos.y - cursorPos.y;

        cursorPos.x = newPos.x;
        cursorPos.y = newPos.y;
    }

    public Vector2f getCursorDelta(){
        return cursorDel;
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

    public void enableCursor(){
        glfwSetInputMode(window.getHandle(), GLFW_CURSOR, GLFW_CURSOR_NORMAL);
    }

    public void disableCursor(){
        glfwSetInputMode(window.getHandle(), GLFW_CURSOR, GLFW_CURSOR_DISABLED);
    }
}