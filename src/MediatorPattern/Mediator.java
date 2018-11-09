package MediatorPattern;

import SingletonPattern.NotesUI;
import Swiftnote.FileManager;
import java.util.ArrayList;
import java.util.Random;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import Swiftnote.Notes;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Mediator {

    private NewNoteButton newNoteButton;
    private TitleTextField titleTextField;
    private NoteListView noteListView;
    private NoteColorPicker noteColorPicker;
    private StatusLabel statusLabel;

    private int id;
    private String title, content;
    private Color color;
    private Random random = new Random();
    private FileManager fileManager = new FileManager();

    ObservableList<String> observableNoteList = FXCollections.observableArrayList();
    ArrayList<Notes> noteList = fileManager.fetchNotes();

    public void showNoteFromListView() {
        String item = (String) noteListView.getSelectionModel().getSelectedItem();
        for (int i = 0; i < noteList.size(); i++) {
            Notes note = noteList.get(i);
            if (note.getTitle().compareTo(item) == 0) {
                statusLabel.setText("Opening note: " + note.getTitle());
                new NotesUI(note, this);
                break;
            }
        }
    }

    public void saveAction(Notes notes) {
        for (int i = 0; i < noteList.size(); i++) {
            Notes note = noteList.get(i);
            if (note.getTitle().compareTo(notes.getTitle()) == 0) {
                try {
                    fileManager.serializeDataOut(note);         // updates note object to disk
                    statusLabel.setText("Updating note: " + note.getTitle());
                } catch (IOException e) {
                    System.out.println(e);
                }
                break;
            }
        }
    }

    public void addClickAction() {
        id = random.nextInt(99);
        title = titleTextField.getText();
        content = "";
        color = noteColorPicker.getValue();

        Notes note = new Notes(id, title, content, color);
        new NotesUI(note, this);                        // displays note object in gui
        noteList.add(note);
        observableNoteList.add(note.getTitle());
        try {
            fileManager.serializeDataOut(note);         // saves note object to disk
        } catch (IOException e) {
            System.out.println(e);
        }
        statusLabel.setText("Creating note #" + noteList.size() + ": " + title);

        titleTextField.clear();
        newNoteButton.setDisable(true);
    }

    public NewNoteButton getNewNoteButton() {
        return newNoteButton;
    }

    public void setNewNoteButton(NewNoteButton newNoteButton) {
        this.newNoteButton = newNoteButton;
    }

    public TitleTextField getTextField() {
        return titleTextField;
    }

    public void setTextField(TitleTextField titleTextField) {
        this.titleTextField = titleTextField;
    }

    public void textValueChanged() {
        if (titleTextField.getText().length() > 0) {
            newNoteButton.setDisable(false);
        } else {
            newNoteButton.setDisable(true);
        }
    }

    public NoteListView getNoteListView() {
        return noteListView;
    }

    public void setNoteListView(NoteListView noteListView) {
        this.noteListView = noteListView;
    }

    public NoteColorPicker getNoteColorPicker() {
        return noteColorPicker;
    }

    public void setNoteColorPicker(NoteColorPicker noteColorPicker) {
        this.noteColorPicker = noteColorPicker;
    }

    public StatusLabel getStatusLabel() {
        return statusLabel;
    }

    public void setStatusLabel(StatusLabel statusLabel) {
        this.statusLabel = statusLabel;
    }

}
