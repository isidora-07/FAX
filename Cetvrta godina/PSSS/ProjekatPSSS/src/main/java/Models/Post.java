package Models;

public class Post {
    private int id;
    private String content;
    private int subjectId;

    public Post() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Post(int id, String content, int subjectId) {
        super();
        this.id = id;
        this.content = content;
        this.subjectId = subjectId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }
}
