package display;

import control.TexturePack;
import graphic.Mesh;
import graphic.Texture;

public class Image extends Controller{
    private Texture texture;
    public Image(String path){
        try {
            texture = TexturePack.getTexture(path);
            float[] vertices = new float[]{
                0.0f, 0.0f, 0.0f,
                0.0f, 1.0f, 0.0f,
                1.0f, 1.0f, 0.0f,
                1.0f, 0.0f, 0.0f
            };
            float[] textCoord = new float[]{
                0.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 1.0f,
                1.0f, 0.0f
            };
            byte[] indices = new byte[]{
                0, 1, 3, 3, 1, 2
            };

            Mesh mesh = new Mesh(vertices, indices);
            mesh.setTexture(texture);
            mesh.setTextCoord(textCoord);

            setMesh(mesh);
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
        getMesh().setTextCoord(textCoord);
    }
}