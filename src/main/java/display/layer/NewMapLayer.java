package display.layer;

import static org.lwjgl.glfw.GLFW.*;

import java.util.ArrayList;

import org.joml.Vector2f;

import control.KeyboardInput;
import control.LayerController;
import control.MapLoader;
import control.MouseInput;
import control.Window;
import display.Button;
import display.Controller;
import display.Image;
import display.Label;
import graphic.Renderer;

public class NewMapLayer extends Layer{
    private Renderer renderer;

    private ArrayList<Controller> controllers;
    private Button[] btns = new Button[2];
    private Button createBtn;
    private Button cancelBtn;
    private Label mapName;
    private Label cursor;
    private float updateInterval = 0.5f;
    
    public NewMapLayer(LayerController layerController, Renderer renderer){
        super(layerController);
        this.renderer = renderer;
        controllers = new ArrayList<>();
    }

    public void init() throws Exception{
        Image background = new Image("background");
        background.setPosition(0, 0);
        background.setSize(1920, 1080);
        background.setTextCoord(0.0f, 0.0f, 0.965f, 0.645f);
        controllers.add(background);

        Image textfiled = new Image("background");
        textfiled.setPosition(910, 460);
        textfiled.setSize(540, 80);
        textfiled.setColour(0.5f, 0.5f, 0.5f, 1.0f);
        textfiled.setTextCoord(0.0f, 0.0f, 0.965f, 0.645f);
        controllers.add(textfiled);

        for(int i = 0; i < 2; i++){
            btns[i] = new Button();
            btns[i].setPosition(470 + 500*i, 560);
            btns[i].setSize(480, 80);
            controllers.add(btns[i]);

            btns[i].setText("");
            btns[i].setTextSize(30);
        }

        createBtn = btns[0];
        cancelBtn = btns[1];

        createBtn.setText("Create");
        cancelBtn.setText("Cancel");

        createBtn.setTextPosition((480 - createBtn.getLabelWidth())/2, 25);
        cancelBtn.setTextPosition((480 - cancelBtn.getLabelWidth())/2, 25);

        Label title = new Label();
        title.setText("Name for new map :");
        title.setPosition(480, 484);
        title.setTextSize(30);
        controllers.add(title);

        mapName = new Label();
        mapName.setText("");
        mapName.setPosition(930, 485);
        mapName.setTextSize(30);
        mapName.setColour(0.85f, 0.85f, 0.85f, 0.9f);
        controllers.add(mapName);

        cursor = new Label();
        cursor.setText("|");
        cursor.setTextSize(30);
        cursor.setColour(0.85f, 0.85f, 0.85f, 0.9f);
        controllers.add(cursor);
    }

    public void input(KeyboardInput keyboard, MouseInput mouse){
        Vector2f cursorPos = new Vector2f(mouse.getPosition());
        cursorPos.x *= 1920;
        cursorPos.y *= 1080;

        for(Button btn : btns){
            btn.unSelect();
            btn.setTextColour(0.6f, 0.6f, 0.6f, 1.0f);
            
            if(btn.isHover(cursorPos)){
                btn.select();
                btn.setTextColour(1.0f, 1.0f, 1.0f, 1.0f);
            }
        }

        if(createBtn.isHover(cursorPos) && mouse.isKeyPressed(MouseInput.LEFT_KEY)){
            if(MapLoader.createMap(mapName.getText())){
                controller.removeLastLayer();
                controller.removeLastLayer();

                try {
                    Layer selectionLayer = new MapSelectionLayer(controller, renderer);
                    selectionLayer.init();

                    controller.addLayer(selectionLayer);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        if(cancelBtn.isHover(cursorPos) && mouse.isKeyPressed(MouseInput.LEFT_KEY))
            controller.removeLastLayer();

        if(!keyboard.isLastKeyGetted() && keyboard.getLastAction() == GLFW_PRESS){
            String lastKey = keyboard.getLastActionKey();
            if(lastKey.equals(Character.toString(GLFW_KEY_BACKSPACE))){
                String curStr = mapName.getText();
                mapName.setText(curStr.substring(0, curStr.length() == 0 ? 0 : curStr.length() - 1));
            }else
                mapName.setText(mapName.getText() + lastKey.toLowerCase());
        }

        if(keyboard.isKeyPressed(GLFW_KEY_ESCAPE))
            controller.removeLastLayer();
    }

    public void update(float interval){
        cursor.setPosition(930 + mapName.getWidth(), 485);

        if(updateInterval < 0)
            cursor.visible = !cursor.visible;

        updateInterval = updateInterval < 0 ? 0.5f : updateInterval - interval;
    }

    public void render(Window window){
        window.clear();
        window.setClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        renderer.render(null, null, controllers);
    }
}