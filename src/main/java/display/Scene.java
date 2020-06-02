package display;

import java.util.ArrayList;

import control.TextureInfo;
import control.TexturePack;
import graphic.InstancedMesh;

public class Scene{
    private InstancedMesh mesh;
    private ArrayList<Cube> cubes;

    public Scene(){
        mesh = new InstancedMesh(Cube.vertices, Cube.indices, 65536);
        mesh.setTextColRow(TextureInfo.UNIT1_SEPERATION.x, TextureInfo.UNIT1_SEPERATION.y);
        mesh.setTextCoord(Cube.textCoord);

        cubes = new ArrayList<>();
    }

    public void setTexture(String name){
        mesh.setTexture(TexturePack.getTexture(name));
    }

    public void addCube(Cube cube){
        cubes.add(cube);
    }

    public void clearCubeList(){
        cubes.clear();
    }

    public InstancedMesh getMesh(){
        return mesh;
    }

    public ArrayList<Cube> getCubeList(){
        return cubes;
    }
}