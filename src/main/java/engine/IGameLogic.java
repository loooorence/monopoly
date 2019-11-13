package engine;

public interface IGameLogic {
    void init(Window window) throws Exception;

    void inputKeyboard(int key, int scancode, int action, int mods);

    void inputMouseButton(int button, int action, int mods);

    void update();

    void render(double alpha);

    void cleanup();
}
