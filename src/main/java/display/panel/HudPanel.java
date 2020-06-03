package display.panel;

import java.util.ArrayList;

import org.joml.Vector2f;

import control.ItemInfo;
import control.TextureInfo;
import display.Image;

public class HudPanel extends Panel{
    private Image hud;
    private Image onHand;
    private Image cursor;

    private ItemPanel[] items = new ItemPanel[9];

    public HudPanel(){
        hud = new Image("widgets");
        hud.setPosition(0, 0);
        hud.setSize(900, 100);
        hud.setTextCoord(0.0f, 0.0f, 0.71f, 0.085f);
        addController(hud);

        cursor = new Image("widgets");
        cursor.setSize(50, 50);
        cursor.setPosition(425, -440);
        cursor.setTextCoord(0.95f, 0.0f, 1.0f, 0.05f);
        addController(cursor);

        for(int i = 0; i < 9; i++){
            items[i] = new ItemPanel();
            items[i].setPosition(15 + i*99f, 10);
            addController(items[i]);
        }

        onHand = new Image("widgets");
        onHand.setSize(100, 100);
        onHand.setTextCoord(0.01f, 0.09f, 0.086f, 0.172f);
        addController(onHand);

        setFocus(0);
    }

    public void update(ArrayList<ItemInfo> inhud){
        for (ItemInfo item : inhud) {
            Vector2f tOffset = TextureInfo.getItemTextureOffset(item.itemId);
            tOffset.x /= TextureInfo.UNIT2_SEPERATION.x;
            tOffset.y /= TextureInfo.UNIT2_SEPERATION.y;

            items[(int)item.position.y].visible = true;
            items[(int)item.position.y].setTextCoord(tOffset.x, tOffset.y, TextureInfo.UNIT2_WIDTH, TextureInfo.UNIT2_HEIGHT);
            items[(int)item.position.y].setCount(item.count);
        }
    }

    public void setFocus(int index){
        onHand.setPosition(4 + index*99.5f, 0);
    }

    public void clear(){
        for(int i = 0; i < 9; i++)
            items[i].visible = false;
    }
}