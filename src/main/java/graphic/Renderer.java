package graphic;

import java.util.ArrayList;

import control.MatrixGenerator;
import control.Utils;
import control.Window;
import display.Controller;
import display.Cube;
import display.Skybox;

public class Renderer {
    private ShaderProgram sceneShader;
    private ShaderProgram controllerShader;

    public void init() throws Exception{
        initSceneShader();
        initControllerShader();
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

    public void render(Window window, Skybox skybox, Cube cube, ArrayList<Controller> controller){
        if(skybox != null)
            renderSkybox(skybox);

        window.enableDepthTest();
        if(cube != null)
            renderCube(cube);
        window.disableDepthTest();
            
        if(controller != null)
            renderControllers(controller);
    }

    public void render(Skybox skybox, Cube cube, ArrayList<Controller> controller){
        if(skybox != null)
            renderSkybox(skybox);

        if(cube != null)
            renderCube(cube);
            
        if(controller != null)
            renderControllers(controller);
    }

    private void renderControllers(ArrayList<Controller> controller){
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

    private void renderSkybox(Skybox skybox){
        sceneShader.bind();

        sceneShader.setUniform("projectionMatrix", 
            MatrixGenerator.getProjectionMatrix(1920, 1080, 0, 1000, (float)Math.toRadians(90)));
        sceneShader.setUniform("worldMatrix",
            MatrixGenerator.getWorldMatrix(skybox.getPosition(), skybox.getRotation(), skybox.getScale()));
        sceneShader.setUniform("texture_sampler", 0);
        skybox.getMesh().render();

        sceneShader.unbind();
    }

    private void renderCube(Cube cube){
        sceneShader.bind();
        sceneShader.setUniform("projectionMatrix", 
            MatrixGenerator.getProjectionMatrix(1920, 1080, 0.01f, 1000f, (float)Math.toRadians(90)));
        sceneShader.setUniform("worldMatrix",
            MatrixGenerator.getWorldMatrix(cube.getPosition(), cube.getRotation(), cube.getScale()));
        sceneShader.setUniform("texture_sampler", 0);
        cube.getMesh().render();

        sceneShader.unbind();
    }
}