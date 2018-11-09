package Swiftnote;

import javafx.scene.paint.Color;

public class Notes {

    private int id;
    private String title, content;
    private Color color;

    public Notes(int id, String title, String content, Color color) {
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

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

}
