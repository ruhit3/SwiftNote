package SingletonPattern;

import MediatorPattern.Mediator;
import MediatorPattern.NewNoteButton;
import MediatorPattern.NoteColorPicker;
import MediatorPattern.NoteListView;
import MediatorPattern.StatusLabel;
import MediatorPattern.TitleTextField;
import Swiftnote.Notes;
import java.util.ArrayList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ManagerUI {

    // singleton object
    private static ManagerUI uniqueInstance;

    // singleton constructor
    private ManagerUI() {

    }

    // singleton method
    public static synchronized ManagerUI getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ManagerUI();
        }
        return uniqueInstance;
    }

    private Stage stage;
    private static double xOffset = 0, yOffset = 0;
    private String icons[] = {"file:resources/icons/logo.png", "file:resources/icons/close0.png", "file:resources/icons/close1.png"};
    private String fonts[] = {"file:resources/fonts/Bohemian Typewriter.ttf", "file:resources/fonts/Qarmic sans Abridged.ttf"};

    Mediator mediator = new Mediator();
    ArrayList<Notes> noteList = new  ArrayList<Notes>();

    public void sendNotes(ArrayList<Notes> noteList) {
        this.noteList = noteList;
        System.out.println("Initial note count: " + this.noteList.size());
    }

    public void createManagerUI(Stage stage) {
        this.stage = stage;
        stage.initStyle(StageStyle.UNDECORATED);

        FlowPane root2 = new FlowPane(new TitleTextField(mediator), new NoteColorPicker(mediator), new NewNoteButton(mediator));
        root2.setAlignment(Pos.CENTER);
        root2.setHgap(20);
        root2.setVgap(10);

        VBox root = new VBox(header(), new Separator(), root2, new NoteListView(mediator, noteList), new StatusLabel(mediator));
        root.setAlignment(Pos.TOP_CENTER);
        root.setSpacing(10);
        root.setPadding(new Insets(5));
        root.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #808080");

        Scene scene = new Scene(root, 320, 580);
        this.stage.setScene(scene);
        this.stage.setX(150);
        this.stage.setY(50);
        this.stage.show();
    }

    private GridPane header() {
        GridPane gridPane = new GridPane();
        gridPane.getColumnConstraints().addAll(new ColumnConstraints(25), new ColumnConstraints(250));

        makeMouseDraggable(gridPane);

        Label logo = new Label();
        logo.setGraphic(new ImageView(new Image(icons[0])));
        gridPane.add(logo, 0, 0);

        Text titleText = new Text("Swiftnote");
        titleText.setFont(Font.loadFont(fonts[0], 16));
        GridPane.setValignment(titleText, VPos.CENTER);
        GridPane.setHalignment(titleText, HPos.CENTER);
        gridPane.add(titleText, 1, 0);
        gridPane.add(closeButton(), 2, 0);
        return gridPane;
    }

    private Button closeButton() {
        Button close = new Button();
        close.setDefaultButton(true);
        close.setStyle("-fx-background-color: #FFFFFF;");
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
