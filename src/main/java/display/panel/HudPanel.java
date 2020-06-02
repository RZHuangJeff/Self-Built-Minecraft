package display.panel;

import display.Image;

public class HudPanel extends Panel{
    private Image hud;
    private Image onHand;
    private Image cursor;

    public HudPanel(){
        hud = new Image("widgets");
        hud.setPosition(0, 0);
        hud.setSize(900, 100);
        hud.setTextCoord(0.0f, 0.0f, 0.71f, 0.085f);
        addController(hud);

        onHand = new Image("widgets");
        onHand.setSize(100, 100);
        onHand.setTextCoord(0.01f, 0.09f, 0.086f, 0.172f);
        addController(onHand);

        cursor = new Image("widgets");
        cursor.setSize(50, 50);
        cursor.setPosition(425, -440);
        cursor.setTextCoord(0.95f, 0.0f, 1.0f, 0.05f);
        addController(cursor);

        setFocus(0);
    }

    public void setFocus(int index){
        onHand.setPosition(4 + index*99.5f, 0);
    }
}