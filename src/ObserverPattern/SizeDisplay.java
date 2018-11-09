package ObserverPattern;

import javafx.scene.control.Label;

public class SizeDisplay implements Observer {

    private Subject noteData;
    private String text;

    public SizeDisplay(Subject noteData) {
        this.noteData = noteData;
        noteData.registerObserver(this);
    }

    public void update(String text) {
        this.text = text;
        display();
    }

    public int display() {
        return text.length();
    }
}
