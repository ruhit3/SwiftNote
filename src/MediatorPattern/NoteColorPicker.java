package MediatorPattern;

import javafx.scene.control.ColorPicker;

public class NoteColorPicker extends ColorPicker {

    private final Mediator mediator;

    public NoteColorPicker(final Mediator mediator) {
        this.mediator = mediator;
        this.mediator.setNoteColorPicker(this);
        this.setMaxWidth(100);
        this.getStyleClass().add("button");
        this.setStyle("-fx-background-radius: 15 15 15 15;");
    }
}
