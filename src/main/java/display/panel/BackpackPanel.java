package display.panel;

import java.util.ArrayList;

import org.joml.Vector2f;

import control.ItemInfo;
import control.TextureInfo;
import display.Image;

public class BackpackPanel extends Panel{
    private Image background;

    private ItemPanel[][] contains = new ItemPanel[9][3];
    private ItemPanel[] inHud = new ItemPanel[9];
    private ItemPanel[][] crafting = new ItemPanel[2][2];
    private ItemPanel crafted;
    
    public BackpackPanel(){
        background = new Image("backpack");
        background.setSize(900, 900);
        background.setTextCoord(0.0f, 0.0f, 0.69f, 0.645f);
        addController(background);

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 3; j++){
                contains[i][j] = new ItemPanel();
                contains[i][j].setPosition(42 + i*91.5f, 460 + j*98);
                addController(contains[i][j]);
            }
            inHud[i] = new ItemPanel();
            inHud[i].setPosition(42 + i*91.5f, 775);
            addController(inHud[i]);
        }

        for(int i = 0; i < 2; i++)
            for(int j = 0; j < 2; j++){
                crafting[i][j] = new ItemPanel();
                crafting[i][j].setPosition(500 + i*91.5f, 102 + j*98);
                addController(crafting[i][j]);
            }

        crafted = new ItemPanel();
        crafted.setPosition(785, 155);
        addController(crafted);
    }

    public void update(ArrayList<ItemInfo> inBackpack){
        clear();

        for (ItemInfo itemInfo : inBackpack) {
            Vector2f tOffset = TextureInfo.getItemTextureOffset(itemInfo.itemId);
            tOffset.x /= TextureInfo.UNIT2_SEPERATION.x;
            tOffset.y /= TextureInfo.UNIT2_SEPERATION.y;

            if(itemInfo.position.x == 0){
                inHud[(int)itemInfo.position.y].visible = true;
                inHud[(int)itemInfo.position.y].setTextCoord(tOffset.x, tOffset.y, TextureInfo.UNIT2_WIDTH, TextureInfo.UNIT2_HEIGHT);
            }
        }
    }

    private void clear(){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 3; j++)
                contains[i][j].visible = false;
            inHud[i].visible = false;
        }

        for(int i = 0; i < 2; i++)
            for(int j = 0; j < 2; j++)
                crafting[i][j].visible = false;

        crafted.visible = false;
    }
}