package quizSystem;
import java.util.*;
import java.io.*;
public class ExistingSubjectList implements Serializable {
    private int courseId;
    private ArrayList<String> courseList;
    private String course;
    private int totalQuestions;
    private char job;
    public void setCourseId(int courseId)
    {
        this.courseId = courseId;
    }
    public void setCourseList(ArrayList<String> courseList)
    {
        this.courseList = courseList;
    }
    public void setTotalQuestions(int totalQuestions)
    {
        this.totalQuestions = totalQuestions;
    }
    public void setCourse(String course)
    {
        this.course = course;
    }
    public void setJob(char job)
    {
        this.job = job;
    }
    public int getCourseId()
    {
        return courseId;
    }
    public ArrayList<String> getCourseList()
    {
        return courseList;
    }
    public int getTotalQuestions()
    {
        return totalQuestions;
    }
    public String getCourse()
    {
        return course;
    }

    public char getJob() {
        return job;
    }
}
