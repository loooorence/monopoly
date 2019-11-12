package engine;

public interface IGameLogic {
    void init(Window window) throws Exception;

    void input(Window window, int key, int scancode, int action, int mods);

    void update();

    void render(Window window);

    void cleanup();
}
