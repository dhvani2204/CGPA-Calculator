import java.util.List;

public class Semester {
    private int semesterNumber;
    private List<Subject> subjects;

    public Semester(int semesterNumber, List<Subject> subjects) {
        this.semesterNumber = semesterNumber;
        this.subjects = subjects;
    }

    public int getSemesterNumber() {
        return semesterNumber;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}
