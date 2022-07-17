package common.game.engine.core;

import common.CommonConstants;
import common.game.engine.Container;

public class Runner extends Thread {
    private boolean running = false;
    private final double UPDATE_CAP = 1.0/CommonConstants.FRAMES_PER_SECOND;
    private Container container;

    public Runner(Container container) {
        this.container = container;
    }

    public void run() {
        running = true;

        boolean render = false;
        double firstTime = 0;
        double lastTime = System.nanoTime() / 1000000000.0;
        double passedTime = 0;
        double unprocessedTime = 0;

        double frameTime = 0;
        int frames = 0;
        int fps = 0;

        while (running) {
            render = false;

            firstTime = System.nanoTime() / 1000000000.0;
            passedTime = firstTime - lastTime;
            lastTime = firstTime;

            unprocessedTime += passedTime;
            frameTime += passedTime;

            while (unprocessedTime >= UPDATE_CAP) {
                unprocessedTime -= UPDATE_CAP;
                render = true;
                container.update();

                if (frameTime >= 1.0) {
                    frameTime = 0;
                    fps = frames;
                    frames = 0;
                    if (CommonConstants.DEBUG_MODE && CommonConstants.RUNNER_DEBUG) {
                        System.out.println("FPS " + fps);   
                    }
                }
            }

            if (render) {
                container.render();
                frames++;
            }else{
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
