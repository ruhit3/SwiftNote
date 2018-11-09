package AdapterPattern;

import Swiftnote.Notes;
import javafx.scene.paint.Color;

public class DeserializeNotesAdapter {

    Notes notes;

    public Notes getNotes(SerializableNotes serializableNotes) {
        notes = new Notes(serializableNotes.getId(),
            serializableNotes.getTitle(),
            serializableNotes.getContent(),
            makeColor(serializableNotes.getColor()));
        return notes;
    }

    private Color makeColor(String color) {
        return Color.web(color);
    }

}
