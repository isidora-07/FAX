package Models;

public class UserSubject {
    private String email;
    private int subjectId;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public UserSubject(String email, int subjectId) {
        super();
        this.email = email;
        this.subjectId = subjectId;
    }

    public UserSubject() {
        super();
        // TODO Auto-generated constructor stub
    }
}
