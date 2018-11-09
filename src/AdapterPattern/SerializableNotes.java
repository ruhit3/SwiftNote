package AdapterPattern;

import java.io.Serializable;

public class SerializableNotes implements Serializable {

    private int id;
    private String title, content, color;

    public SerializableNotes(int id, String title, String content, String color) {
        setId(id);
        setTitle(title);
        setContent(content);
        setColor(color);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
