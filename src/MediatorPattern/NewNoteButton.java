package MediatorPattern;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class NewNoteButton extends Button {

    private final Mediator mediator;
    private String icons[] = {"file:resources/icons/plus0.png", "file:resources/icons/plus1.png",};

    public NewNoteButton(final Mediator mediator) {
        this.mediator = mediator;
        this.mediator.setNewNoteButton(this);
        this.mediator.getNewNoteButton().setOnAction(buttonHandler);
        this.setText("New Note");
        this.setStyle("-fx-background-color: #FFFFFF; -fx-font-weight: bold;");
        this.setGraphic(new ImageView(new Image(icons[0])));
        this.setOnMouseEntered((MouseEvent event) -> {
            this.setGraphic(new ImageView(new Image(icons[1])));
        });
        this.setOnMouseExited((MouseEvent event) -> {
            this.setGraphic(new ImageView(new Image(icons[0])));
        });
        this.setDisable(true);
    }

    EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            mediator.addClickAction();
        }
    };
}
