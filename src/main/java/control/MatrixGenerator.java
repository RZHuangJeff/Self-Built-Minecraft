package control;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class MatrixGenerator {
    private static Matrix4f ortho = new Matrix4f();
    private static Matrix4f projection = new Matrix4f();
    private static Matrix4f world = new Matrix4f();


    public static Matrix4f getOrthoMatrix(float width, float height, float depth){
        return ortho.identity().setOrtho2D(0, width, height, 0);
    }

    public static Matrix4f getProjectionMatrix(float width, float height, float zNear, float zFar, float fov){
        return projection.identity().setPerspective(fov, width/height, zNear, zFar);
    }

    public static Matrix4f getWorldMatrix(Vector3f position, Vector3f rotation, Vector3f scale){
        return world.translation(position)
                .rotateX(rotation.x)
                .rotateY(rotation.y)
                .rotateZ(rotation.z)
                .scale(scale);
    }
}