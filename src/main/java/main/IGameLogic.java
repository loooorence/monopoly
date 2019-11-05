package main;

public interface IGameLogic {
    void init();

    void input(Window window, int key, int scancode, int action, int mods);

    void update();

    void render(Window window);
}
