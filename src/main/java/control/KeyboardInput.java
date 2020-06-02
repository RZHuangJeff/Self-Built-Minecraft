package control;

import static org.lwjgl.glfw.GLFW.*;

import java.awt.MouseInfo;
import java.nio.DoubleBuffer;

import org.lwjgl.system.MemoryUtil;

public class KeyboardInput {
    private final Window window;

    private boolean hasGet = false;
    private int lastActionKey;
    private int lastAction;

    private int key;
    private int action;

    public KeyboardInput(Window window){
        this.window = window;
    }

    public void init(){
        glfwSetKeyCallback(window.getHandle(), (window, key, scancode, action, mods) -> {
            this.key = key;
            this.action = action;
            hasGet = false;
        });
    }

    public String getLastActionKey(){
        String value = "";
        if(!hasGet && 
            (Character.isLetterOrDigit(key) || Character.isWhitespace(key)))
            value = Character.toString(key);

        hasGet = true;
        return value;
    }

    public int getLastAction(){
        return action;
    }

    public boolean isLastKeyGetted(){
        return hasGet;
    }

    public boolean isKeyPressed(int keyCode){
        boolean isPressed = glfwGetKey(window.getHandle(), keyCode) == GLFW_PRESS;
        if(isPressed){
            if(lastAction != GLFW_PRESS){
                lastActionKey = keyCode;
                lastAction = GLFW_PRESS;
                return true;
            }
        }else if(keyCode == lastActionKey){
            lastAction = -1;
        }
        return false;
    }

    public boolean isKeyHold(int keyCode){
        return glfwGetKey(window.getHandle(), keyCode) == GLFW_PRESS;
    }

    public boolean isKeyReleased(int keyCode){
        boolean isKeyReleased = glfwGetKey(window.getHandle(), keyCode) == GLFW_RELEASE;
        if(isKeyReleased){
            if(lastAction != GLFW_RELEASE){
                lastActionKey = keyCode;
                lastAction = GLFW_RELEASE;
                return true;
            }
        }else if(keyCode == lastActionKey){
            lastAction = -1;
        }
            
        return false;
    }
}