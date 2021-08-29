package quizSystem;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
public class Client {

    Client client;
    Socket socket;
    ObjectInputStream oin;
    ObjectOutputStream oout;
    StudentDetail studentDetail;
    FacultyDetail facultyDetail;
    ExistingSubjectList existingSubjectList;
    FacultyQuestion facultyQuestion;
    StudentAnswer studentAnswer;
    public Client(String url,int port)
    {
        try {
            socket = new Socket(url, port);
            oout = new ObjectOutputStream(socket.getOutputStream());
            oin = new ObjectInputStream(socket.getInputStream());
        }
        catch(Exception e) {
            System.out.println("e");
        }
    }

    public Boolean sendStudentDetail(StudentDetail studentDetail) {
        try {
            System.out.println("Client begin");
            //oout = new ObjectOutputStream(socket.getOutputStream());
            oout.writeObject(studentDetail);
            return true;
        }
        catch (Exception e){
            System.out.println("Client : "+e);
            return false;
        }
    }
    public StudentDetail receiveStudentDetail()
    {
        try
        {
            studentDetail = (StudentDetail)oin.readObject();
            return studentDetail;
        }
        catch(Exception e)
        {
            System.out.println("client - receive data : "+e);
            return null;
        }
    }
    public Boolean sendFacultyDetail(FacultyDetail facultyDetail) {
        try {
            System.out.println("Client begin");
            //oout = new ObjectOutputStream(socket.getOutputStream());
            this.facultyDetail = facultyDetail;
            oout.writeObject(this.facultyDetail);
            return  true;
        }
        catch (Exception e){
            System.out.println("Client - sendFacultyDetail : "+e);
            return false;
        }
    }
    public FacultyDetail receiveFacultDetail()
    {
        try
        {
            //oin = new ObjectInputStream(socket.getInputStream());
            facultyDetail = (FacultyDetail)oin.readObject();
            System.out.println("Client - return : "+facultyDetail.getCheckFacultyLogin());
            return facultyDetail;
        }
        catch(Exception e)
        {
            System.out.println("client - receive data : "+e);
            return null;
        }
    }
    public Boolean sendExistingSubjectList(ExistingSubjectList existingSubjectList)
    {
        try
        {
            System.out.println("client - sendExistingSubjectList : call success");
           this.existingSubjectList = existingSubjectList;
           oout.writeObject(this.existingSubjectList);
            System.out.println("write object - client");
           return true;
        }
        catch (Exception e)
        {
            System.out.println("Client - sendExistingSubjectList : "+e);
            return false;
        }
    }
    public ExistingSubjectList receiveExistingSubjectList()
    {
        try
        {
            this.existingSubjectList = (ExistingSubjectList)oin.readObject();
            return existingSubjectList;
        }
        catch (Exception e)
        {
            System.out.println("Client - receiveExistingSubjectList : "+e);
            return null;
        }
    }
    public Boolean sendFacultyQuestion(FacultyQuestion facultyQuestion)
    {
        try
        {
            System.out.println("client - sendFacultyQuestion : call success");
            this.facultyQuestion = facultyQuestion;
            oout.writeObject(this.facultyQuestion);
            System.out.println("write object - client");
            return true;
        }
        catch (Exception e)
        {
            System.out.println("Client - sendFacultyQuestion : "+e);
            return false;
        }
    }
    public FacultyQuestion receiveFacultyQuestion()
    {
        try
        {
            System.out.println("Client - receive faculty - success");
            this.facultyQuestion = (FacultyQuestion) oin.readObject();
            return this.facultyQuestion;
        }
        catch (Exception e)
        {
            System.out.println("Client - receiveFacultyQuestion : "+e);
            return null;
        }
    }
    public Boolean sendStudentAnswer(StudentAnswer studentAnswer)
    {
        try
        {
            System.out.println("client - sendStudentAnswer : call success");
            this.studentAnswer = studentAnswer;
            oout.writeObject(this.studentAnswer);
            System.out.println("write object - client");
            return true;
        }
        catch (Exception e)
        {
            System.out.println("Client - sendStudentAnswer : "+e);
            return false;
        }
    }
    public StudentAnswer receiveStudentAnswer()
    {
        try
        {
            System.out.println("Client - StudentAnswer - success");
            this.studentAnswer = (StudentAnswer) oin.readObject();
            return this.studentAnswer;
        }
        catch (Exception e)
        {
            System.out.println("Client - receiveStudentAnswer : "+e);
            return null;
        }
    }



}
