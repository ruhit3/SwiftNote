package MediatorPattern;

import javafx.geometry.Pos;
import javafx.scene.control.Label;

public class StatusLabel extends Label {

    private final Mediator mediator;

    public StatusLabel(final Mediator mediator) {
        this.mediator = mediator;
        this.mediator.setStatusLabel(this);
        this.setText("Status label");
        this.setStyle("-fx-font-weight: bold;");
        this.setAlignment(Pos.CENTER_LEFT);
    }
}
