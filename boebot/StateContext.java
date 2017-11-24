package boebot;


/**
 * Write a description of class StateContext here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class StateContext implements Updatable
{
    private State currentState;
    private State lastState;
    private Robot robot;
    
    public StateContext(State initialState, Robot robot) {
        this.currentState = initialState;
        this.robot = robot;
    }
    
    public void setState(State newState) {
        this.lastState = this.currentState;
        this.currentState = newState;
        this.currentState.init();
        System.out.println("Switched to state: " + newState);
    }
    
    public void update() {
        this.currentState.update(this, this.robot);
    }
    
    public void goBack() {
        this.setState(this.lastState);
        System.out.println("Went back to lastState: " + this.lastState);
    }
}
