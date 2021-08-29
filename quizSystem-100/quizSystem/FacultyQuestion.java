package quizSystem;
import java.io.*;
import java.util.ArrayList;

public class FacultyQuestion implements Serializable {
    private int questionId;
    private int courseId;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String answer;
    private char job;
    private ArrayList<Integer> questionIdList;
    private ArrayList<Integer> courseIdList;
    private ArrayList<String> questionList;
    private ArrayList<String> option1List;
    private ArrayList<String> option2List;
    private ArrayList<String> option3List;
    private ArrayList<String> option4List;
    private ArrayList<String> answerList;

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
    public void  setCourseId(int courseId)
    {
        this.courseId = courseId;
    }
    public void setQuestion(String question)
    {
        this.question = question;
    }
    public void setOption1(String  option1)
    {
        this.option1 = option1;
    }
    public void setOption2(String option2)
    {
        this.option2 = option2;
    }
    public void setOption3(String option3)
    {
        this.option3 = option3;
    }
    public void setOption4(String option4)
    {
        this.option4 = option4;
    }
    public void setAnswer(String answer)
    {
        this.answer = answer;
    }
    public void setQuestionIdList(ArrayList<Integer> questionIdList) {
        this.questionIdList = questionIdList;
    }
    public void setCourseIdList(ArrayList<Integer> courseIdList)
    {
        this.courseIdList = courseIdList;
    }
    public void setQuestionList(ArrayList<String> questionList)
    {
        this.questionList = questionList;
    }
    public void setOption1List(ArrayList<String> option1List)
    {
        this.option1List = option1List;
    }
    public void setOption2List(ArrayList<String> option2List)
    {
        this.option2List = option2List;
    }
    public void setOption3List(ArrayList<String> option3List)
    {
        this.option3List = option3List;
    }
    public void setOption4List(ArrayList<String> option4List)
    {
        this.option4List = option4List;
    }
    public void setAnswerList(ArrayList<String> answerList)
    {
        this.answerList = answerList;
    }
    public void setJob(char job)
    {
        this.job = job;
    }
    public int getQuestionId()
    {
        return questionId;
    }
    public int getCourseId()
    {
        return courseId;
    }
    public String getQuestion()
    {
        return question;
    }
    public String getOption1()
    {
        return option1;
    }
    public String getOption2()
    {
        return option2;
    }
    public String getOption3()
    {
        return option3;
    }
    public String getOption4()
    {
        return option4;
    }
    public String getAnswer()
    {
        return answer;
    }
    public char getJob()
    {
        return job;
    }
    public ArrayList<Integer> getQuestionIdList()
    {
        return questionIdList;
    }
    public ArrayList<Integer> getCourseIdList()
    {
        return courseIdList;
    }
    public ArrayList<String > getQuestionList()
    {
        return questionList;
    }
    public ArrayList<String> getOption1List()
    {
        return option1List;
    }
    public ArrayList<String> getOption2List()
    {
        return option2List;
    }
    public ArrayList<String> getOption3List()
    {
        return option3List;
    }
    public ArrayList<String > getOption4List()
    {
        return option4List;
    }
    public ArrayList<String> getAnswerList()
    {
        return answerList;
    }
}
