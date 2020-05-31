package control;

import static org.lwjgl.glfw.GLFW.*;

public class KeyboardInput {
    private final Window window;

    private boolean hasGet = false;
    private int lastActionKey;
    private int lastAction;

    public KeyboardInput(Window window){
        this.window = window;
    }

    public void init(){
        glfwSetKeyCallback(window.getHandle(), (window, key, scancode, action, mods) -> {
            lastActionKey = key;
            lastAction = action;
            hasGet = false;
        });
    }

    public String getLastActionKey(){
        String value = "";
        if(!hasGet && 
            (Character.isLetterOrDigit(lastActionKey) || Character.isWhitespace(lastActionKey)))
            value = Character.toString(lastActionKey);

        hasGet = true;
        return value;
    }

    public int getLastAction(){
        return lastAction;
    }

    public boolean isLastKeyGetted(){
        return hasGet;
    }

    public boolean isKeyPressed(int keyCode){
        if(lastActionKey == keyCode && lastAction == GLFW_PRESS){
            lastActionKey = -1;
            return true;
        }
        return false;
    }

    public boolean isKeyHold(int keyCode){
        return lastActionKey == keyCode && (lastAction == GLFW_PRESS || lastAction == GLFW_REPEAT);
    }

    public boolean isKeyReleased(int keyCode){
        if(lastActionKey == keyCode && lastAction == GLFW_PRESS){
            lastActionKey = -1;
            return true;
        }
        return false;
    }
}