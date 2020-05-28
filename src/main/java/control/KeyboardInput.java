package control;

public class KeyboardInput {
    private final Window window;

    public KeyboardInput(Window window){
        this.window = window;
    }

    public boolean isKeyPressed(int keyCode){
        return window.isKeyPressed(keyCode);
    }
}