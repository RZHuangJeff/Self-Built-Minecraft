package graphic;

import org.joml.Vector3f;

public class Drawable {
    private Mesh mesh;

    private final Vector3f position;
    private final Vector3f scale;
    private final Vector3f rotation;

    public Drawable(){
        this.position = new Vector3f();
        this.rotation = new Vector3f();
        this.scale = new Vector3f(1, 1, 1);
    }

    public Drawable(Mesh mesh){
        this();
        this.mesh = mesh;
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

    public Mesh getMesh(){
        return mesh;
    }

    public void setMesh(Mesh m){
        mesh = m;
    }
}