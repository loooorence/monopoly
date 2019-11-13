package engine;

public interface IGameLogic {
    void init(Window window) throws Exception;

    void input(int key, int scancode, int action, int mods);

    void update();

    void render(double alpha);

    void cleanup();
}
