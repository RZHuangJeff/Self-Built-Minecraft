package control;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

import graphic.Camera;

public class MatrixGenerator {
    private static Matrix4f ortho = new Matrix4f();
    private static Matrix4f projection = new Matrix4f();
    private static Matrix4f world = new Matrix4f();
    private static Matrix4f view = new Matrix4f();


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

    public static Matrix4f getViewMatrix(Camera camera){
        Vector3f position = camera.getPosition();
        Vector2f rotation = camera.getRotation();

        view.identity();
        view.rotate((float)Math.toRadians(rotation.x), new Vector3f(1, 0, 0))
            .rotate((float)Math.toRadians(rotation.y), new Vector3f(0, 1, 0));

        view.translate(-position.x, -position.y, -position.z);
        return view;
    }
}