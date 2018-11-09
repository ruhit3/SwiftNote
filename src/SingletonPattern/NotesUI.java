package SingletonPattern;

import MediatorPattern.Mediator;
import ObserverPattern.NoteData;
import ObserverPattern.SizeDisplay;
import java.util.concurrent.ThreadLocalRandom;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Swiftnote.Notes;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class NotesUI {

    private Notes notes;
    private Stage stage;
    private static double xOffset = 0;
    private static double yOffset = 0;
    private String icons[] = {"file:resources/icons/logo.png", "file:resources/icons/close0.png", "file:resources/icons/close1.png"};
    private String fonts[] = {"file:resources/fonts/Bohemian Typewriter.ttf", "file:resources/fonts/Qarmic sans Abridged.ttf"};
    private Label noteSize;
    private TextArea textArea;
    private Mediator mediator;

    public NotesUI(Notes notes) {
        this.notes = notes;
        createNotesUI();
    }

    public NotesUI(Notes notes, Mediator mediator) {
        this.notes = notes;
        this.mediator = mediator;
        createNotesUI();
    }

    private void createNotesUI() {
        VBox root = new VBox();
        root.setStyle("-fx-background-color: " + makeColor(notes.getColor().saturate()) + ";"
            + "-fx-border-color: " + makeColor(notes.getColor().darker()) + ";");

        root.getChildren().addAll(header(), body(), footer());
        Scene scene = new Scene(root, 320, 290);
        stage = new Stage(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.setX(ThreadLocalRandom.current().nextDouble(500, 1000));
        stage.setY(ThreadLocalRandom.current().nextDouble(50, 450));
        stage.show();
    }

    private GridPane header() {
        GridPane gridPane = new GridPane();
        gridPane.getColumnConstraints().addAll(new ColumnConstraints(25), new ColumnConstraints(250));
        gridPane.setStyle("-fx-background-color: " + makeColor(notes.getColor().saturate()) + ";");

        makeMouseDraggable(gridPane);

        Label logo = new Label();
        logo.setGraphic(new ImageView(new Image(icons[0])));
        gridPane.add(logo, 0, 0);

        Text titleText = new Text(notes.getTitle());
        titleText.setFont(Font.loadFont(fonts[0], 16));
        GridPane.setValignment(titleText, VPos.CENTER);
        GridPane.setHalignment(titleText, HPos.CENTER);
        gridPane.add(titleText, 1, 0);
        gridPane.add(closeButton(), 2, 0);
        return gridPane;
    }

    private TextArea body() {
        textArea = new TextArea();
        textArea.setPrefRowCount(12);
        textArea.setWrapText(true);
        textArea.setFont(Font.loadFont(fonts[1], 12));
        textArea.setStyle("-fx-control-inner-background: " + makeColor(notes.getColor()) + ";");
        textArea.setPromptText("Take a note...");
        textArea.setText(notes.getContent());

        /* Observer Pattern implemented here :) */
        NoteData noteData = new NoteData();
        SizeDisplay sizeDisplay = new SizeDisplay(noteData);
        textArea.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            noteData.setText(newValue);
            noteSize.setText("Note size: " + sizeDisplay.display() + " bytes");
        });
        return textArea;
    }

    private Label noteSizeLabel() {
        noteSize = new Label("Note size: " + textArea.getText().length() + " bytes");
        noteSize.setStyle("-fx-font-weight: bold;");
        return noteSize;
    }

    private GridPane footer() {
        GridPane gridPane = new GridPane();
        gridPane.getColumnConstraints().addAll(new ColumnConstraints(155), new ColumnConstraints(155));
        gridPane.setPadding(new Insets(5));
        gridPane.add(noteSizeLabel(), 0, 0);
        HBox hbox = new HBox(5);
        hbox.getChildren().addAll(saveButton(), undoButton());
        hbox.setAlignment(Pos.CENTER_RIGHT);
        gridPane.add(hbox, 1, 0);
        return gridPane;
    }

    private Button saveButton() {
        Button save = new Button("Save");
        save.setStyle("-fx-background-color: " + makeColor(notes.getColor()) + "; -fx-font-weight: bold;");
        save.setOnMouseEntered((MouseEvent event) -> {
            save.setStyle("-fx-background-color: " + makeColor(notes.getColor().saturate().saturate()) + "; -fx-font-weight: bold;");
        });
        save.setOnMouseExited((MouseEvent event) -> {
            save.setStyle("-fx-background-color: " + makeColor(notes.getColor()) + "; -fx-font-weight: bold;");
        });
        save.setOnAction(saveButtonHandler);
        return save;
    }

    EventHandler<ActionEvent> saveButtonHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            notes.setContent(textArea.getText());
            mediator.saveAction(notes);
        }
    };

    private Button undoButton() {
        Button undo = new Button("Undo");
        undo.setStyle("-fx-background-color: " + makeColor(notes.getColor()) + "; -fx-font-weight: bold;");
        undo.setOnMouseEntered((MouseEvent event) -> {
            undo.setStyle("-fx-background-color: " + makeColor(notes.getColor().saturate().saturate()) + "; -fx-font-weight: bold;");
        });
        undo.setOnMouseExited((MouseEvent event) -> {
            undo.setStyle("-fx-background-color: " + makeColor(notes.getColor()) + "; -fx-font-weight: bold;");
        });
        return undo;
    }

    private Button closeButton() {
        Button close = new Button();
        close.setStyle("-fx-background-color: " + makeColor(notes.getColor().saturate()) + ";");
        close.setGraphic(new ImageView(new Image(icons[1])));
        close.setOnMouseEntered((MouseEvent event) -> {
            close.setGraphic(new ImageView(new Image(icons[2])));
        });
        close.setOnMouseExited((MouseEvent event) -> {
            close.setGraphic(new ImageView(new Image(icons[1])));
        });
        close.setOnMouseClicked((MouseEvent e) -> {
            stage.close();
        });
        return close;
    }

    private String makeColor(Color color) {
        String colorCode = color.toString();
        colorCode = colorCode.substring(2, 8);
        colorCode = "#".concat(colorCode);
        return colorCode;
    }

    private void makeMouseDraggable(GridPane gridPane) {
        gridPane.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        gridPane.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
    }
}
