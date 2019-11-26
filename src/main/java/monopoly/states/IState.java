package monopoly.states;

public interface IState {

    /**
     * Takes actions immediately upon entering this state.
     */
    void beginState();

    /**
     * Updates this state.
     * Actions that need to be taken are done through this method.<br>
     *
     * @return <code>null</code> if state should be popped<br>
     *         <code>this</code> if state should not be changed<br>
     *         A new state object to direct state machine to the next state
     */
    IState update();

    /**
     * Determines if the previous state should be
     * popped or not before this state is pushed.
     *
     * @return whether to stack this state
     */
    boolean isStacked();
}
