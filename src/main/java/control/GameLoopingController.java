package control;

import java.util.ArrayList;

import display.*;

public class GameLoopingController implements Runnable{
    public static final int fps = 75;
    public static final int ups = 30;

    private final Window window;
    private final Timer timer;

    private ArrayList<Frame> frameList;

    public GameLoopingController(Window window){
        this.window = window;
        this.timer = new Timer();
        this.frameList = new ArrayList<>();
    }

    public void run(){
        try {
            init();
            startLooping();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() throws Exception{
        window.init();
        timer.init();
    }

    private void startLooping(){
        float elapsedTime;
        float accumulator = 0f;
        float interval = 1f/ups;

        while (frameList.size() != 0 && !window.windowShouldClose()) {
            elapsedTime = timer.getElapsedTime();
            accumulator += elapsedTime; 

            handleInput();

            while(accumulator >= interval){
                update();
                accumulator -= interval;
            }

            render();
        }
    }

    private void handleInput(){
        frameList.get(frameList.size() - 1).input();
    }

    private void update(){
        frameList.get(frameList.size() - 1).update();
    }

    private void render(){
        frameList.get(frameList.size() - 1).render();
        window.update();
    }

    public void addFrame(Frame frame){
        frameList.add(frame);
    }

    public void removeLastFrame(){
        frameList.remove(frameList.size() - 1);
    }
}