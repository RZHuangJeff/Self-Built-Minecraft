package display.panel;

import java.util.ArrayList;

import org.joml.Vector4f;

import control.ItemInfo;
import control.TextureInfo;
import display.Image;

public class HudPanel extends Panel{
    private Image hud;
    private Image onHand;
    private Image cursor;

    private Image[] items = new Image[9];

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
            items[i] = new Image("unit1");
            items[i].setSize(70, 70);
            items[i].setTextCoord(0, 0, 0.0625f, 0.1667f);
            items[i].setPosition(19 + i*99f, 14);
            addController(items[i]);
        }

        onHand = new Image("widgets");
        onHand.setSize(100, 100);
        onHand.setTextCoord(0.01f, 0.09f, 0.086f, 0.172f);
        addController(onHand);

        setFocus(0);
    }

    public void update(ArrayList<ItemInfo> inhud){
        clear();

        for (ItemInfo item : inhud) {
            Vector4f offs = TextureInfo.getItemTextureOffset(item.itemId);

            items[(int)item.position.y].visible = true;
            items[(int)item.position.y].setTextCoord(offs.x, offs.y, offs.z, offs.w);
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