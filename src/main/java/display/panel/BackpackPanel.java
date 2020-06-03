package display.panel;

import java.util.ArrayList;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import control.ItemInfo;
import control.MouseInput;
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
                contains[i][j].setSize(78, 78);
                addController(contains[i][j]);
            }
            inHud[i] = new ItemPanel();
            inHud[i].setPosition(42 + i*91.5f, 775);
            inHud[i].setSize(78, 78);
            addController(inHud[i]);
        }

        for(int i = 0; i < 2; i++)
            for(int j = 0; j < 2; j++){
                crafting[i][j] = new ItemPanel();
                crafting[i][j].setPosition(500 + i*91.5f, 102 + j*98);
                crafting[i][j].setSize(78, 78);
                addController(crafting[i][j]);
            }

        crafted = new ItemPanel();
        crafted.setPosition(785, 155);
        crafted.setSize(78, 78);
        addController(crafted);
    }

    public void update(ArrayList<ItemInfo> inBackpack){
        clear();

        ItemPanel target = null;
        for (ItemInfo itemInfo : inBackpack) {
            Vector2f tOffset = TextureInfo.getItemTextureOffset(itemInfo.itemId);
            tOffset.x /= TextureInfo.UNIT2_SEPERATION.x;
            tOffset.y /= TextureInfo.UNIT2_SEPERATION.y;

            switch((int)itemInfo.position.x){
                case 0:
                    target = inHud[(int)itemInfo.position.y];
                    break;
                case 1:
                    target = contains[(int)itemInfo.position.y][(int)itemInfo.position.z];
                    break;
                case 2:
                    target = crafting[(int)itemInfo.position.y][(int)itemInfo.position.z];
                    break;
                default:
                    target = crafted;
            }

            target.visible = true;
            target.setTextCoord(tOffset.x, tOffset.y, TextureInfo.UNIT2_WIDTH, TextureInfo.UNIT2_HEIGHT);
            target.setCount(itemInfo.count);
        }
    }

    public Vector4f input(MouseInput mouse){
        Vector4f result = new Vector4f(-1, -1, -1, -1);

        if(mouse.isKeyPressed(MouseInput.LEFT_KEY))
            result.w = MouseInput.LEFT_KEY;
        else if(mouse.isKeyPressed(MouseInput.RIGHT_KEY))
            result.w = MouseInput.RIGHT_KEY;
        else
            return result;

        Vector2f cursorPos = new Vector2f(mouse.getPosition());
        cursorPos.x *= 1920;
        cursorPos.y *= 1080;

        for(int i = 0; i < 9; i++){
            if(inHud[i].isHover(cursorPos)){
                result.x = 0;
                result.y = i;
                return result;
            }

            for(int j = 0; j < 3; j++)
                if(contains[i][j].isHover(cursorPos)){
                    result.x = 1;
                    result.y = i;
                    result.z = j;
                    return result;
                }
        }

        for(int i = 0; i < 2; i++)
            for(int j = 0; j < 2; j++)
                if(crafting[i][j].isHover(cursorPos)){
                    result.x = 2;
                    result.y = i;
                    result.z = j;
                    return result;
                }

        if(crafted.isHover(cursorPos)){
            result.x = 3;
            return result;
        }

        return result;
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