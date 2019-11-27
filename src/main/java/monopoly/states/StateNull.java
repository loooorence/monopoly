package monopoly.states;

public class StateNull implements IState{
    @Override
    public void beginState() {

    }

    @Override
    public IState update() {
        return this;
    }

    @Override
    public boolean isStacked() {
        return false;
    }
}
