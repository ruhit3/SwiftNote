package AdapterPattern;

import Swiftnote.Notes;
import javafx.scene.paint.Color;

public class SerializeNotesAdapter {

    SerializableNotes serializableNotes;

    public SerializableNotes getNotes(Notes notes) {
        serializableNotes = new SerializableNotes(notes.getId(),
            notes.getTitle(),
            notes.getContent(),
            makeColor(notes.getColor()));
        return serializableNotes;
    }

    private String makeColor(Color color) {
        String colorCode = color.toString();
        colorCode = colorCode.substring(2, 8);
        colorCode = "#".concat(colorCode);
        return colorCode;
    }

}
