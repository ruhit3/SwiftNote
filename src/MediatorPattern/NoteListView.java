package MediatorPattern;

import Swiftnote.Notes;
import java.util.ArrayList;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class NoteListView extends ListView {

    private final Mediator mediator;

    public NoteListView(final Mediator mediator, ArrayList<Notes> noteList) {
        this.mediator = mediator;
        this.mediator.setNoteListView(this);
        for (Notes notes : noteList) {
            this.mediator.observableNoteList.add(notes.getTitle());
        }
        this.mediator.getNoteListView().setItems(this.mediator.observableNoteList);
        this.mediator.getNoteListView().setOnMouseClicked((MouseEvent event) -> {
            this.mediator.showNoteFromListView();
        });
        this.setStyle("-fx-font-weight: bold;");
    }

}