package BussinessLayer;

public class JobRequest {

    private int id;
    private String title;
    private String content;
    private String date;

    public JobRequest(String title, String content, String date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public JobRequest(int id, String title, String content, String date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public JobRequest() { }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }
}
