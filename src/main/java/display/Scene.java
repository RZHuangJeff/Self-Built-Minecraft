package display;

import java.util.ArrayList;

import control.TextureInfo;
import control.TexturePack;
import graphic.InstancedMesh;

public class Scene{
    private InstancedMesh mesh;
    private ArrayList<Cube> cubes;
    private ArrayList<Cube> glasses;

    public Scene(){
        mesh = new InstancedMesh(Cube.vertices, Cube.indices, 65536);
        mesh.setTextColRow(TextureInfo.UNIT1_SEPERATION.x, TextureInfo.UNIT1_SEPERATION.y);
        mesh.setTextCoord(Cube.textCoord);

        cubes = new ArrayList<>();
        glasses = new ArrayList<>();
    }

    public void setTexture(String name){
        mesh.setTexture(TexturePack.getTexture(name));
    }

    public void addCube(Cube cube){
        if(cube.getTextOffset().equals(TextureInfo.getCubeTextureOffset(TextureInfo.GLASS)))
            glasses.add(cube);
        else
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

    public ArrayList<Cube> getGlasses(){
        return glasses;
    }
}