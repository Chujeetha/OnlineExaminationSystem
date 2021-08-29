package quizSystem;
import java.io.Serializable;
public class StudentAnswer implements Serializable {
    private int queId;
    private int courseId;
    private String answer;
    private String email;
    private char job;
    private int mark;
    private int totalQuestion;
    public void setQueId(int queId)
    {
        this.queId = queId;
    }
    public void setcourseId(int courseId)
    {
        this.courseId = courseId;
    }
    public void setAnswer(String answer)
    {
        this.answer = answer;
    }
    public  void setEmail(String email)
    {
        this.email = email;
    }
    public void setJob(char job)
    {
        this.job = job;
    }
    public void setMark(int mark)
    {
        this.mark = mark;
    }
    public void setTotalQuestion(int totalQuestion)
    {
        this.totalQuestion = totalQuestion;
    }
    public int getQueId()
    {
        return queId;
    }
    public int getCourseId()
    {
        return courseId;
    }
    public String getAnswer()
    {
        return answer;
    }
    public String getEmail()
    {
        return email;
    }
    public char getJob()
    {
        return job;
    }
    public int getMark()
    {
        return mark;
    }
    public int getTotalQuestion()
    {
        return totalQuestion;
    }
}
