package graphic;

import org.joml.Vector2f;
import org.joml.Vector3f;

public class Camera {
    private Vector3f position;
    private Vector2f rotation;

    public Camera(){
        position = new Vector3f();
        rotation = new Vector2f();
    }

    public Vector3f getPosition(){
        return position;
    }

    public void setPostion(float x, float y, float z){
        position.x = x;
        position.y = y;
        position.z = z;
    }

    public Vector2f getRotation(){
        return rotation;
    }

    public void setRotation(int rx, int ry){
        rotation.x = rx;
        rotation.y = ry;
    }
}