package control;

public class GameLooper implements Runnable{
    public static final int fps = 75;
    public static final int ups = 60;

    private final Window window;
    private final Timer timer;
    private final LayerController layerController;

    private final KeyboardInput keyboard;
    private final MouseInput mouse;

    public GameLooper(Window window, LayerController layerController){
        this.window = window;
        this.timer = new Timer();
        this.layerController = layerController;
        this.keyboard = new KeyboardInput(window);
        this.mouse = new MouseInput(window);
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
        TexturePack.init();
        timer.init();
        mouse.init();
        keyboard.init();
        layerController.init();
    }

    private void startLooping(){
        float elapsedTime;
        float accumulator = 0f;
        float interval = 1f/ups;

        while (!layerController.isEmpty()) {
            elapsedTime = timer.getElapsedTime();
            accumulator += elapsedTime; 

            handleInput();

            while(accumulator >= interval){
                update(interval);
                accumulator -= interval;
            }

            render();
        }
    }

    private void handleInput(){
        mouse.input();
        layerController.input(keyboard, mouse);
    }

    private void update(float interval){
        layerController.update(interval);
    }

    private void render(){
        layerController.render(window);
        window.update();
    }
}