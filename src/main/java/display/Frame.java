package display;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import control.*;

abstract public class Frame {
    protected final Window window;
    protected final GameLoopingController controller;

    public Frame(Window window, GameLoopingController controller){
        this.window = window;
        this.controller = controller;
    }

    abstract public void init() throws Exception;
    abstract public void input();
    abstract public void update();
    abstract public void render();

    protected void clear(){
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }
}