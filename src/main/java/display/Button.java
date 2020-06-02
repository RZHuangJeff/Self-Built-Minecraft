package display;

import control.TexturePack;
import graphic.Mesh;

public class Button extends Controller{
    private final float[] defaultTextCoord = new float[]{
        0.001f, 0.258f,
        0.001f, 0.336f,
        0.781f, 0.336f,
        0.781f, 0.258f
    };

    private final float[] selectTextCoord = new float[]{
        0.001f, 0.336f,
        0.001f, 0.414f,
        0.781f, 0.414f,
        0.781f, 0.336f
    };

    private Label text;

    public Button(){
        mesh = new Mesh(vertices, indices);
        mesh.setTexture(TexturePack.getTexture("widgets"));
        unSelect();
    }

    public void select(){
        mesh.setTextCoord(selectTextCoord);
    }

    public void unSelect(){
        mesh.setTextCoord(defaultTextCoord);
    }

    public void setText(String text){
        if(this.text == null)
            this.text = new Label();

        this.text.setText(text);
        addController(this.text);
    }

    public void setTextPosition(float x, float y){
        text.setPosition(x, y);
    }

    public void setTextSize(float size){
        text.setSize(size, size);
    }

    public float getLabelWidth(){
        return this.text.getWidth();
    }

    public void setTextColour(float r, float g, float b, float a){
        text.setColour(r, g, b, a);
    }
}