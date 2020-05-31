package display.panel;

import display.Image;

public class BackpackPanel extends Panel{
    private Image background;
    private Image[][] contains = new Image[9][3];
    private Image[] inHud = new Image[9];
    
    public BackpackPanel(){
        background = new Image("backpack");
        background.setSize(900, 900);
        background.setTextCoord(0.0f, 0.0f, 0.69f, 0.645f);
        addController(background);

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 3; j++){
                contains[i][j] = new Image("apple");
                contains[i][j].setPosition(42 + i*91.5f, 460 + j*98);
                contains[i][j].setSize(78, 78);
                addController(contains[i][j]);
            }

            inHud[i] = new Image("apple");
            inHud[i].setPosition(42 + i*91.5f, 775);
            inHud[i].setSize(78, 78);
            addController(inHud[i]);
        }
    }
}