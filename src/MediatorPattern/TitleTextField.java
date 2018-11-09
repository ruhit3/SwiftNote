package MediatorPattern;

import javafx.geometry.Insets;
import javafx.scene.control.TextField;

public class TitleTextField extends TextField {

    private final Mediator mediator;

    public TitleTextField(final Mediator mediator) {
        this.mediator = mediator;
        this.mediator.setTextField(this);
        this.mediator.getTextField().textProperty().addListener((observable, oldValue, newValue) -> {
            this.mediator.textValueChanged();
        });
        this.setPromptText("Title...");
        this.setMaxWidth(160);
        this.setStyle("-fx-background-radius: 15 15 15 15; -fx-font-weight: bold;");
    }
}
