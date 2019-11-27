package engine;

public interface IGameLogic {
    void init(Window window) throws Exception;

    void inputKeyboard(int key, int scancode, int action, int mods);

    void inputMouseButton(int button, int action, int mods, float xPos, float yPos);

    void update();

    void render(double alpha);

    void cleanup();
}
