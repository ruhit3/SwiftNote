package Swiftnote;

import SingletonPattern.ManagerUI;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.stage.Stage;

public class Swiftnote extends Application {

    @Override
    public void start(Stage stage) throws IOException, FileNotFoundException, ClassNotFoundException {
        FileManager fileManager = new FileManager();
        ArrayList<Notes> noteList = fileManager.fetchNotes();

        ManagerUI noteManager = ManagerUI.getInstance();
        noteManager.sendNotes(noteList);
        noteManager.createManagerUI(stage);
    }

    public static void main(String args[]) {
        launch(args);
    }
}
