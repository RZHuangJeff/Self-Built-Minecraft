package control;

import java.util.ArrayList;

import display.Layer;

public class LayerController {
    private int lastLayerIndex;
    private final ArrayList<Layer> layers = new ArrayList<>();

    public LayerController(Layer initialLayer){
        layers.add(initialLayer);
        lastLayerIndex = 0;
    }

    public void init() throws Exception{
        layers.get(0).init();
    }

    public void addLayer(Layer newLayer){
        layers.add(newLayer);
        lastLayerIndex++;
    }

    public void removeLastLayer(){
        layers.remove(lastLayerIndex--);
    }

    public boolean isEmpty() {
        return layers.size() == 0;
    }

    public void input(KeyboardInput keyboard, MouseInput mouse){
        if(lastLayerIndex >= 0)
            layers.get(lastLayerIndex).input(keyboard, mouse);
    }

    public void update(float interval){
        if(lastLayerIndex >= 0)
            layers.get(lastLayerIndex).update(interval);
    }

    public void render(Window window){
        if(lastLayerIndex >= 0)
            layers.get(lastLayerIndex).render(window);
    }
}