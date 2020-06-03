package graphic;

import java.util.ArrayList;

import org.joml.Matrix4f;

import control.MatrixGenerator;
import control.Utils;
import display.Controller;
import display.Scene;
import display.Skybox;

public class Renderer {
    private ShaderProgram instancedShader;
    private ShaderProgram sceneShader;
    private ShaderProgram controllerShader;

    public void init() throws Exception{
        initInstancedShader();
        initSceneShader();
        initControllerShader();
    }

    private void initInstancedShader() throws Exception{
        instancedShader = new ShaderProgram();
        instancedShader.createVertexShader(Utils.loadResource("/shaders/inst_vertex.vs"));
        instancedShader.createFragmentShader(Utils.loadResource("/shaders/scene_fragment.fs"));
        instancedShader.link();

        instancedShader.createUniform("projectionMatrix");
        instancedShader.createUniform("numOfCol");
        instancedShader.createUniform("numOfRow");
        instancedShader.createUniform("texture_sampler");
    }

    private void initSceneShader() throws Exception{
        sceneShader = new ShaderProgram();
        sceneShader.createVertexShader(Utils.loadResource("/shaders/scene_vertex.vs"));
        sceneShader.createFragmentShader(Utils.loadResource("/shaders/scene_fragment.fs"));
        sceneShader.link();

        sceneShader.createUniform("projectionMatrix");
        sceneShader.createUniform("worldMatrix");
        sceneShader.createUniform("texture_sampler");
    }

    private void initControllerShader() throws Exception{
        controllerShader = new ShaderProgram();
        controllerShader.createVertexShader(Utils.loadResource("/shaders/ctrl_vertex.vs"));
        controllerShader.createFragmentShader(Utils.loadResource("/shaders/ctrl_fragm.fs"));
        controllerShader.link();

        controllerShader.createUniform("orthoMatrix");
        controllerShader.createUniform("worldMatrix");
        controllerShader.createUniform("texture_sampler");
        controllerShader.createUniform("colour");
    }

    public void render(Scene scene, Camera camera){
        instancedShader.bind();

        InstancedMesh mesh = scene.getMesh();

        Matrix4f pvMatrix = 
            new Matrix4f(MatrixGenerator.getProjectionMatrix(1920, 1080, 0.01f, 1000f, (float)Math.toRadians(60)));
        pvMatrix.mul(MatrixGenerator.getViewMatrix(camera));
        instancedShader.setUniform("projectionMatrix", pvMatrix);
        instancedShader.setUniform("numOfCol", mesh.getTextColumn());
        instancedShader.setUniform("numOfRow", mesh.getTextRow());
        instancedShader.setUniform("texture_sampler", 0);

        mesh.render(scene.getCubeList());
        mesh.render(scene.getGlasses());

        instancedShader.unbind();
    }

    public void render(ArrayList<Controller> controller){
        controllerShader.bind();

        controllerShader.setUniform("orthoMatrix", MatrixGenerator.getOrthoMatrix(1920, 1080, 1000));
        controllerShader.setUniform("texture_sampler", 0);
        for (Controller control : controller) {
            recursiveRender(control);
        }

        controllerShader.unbind();
    }

    private void recursiveRender(Controller controller){
        if(controller.visible){
            controllerShader.setUniform("worldMatrix", MatrixGenerator.getWorldMatrix(
                    controller.getPosition(), controller.getRotation(), controller.getScale()));
            controllerShader.setUniform("colour", controller.getColour());
            if(controller.getMesh() != null)
                controller.getMesh().render();

            for (Controller control : controller.getChildren()) {
                recursiveRender(control);
            }
        }
    }

    public void render(Skybox skybox){
        sceneShader.bind();

        sceneShader.setUniform("projectionMatrix", 
            MatrixGenerator.getProjectionMatrix(1920, 1080, 0.01f, 1000.f, (float)Math.toRadians(90)));
        sceneShader.setUniform("worldMatrix",
            MatrixGenerator.getWorldMatrix(skybox.getPosition(), skybox.getRotation(), skybox.getScale()));
        sceneShader.setUniform("texture_sampler", 0);
        skybox.getMesh().render();

        sceneShader.unbind();
    }
}