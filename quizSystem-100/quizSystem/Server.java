package quizSystem;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
public class Server extends Thread{
    Socket socket;
    ObjectInputStream oin;
    ObjectOutputStream oout;
    Object object;
    FacultyDetail facultyDetail;
    DataBase dBase;
    ExistingSubjectList existingSubjectList;
    FacultyQuestion facultyQuestion;
    StudentDetail studentDetail;
    StudentAnswer studentAnswer;
    public Server(Socket socket)
    {
        this.socket = socket;
        makeServer();
    }
    public void makeServer()
    {
        try {
            oin = new ObjectInputStream(socket.getInputStream());
            oout = new ObjectOutputStream(socket.getOutputStream());
        }
        catch (Exception e) {
            System.out.println("Server - makeServer : "+e);
        }
    }
    public void run()
    {
        System.out.println("run -sucess");
        try {
             this.object = oin.readObject();
             if(object instanceof FacultyDetail)
             {
                 System.out.println("run-fac");
                 facultyDetail = (FacultyDetail)object;
                 accessFacultyDetail(facultyDetail);
             }
             else if(object instanceof ExistingSubjectList)
             {
                 System.out.println("instance of ExistingSubjectList");
                 this.existingSubjectList = (ExistingSubjectList)object;
                 accessCourseDetail(this.existingSubjectList);
             }
             else if(object instanceof FacultyQuestion)
             {
                 System.out.println("instance of FacultyQuestion");
                 this.facultyQuestion = (FacultyQuestion)object;
                 accessQuestionDetail(this.facultyQuestion);
             }
             else if(object instanceof StudentDetail)
             {
                 System.out.println("instance of studentDetail");
                 this.studentDetail = (StudentDetail)object;
                 accessStudentDetail(this.studentDetail);
             }
             else if(object instanceof StudentAnswer)
             {
                 System.out.println("instance of studentAnswer");
                 this.studentAnswer = (StudentAnswer) object;
                 accessStudentAnswer(this.studentAnswer);
             }
        }
        catch (Exception e) {
           System.out.println("Server - run : "+e);
        }
        finally {
            try {
                //this.socket.close();
            }
            catch (Exception e)
            {
                System.out.println("run - finally : "+e);
            }
        }
    }
    public void accessFacultyDetail(FacultyDetail facultyDetail)
    {
        System.out.println("access");
        this.facultyDetail = facultyDetail;
        Character job = facultyDetail.getJob_faculty();
        dBase = new DataBase();
        switch (job)
        {
            case 'R' :
            {
                System.out.println("R");
                dBase.FacultyDetailTable(facultyDetail);
                break;
            }
            case 'L' :
            {
                dBase.FacultyDetailTable(facultyDetail);
                try {
                    oout.writeObject(facultyDetail);
                    System.out.println("return");
                    //oout.writeObject(facultyDetail);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }

            default:
                throw new IllegalStateException("Unexpected value: " + job);
        }
    }
    public void accessCourseDetail(ExistingSubjectList existingSubjectList)
    {
        this.existingSubjectList = existingSubjectList;
        this.dBase = new DataBase();
        switch (existingSubjectList.getJob())
        {
            case 'A' :
            {
                System.out.println("Server - accessCourseDetail : success");
                dBase.courseDetailTable(existingSubjectList);
                break;
            }
            case 'U' :
            {
                dBase.courseDetailTable(this.existingSubjectList);
                try {
                    System.out.println("SErver - couresLIst - "+this.existingSubjectList.getCourseList());
                    oout.writeObject(this.existingSubjectList);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case 'S' :
            {
                this.facultyQuestion = dBase.getCourseId(this.existingSubjectList);
                try
                {
                   this.facultyQuestion = dBase.selectQuestion(this.facultyQuestion);
                   oout.writeObject(this.facultyQuestion);
                }
                catch (Exception e)
                {
                    System.out.println("Server - accessCourseDetail : "+e);
                }
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " );
        }

    }
    public void accessQuestionDetail(FacultyQuestion facultyQuestion)
    {
        this.facultyQuestion = facultyQuestion;
        this.dBase = new DataBase();
        switch (facultyQuestion.getJob())
        {
            case 'I' :
            {
                System.out.println("Server - accessQuestionDetail : success");
                dBase.QuestionTable(this.facultyQuestion);
                break;
            }
            case 'U' :
            {
                System.out.println("Server - accessQuestion - case : u - success");
                dBase.QuestionTable(this.facultyQuestion);
                break;
            }

            default:
                throw new IllegalStateException("Unexpected value: " );
        }

    }
    public void accessStudentDetail(StudentDetail studentDetail)
    {
        System.out.println("access Student Detail");
        this.studentDetail = studentDetail;
        Character job = studentDetail.getJob_student();
        dBase = new DataBase();
        switch (job)
        {
            case 'R' :
            {
                System.out.println("R");
                dBase.StudentDetailTable(this.studentDetail);
                break;
            }
            case 'L' :
            {
                dBase.StudentDetailTable(this.studentDetail);
                try {
                    oout.writeObject(studentDetail);
                    System.out.println("return");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + job);
        }
    }
    public void accessStudentAnswer(StudentAnswer studentAnswer)
    {
        System.out.println("access Student Answer");
        this.studentAnswer = studentAnswer;
        Character job = studentAnswer.getJob();
        this.dBase = new DataBase();
        switch (job)
        {
            case 'I' :
            {
                System.out.println("I - Student answer");
                this.dBase.studentAnswerTable(this.studentAnswer);
                break;
            }
            case 'R' :
            {
                System.out.println("R - Student answer");
                this.dBase.studentAnswerTable(this.studentAnswer);
                try {
                    oout.writeObject(studentAnswer);
                    System.out.println("return");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + job);
        }

    }

}
