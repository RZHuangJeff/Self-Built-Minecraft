package display;

import control.TexturePack;
import graphic.Mesh;
import graphic.Texture;

public class Image extends Controller{
    private Texture texture;

    public static float[] defaultTextCoord = new float[]{
        0.0f, 0.0f,
        0.0f, 1.0f,
        1.0f, 1.0f,
        1.0f, 0.0f
    };

    public Image(String path){
        try {
            mesh = new Mesh(vertices, indices);
            mesh.setTexture(texture);
            mesh.setTexture(TexturePack.getTexture(path));
            mesh.setTextCoord(defaultTextCoord);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTextCoord(float ltx, float lty, float rbx, float rby){
        float[] textCoord = new float[]{
            ltx, lty,
            ltx, rby,
            rbx, rby,
            rbx, lty
        };
        mesh.setTextCoord(textCoord);
    }
}