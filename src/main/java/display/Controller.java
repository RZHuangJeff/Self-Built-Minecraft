package display;

import java.util.ArrayList;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import graphic.Drawable;
import graphic.Mesh;

public class Controller extends Drawable{
    public boolean visible = true;

    protected Mesh mesh;

    private Vector4f colour = new Vector4f(1.0f, 1.0f, 1.0f, 1.0f);

    private Controller parent = null;
    private ArrayList<Controller> children = new ArrayList<>();

    public static float[] vertices = new float[]{
        0.0f, 0.0f, 0.0f,
        0.0f, 1.0f, 0.0f,
        1.0f, 1.0f, 0.0f,
        1.0f, 0.0f, 0.0f
    };

    public static int indices[] = new int[]{
        0, 1, 3, 3, 1, 2
    };

    public void setPosition(float x, float y){
        setPosition(x, y, 0.0f);
    }

    public Vector3f getPosition(){
        Vector3f result = new Vector3f(super.getPosition());
        if(parent != null){
            Vector3f parentPos = parent.getPosition();
            result.x += parentPos.x;
            result.y += parentPos.y;
            result.z += parentPos.z;
        }

        return result;
    }

    public void setSize(float width, float height){
        setScale(width, height, 1);
    }

    public void addController(Controller controller){
        controller.parent = this;
        children.add(controller);
    }

    public void setColour(float r, float g, float b, float a){
        this.colour.x = r;
        this.colour.y = g;
        this.colour.z = b;
        this.colour.w = a;
    }

    public Vector4f getColour(){
        return colour;
    }

    public Mesh getMesh(){
        return mesh;
    }

    public ArrayList<Controller> getChildren(){
        return children;
    }

    public boolean isHover(Vector2f cursorPos){
        boolean inX = cursorPos.x >= getPosition().x && cursorPos.x <= getPosition().x + getScale().x;
        boolean inY = cursorPos.y >= getPosition().y && cursorPos.y <= getPosition().y + getScale().y;

        return inX && inY;
    }
}