package display.panel;

import display.Image;
import display.Label;

public class ItemPanel extends Panel{
    private Image item;
    private Label count;

    public ItemPanel(){
        item = new Image("apple");
        item.setSize(78, 78);
        addController(item);

        count = new Label();
        count.setText("10");
        count.setTextSize(25);
        count.setPosition(40, 53);
        addController(count);
    }

    public void setCount(int count){
        String newCount = count < 10 ? " " + count : "" + count;
        this.count.setText(newCount);
    }

    public void setTextCoord(float ltx, float lty, float rbx, float rby){
        item.setTextCoord(ltx, lty, rbx, rby);
    }
}