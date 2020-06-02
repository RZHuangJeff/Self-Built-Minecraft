package display;

import java.util.ArrayList;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import control.MatrixGenerator;
import control.TexturePack;
import graphic.Drawable;
import graphic.Mesh;
import graphic.Texture;

public class Model extends Drawable{
    protected ArrayList<Cube> components = new ArrayList<>();
    protected Texture texture = TexturePack.getTexture("pig");

    private Mesh mesh = null;
    protected ArrayList<Mesh> meshes = new ArrayList<>();

    public void buildMesh(){
        float[] vertices = new float[Cube.vertices.length];

        for(int i = 0; i < components.size(); i++){
            Cube cube = components.get(i);

            Matrix4f mat = MatrixGenerator.getWorldMatrix(cube.getPosition(), cube.getRotation(), cube.getScale());
            for(int j = 0; j < Cube.vertices.length; j += 3){
                int index = j;
                Vector4f v = new Vector4f(Cube.vertices[index], Cube.vertices[index + 1], Cube.vertices[index + 2], 1.0f).mul(mat);
                
                vertices[index] = v.x;
                vertices[index + 1] = v.y;
                vertices[index + 2] = v.z;
            }

            Mesh mesh = new Mesh(vertices, Cube.indices);
            mesh.setTextCoord(cube.getTextCoord());
            mesh.setTexture(texture);

            meshes.add(mesh);
        }
    }

    public Mesh getMesh(){
        return mesh;
    }

    public ArrayList<Mesh> getMeshes(){
        return meshes;
    }

    public static float[] buildTextCoord(Vector2f ltp, Vector2f rbp, Vector3f size){
        Vector2f offset = ltp;
        Vector2f scale = new Vector2f(rbp.x - ltp.x, rbp.y - ltp.y);

        float w = (size.x + size.z)*2;
        float h = size.y + size.z;

        float dow = size.z/w;
        float wow = size.x/w;
        float doh = size.z/h;

        return new float[]{
            //front
            offset.x + (2*dow + wow)*scale.x, offset.y + doh*scale.y,
            offset.x + (2*dow + wow)*scale.x, offset.y + scale.y,
            offset.x + scale.x, offset.y + scale.y,
            offset.x + scale.x, offset.y + doh*scale.y,
            //right
            offset.x + 0.5f*scale.x, offset.y + doh*scale.y,
            offset.x + 0.5f*scale.x, offset.y + scale.y,
            offset.x + (2*dow + wow)*scale.x, offset.y + scale.y,
            offset.x + (2*dow + wow)*scale.x, offset.y + doh*scale.y,
            //back
            offset.x + dow*scale.x, offset.y + doh*scale.y,
            offset.x + dow*scale.x, offset.y + scale.y,
            offset.x + 0.5f*scale.x, offset.y + scale.y,
            offset.x + 0.5f*scale.x, offset.y + doh*scale.y,
            //left
            offset.x + 0.f, offset.y + doh*scale.y,
            offset.x + 0.f, offset.y + scale.y,
            offset.x + dow*scale.x, offset.y + scale.y,
            offset.x + dow*scale.x, offset.y + doh*scale.y,
            //top
            offset.x + dow*scale.x, offset.y,
            offset.x + dow*scale.x, offset.y + doh*scale.y,
            offset.x + 0.5f*scale.x, offset.y + doh*scale.y,
            offset.x + 0.5f*scale.x, offset.y,
            //bottom
            offset.x + 0.5f*scale.x, offset.y,
            offset.x + 0.5f*scale.x, offset.y + doh*scale.y,
            offset.x + (0.5f + dow)*scale.x, offset.y + doh*scale.y,
            offset.x + (0.5f + dow)*scale.x, offset.y,
        };
    }
}