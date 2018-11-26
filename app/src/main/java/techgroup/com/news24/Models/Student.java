package techgroup.com.news24.Models;

import com.google.gson.annotations.SerializedName;

public class Student {
    @SerializedName("StudentId")
    private String StudentId;

    @SerializedName("StudentName")
    private String StudentName;

    @SerializedName("StudentMarks")
    private String StudentMarks;

    public Student(String studentId, String studentName, String studentMarks) {
        StudentId = studentId;
        StudentName = studentName;
        StudentMarks = studentMarks;
    }

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String studentId) {
        StudentId = studentId;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public String getStudentMarks() {
        return StudentMarks;
    }

    public void setStudentMarks(String studentMarks) {
        StudentMarks = studentMarks;
    }
}
