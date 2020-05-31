package display;

import control.TexturePack;
import graphic.Mesh;
import graphic.Texture;

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
        float[] vertices = new float[]{
            0.0f, 0.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            1.0f, 1.0f, 0.0f,
            1.0f, 0.0f, 0.0f
        };
        byte indices[] = new byte[]{
            0, 1, 3, 3, 1, 2
        };

        setMesh(new Mesh(vertices, indices));
        getMesh().setTexture(TexturePack.getTexture("widgets"));
        unSelect();
    }

    public void select(){
        this.getMesh().setTextCoord(selectTextCoord);
    }

    public void unSelect(){
        this.getMesh().setTextCoord(defaultTextCoord);
    }

    /*public void setTexture(Texture texture){
        this.getMesh().setTexture(texture);
        this.getMesh().setTextCoord(defaultTextCoord);
    }*/

    public void setText(String text){
        if(this.text == null){
            this.text = new Label();
            setTextPosition(0, 0);
        }

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