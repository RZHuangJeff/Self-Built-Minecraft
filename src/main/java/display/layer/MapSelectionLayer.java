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

public class MapSelectionLayer extends Layer{
    private Renderer renderer;

    private ArrayList<Controller> controllers;
    private Button[][] btns = new Button[2][2];
    private Button exitBtn;
    private Button selectBtn;
    private Button deleteBtn;
    private Button createBtn;
    private Button[] listElements = new Button[4];
    private Label pageHint;

    private ArrayList<String> maps;
    private int topOfList = 0;
    private int selectedIndex = -1;

    public MapSelectionLayer(LayerController layerController, Renderer renderer){
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

        Image mapList = new Image("background");
        mapList.setPosition(470, 200);
        mapList.setSize(980, 500);
        mapList.setTextCoord(0.0f, 0.0f, 0.965f, 0.645f);
        mapList.setColour(0.7f, 0.7f, 0.7f, 0.5f);
        controllers.add(mapList);

        for(int i = 0; i < 2; i++)
            for(int j = 0; j < 2; j++){
                btns[i][j] = new Button();
                btns[i][j].setPosition(470 + 500*i, 775 + 100*j);
                btns[i][j].setSize(480, 80);
                controllers.add(btns[i][j]);

                btns[i][j].setText("");
                btns[i][j].setTextSize(30);
            }

        
        for(int i = 0; i < 4; i++){
            listElements[i] = new Button();
            listElements[i].setPosition(480, 215 + 120*i);
            listElements[i].setSize(960, 110);
            listElements[i].setColour(0.7f, 0.7f, 0.7f, 0.5f);
            controllers.add(listElements[i]);

            listElements[i].setText("");
            listElements[i].setTextSize(30);
            listElements[i].setTextPosition(20, 20);
        }

        selectBtn = btns[0][0];
        createBtn = btns[1][0];
        deleteBtn = btns[0][1];
        exitBtn = btns[1][1];
        
        selectBtn.setText("Select Map");
        createBtn.setText("New Map");
        deleteBtn.setText("Delete Map");
        exitBtn.setText("Main Menu");

        selectBtn.setTextPosition((480 - selectBtn.getLabelWidth())/2, 25);
        createBtn.setTextPosition((480 - createBtn.getLabelWidth())/2, 25);
        deleteBtn.setTextPosition((480 - deleteBtn.getLabelWidth())/2, 25);
        exitBtn.setTextPosition((480 - exitBtn.getLabelWidth())/2, 25);

        pageHint = new Label();
        pageHint.setPosition(480, 720);
        pageHint.setTextSize(30);
        pageHint.setColour(0.5f, 0.5f, 0.5f, 1.0f);
        controllers.add(pageHint);

        loadMap();
    }

    public void input(KeyboardInput keyboard, MouseInput mouse){
        Vector2f cursorPos = new Vector2f(mouse.getPosition());
        cursorPos.x *= 1920;
        cursorPos.y *= 1080;

        for(int i = 0; i < 2; i++)
            for(int j = 0; j < 2; j++){
                btns[i][j].unSelect();
                btns[i][j].setTextColour(0.6f, 0.6f, 0.6f, 1.0f);

                if(btns[i][j].isHover(cursorPos)){
                    btns[i][j].select();
                    btns[i][j].setTextColour(1.0f, 1.0f, 1.0f, 1.0f);
                }
            }

        for(int i = 0; i < 4; i++){
            if(topOfList + i == selectedIndex)
                listElements[i].select();
            else
                listElements[i].unSelect();

            if(listElements[i].isHover(cursorPos)){
                listElements[i].select();

                if(mouse.isKeyPressed(MouseInput.LEFT_KEY))
                    selectedIndex = topOfList + i;
            }
        }

        if(createBtn.isHover(cursorPos) && mouse.isKeyPressed(MouseInput.LEFT_KEY)){
            Layer nextLayer;
            try {
                nextLayer = new NewMapLayer(controller, renderer);
                nextLayer.init();

                controller.addLayer(nextLayer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(selectBtn.isHover(cursorPos) && mouse.isKeyPressed(MouseInput.LEFT_KEY)){
            try {
                Layer nextLayer = new GameLayer(controller, renderer);
                nextLayer.init();

                controller.removeLastLayer();
                controller.addLayer(nextLayer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(deleteBtn.isHover(cursorPos) && mouse.isKeyPressed(MouseInput.LEFT_KEY)){
            MapLoader.deleteMapByName(maps.get(selectedIndex));
            loadMap();
        }

        if(exitBtn.isHover(cursorPos) && mouse.isKeyPressed(MouseInput.LEFT_KEY))
            controller.removeLastLayer();

        if(keyboard.isKeyPressed(GLFW_KEY_UP))
            topOfList -= topOfList == 0 ? 0 : 4;

        if(keyboard.isKeyPressed(GLFW_KEY_DOWN))
            topOfList += topOfList + 4 >= maps.size() ? 0 : 4;

        if(keyboard.isKeyPressed(GLFW_KEY_ESCAPE))
            controller.removeLastLayer();
    }

    public void update(float interval){
        for(int i = 0; i < 4; i++){
            if(topOfList + i < maps.size()){
                listElements[i].setText(maps.get(topOfList + i));
                listElements[i].visible = true;
            }else
                listElements[i].visible = false;
        }

        int totalPage = maps.size()/4;
        totalPage += maps.size()%4 == 0 ? 0 : 1;
        pageHint.setText("Page " + (topOfList/4 + 1) + "/" + totalPage + "    To change: Up/Down");
    }

    public void render(Window window){
        window.clear();
        window.setClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        renderer.render(null, null, controllers);
    }

    private void loadMap(){
        maps = MapLoader.getMapNames();
        selectedIndex = -1;
        topOfList = 0;
    }
}