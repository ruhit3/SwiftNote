package Swiftnote;

import AdapterPattern.DeserializeNotesAdapter;
import AdapterPattern.SerializableNotes;
import AdapterPattern.SerializeNotesAdapter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileManager {

    public ArrayList<Notes> fetchNotes() {
        ArrayList<Notes> noteList = new ArrayList<Notes>();
        final File folder = new File("notes");
        for (File file : folder.listFiles()) {
            try {
                noteList.add(serializeDataIn(file.getName()));
            } catch (IOException e) {
                System.out.println(e);
            } catch (ClassNotFoundException e) {
                System.out.println(e);
            }
        }
        return noteList;
    }

    public static void serializeDataOut(Notes nt) throws IOException {
        SerializeNotesAdapter serializableNotesAdapter = new SerializeNotesAdapter();
        String fileName = "notes//".concat(nt.getTitle().concat(".bin"));
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        // System.out.println(fileName);
        oos.writeObject(serializableNotesAdapter.getNotes(nt));
        oos.close();
    }

    public static Notes serializeDataIn(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {
        DeserializeNotesAdapter deserializeNotesAdapter = new DeserializeNotesAdapter();
        FileInputStream fin = new FileInputStream("notes//".concat(fileName));
        ObjectInputStream ois = new ObjectInputStream(fin);
        SerializableNotes nt = (SerializableNotes) ois.readObject();
        ois.close();
        return deserializeNotesAdapter.getNotes(nt);
    }
}
