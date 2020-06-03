package display.panel;

import java.util.ArrayList;

import org.joml.Vector2f;
import org.joml.Vector4f;

import control.ItemInfo;
import control.MouseInput;
import control.TextureInfo;
import display.Image;

public class BackpackPanel extends Panel{
    private Image background;

    private Image[][] contains = new Image[9][3];
    private Image[] inHud = new Image[9];
    private Image onHand;
    
    public BackpackPanel(){
        background = new Image("backpack");
        background.setSize(900, 900);
        background.setTextCoord(0.0f, 0.0f, 0.69f, 0.645f);
        addController(background);

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 3; j++){
                contains[i][j] = new Image("unit1");
                contains[i][j].setSize(70, 70);
                contains[i][j].visible = false;
                contains[i][j].setPosition(46 + i*91.5f, 464 + j*98);
                addController(contains[i][j]);
            }
            inHud[i] = new Image("unit1");
            inHud[i].setSize(70, 70);
            inHud[i].setPosition(46 + i*91.5f, 779);
            addController(inHud[i]);
        }

        int i = 0;
        for(int id : TextureInfo.ITEM_LIST){
            Vector4f cord = TextureInfo.getItemTextureOffset(id);
            contains[i%9][i/9].visible = true;
            contains[i%9][i/9].setTextCoord(cord.x, cord.y, cord.z, cord.w);
            i++;
        }

        onHand = new Image("unit1");
        onHand.setSize(65, 65);
        addController(onHand);
    }

    public void update(ArrayList<ItemInfo> inBackpack){
        clear();

        Image target = null;
        for (ItemInfo itemInfo : inBackpack) {
            Vector4f offs = TextureInfo.getItemTextureOffset(itemInfo.itemId);
            switch((int)itemInfo.position.x){
                case 0:
                    target = onHand;
                    break;
                case 1:
                    target = inHud[(int)itemInfo.position.y];
                    break;
            }

            target.visible = true;
            target.setTextCoord(offs.x, offs.y, offs.z, offs.w);
        }
    }

    public Vector4f input(MouseInput mouse){
        Vector4f result = new Vector4f(-1, -1, -1, -1);
        Vector2f cursorPos = new Vector2f(mouse.getPosition());
        cursorPos.x *= 1920;
        cursorPos.y *= 1080;

        onHand.setPosition(cursorPos.x - this.getPosition().x - 30, cursorPos.y - this.getPosition().y - 30);

        if(mouse.isKeyPressed(MouseInput.LEFT_KEY))
            result.w = MouseInput.LEFT_KEY;
        else if(mouse.isKeyPressed(MouseInput.RIGHT_KEY))
            result.w = MouseInput.RIGHT_KEY;
        else
            return result;

        for(int i = 0; i < 9; i++){
            if(inHud[i].isHover(cursorPos)){
                result.x = 1;
                result.y = i;
                return result;
            }

            for(int j = 0; j < 3; j++)
                if(contains[i][j].isHover(cursorPos)){
                    result.x = 2;
                    result.y = i;
                    result.z = j;
                    return result;
                }
        }

        return result;
    }

    private void clear(){
        for(int i = 0; i < 9; i++)
            inHud[i].visible = false;

        onHand.visible = false;
    }
}