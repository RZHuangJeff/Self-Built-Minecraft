package control;

import java.util.ArrayList;

import org.joml.Intersectionf;
import org.joml.Vector2f;
import org.joml.Vector3f;

import display.Cube;
import graphic.Camera;

public class CameraPicker {
    public static Cube cameraPick(ArrayList<Cube> cubes, Camera camera){
        Cube picked = null;
        float closestDistance = 6f;

        Vector3f dir = new Vector3f();
        Vector3f min = new Vector3f();
        Vector3f max = new Vector3f();
        Vector2f result = new Vector2f();
        dir = MatrixGenerator.getViewMatrix(camera).positiveZ(dir).negate();
        for (Cube cube : cubes) {
            min.set(cube.getPosition());
            max.set(cube.getPosition());

            Vector3f scale = new Vector3f(cube.getScale());
            scale.x /= 2;
            scale.y /= 2;
            scale.z /= 2;

            min.add(-scale.x, -scale.y, -scale.z);
            max.add(scale.x, scale.y, scale.z);
            if(Intersectionf.intersectRayAab(camera.getPosition(), dir, min, max, result) && result.x < closestDistance){
                closestDistance = result.x;
                picked = cube;
            }
        }

        return picked;
    }
}