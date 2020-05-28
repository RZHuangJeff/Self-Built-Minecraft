package display;

import control.KeyboardInput;
import control.LayerController;
import control.MouseInput;
import control.Window;

abstract public class Layer {
    protected LayerController controller;

    public void setLayerController(LayerController controller){
        this.controller = controller;
    }
    
    abstract public void init() throws Exception;
    abstract public void input(KeyboardInput keyboard, MouseInput mouse);
    abstract public void update(float interval);
    abstract public void render(Window window);
}