package monopoly.util;

public class Timer {

    private double lastTime;

    public void init() {
        lastTime = getTime();
    }

    public double getTime() {
        return System.nanoTime() / 1E9;
    }

    public double getDeltaTime() {
        double currentTime = getTime();
        double deltaTime = currentTime - lastTime;
        lastTime = currentTime;
        return deltaTime;
    }
}
