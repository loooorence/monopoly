package main;

public class Main {

    public static void main(String[] args) {
        IGameLogic gameLogic = new MonopolyLogic();
        GameEngine engine = new GameEngine("Monopoly", 800, 600, true, gameLogic);
        engine.run();
    }
}
