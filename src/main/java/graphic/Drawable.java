package graphic;

import org.joml.Vector2f;
import org.joml.Vector3f;

public class Drawable {
    private final Vector3f position;
    private final Vector3f scale;
    private final Vector3f rotation;
    private final Vector2f textOffset;

    public Drawable(){
        this.position = new Vector3f();
        this.rotation = new Vector3f();
        this.textOffset = new Vector2f();
        this.scale = new Vector3f(1, 1, 1);
    }

    public Vector3f getPosition(){
        return position;
    }

    public void setPosition(float x, float y, float z){
        this.position.x = x;
        this.position.y = y;
        this.position.z = z;
    }

    public Vector3f getScale(){
        return scale;
    }

    public void setScale(float x, float y, float z){
        this.scale.x = x;
        this.scale.y = y;
        this.scale.z = z;
    }

    public Vector3f getRotation(){
        return rotation;
    }

    public void setRotation(float x, float y, float z){
        this.rotation.x = x;
        this.rotation.y = y;
        this.rotation.z = z;
    }

    public void setTextOffset(float x, float y){
        this.textOffset.x = x;
        this.textOffset.y = y;
    }

    public Vector2f getTextOffset(){
        return textOffset;
    }
}