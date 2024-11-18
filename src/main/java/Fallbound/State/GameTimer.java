package Fallbound.State;

public class GameTimer {
    private long startTime;
    private long pauseTime;
    private long totalPausedTime;
    private boolean isPaused;

    public GameTimer() {
        this.startTime = 0;
        this.pauseTime = 0;
        this.totalPausedTime = 0;
        this.isPaused = false;
    }

    public void start() {
        this.startTime = System.currentTimeMillis();
        this.isPaused = false;
    }

    public void pause() {
        if (!isPaused) {
            this.pauseTime = System.currentTimeMillis();
            this.isPaused = true;
        }
    }

    public void resume() {
        if (isPaused) {
            this.totalPausedTime += System.currentTimeMillis() - this.pauseTime;
            this.isPaused = false;
        }
    }

    public long getElapsedTime() {
        if (isPaused) {
            return (pauseTime - startTime - totalPausedTime) / 1000;
        } else {
            return (System.currentTimeMillis() - startTime - totalPausedTime) / 1000;
        }
    }
}
