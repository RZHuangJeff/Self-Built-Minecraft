package display;

import control.TexturePack;
import graphic.Mesh;

public class Skybox extends Cube{
    private Mesh mesh;

    public Skybox(){
        mesh = new Mesh(vertices, indices);
        mesh.setTexture(TexturePack.getTexture("skybox"));
        mesh.setTextCoord(textCoord);
    }

    public Mesh getMesh(){
        return mesh;
    }
}