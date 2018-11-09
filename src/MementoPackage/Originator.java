package MementoPackage;

public class Originator {

    private String state;

    public void setState(String state) {
        System.out.println("Set state to: " + state);
        this.state = state;
    }

    public Memento save() {
        System.out.println("Save to memento");
        return new Memento(state);
    }

    public void restore(Memento m) {
        state = m.getState();
        System.out.println("State after restoring: " + state);
    }
}
