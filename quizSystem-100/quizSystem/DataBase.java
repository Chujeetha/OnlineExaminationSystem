package quizSystem;
import java.sql.*;
import java.util.ArrayList;

public class DataBase {
    Connection con;
    FacultyDetail facultyDetail;
    ExistingSubjectList existingSubjectList;
    FacultyQuestion facultyQuestion;
    StudentDetail studentDetail;
    StudentAnswer studentAnswer;
    public DataBase()
    {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","aaju","java");
            con.setAutoCommit(true);


        } catch (Exception e) {
            System.out.println("Database - default constructor : "+e);
        }
    }
    public void FacultyDetailTable(FacultyDetail facultyDetail)
    {
        try
        {
            this.facultyDetail = facultyDetail;
            Statement st = con.createStatement();
            //DatabaseMetaData a = con.getMetaData();
            String query = "select table_name from all_tables where table_name = 'FACULTYDETAIL'";
            System.out.println("database - create");
            ResultSet res = st.executeQuery(query);
            Boolean avaliable = res.next();
            if(!avaliable)
            {
                System.out.println("u;p");
                query = "create table FacultyDetail(" +
                        "Faculty_Email varchar2(35) primary key NOT NULL, " +
                        "Faculty_Name varchar2(30)," +
                        "Faculty_Mobile number(10)," +
                        "Faculty_password varchar2(30)," +
                        "Faculty_Gender varchar2(10))";
                st.executeUpdate(query);
                System.out.println("res-create");
                if(facultyDetail.getJob_faculty()=='R')
                {
                    System.out.println("R-facutyDetail");
                    insertFacultyDetailsTable(facultyDetail);
                }
            }
            if(avaliable)
            {
                if(this.facultyDetail.getJob_faculty()=='R')
                {
                    insertFacultyDetailsTable(this.facultyDetail);
                }
                if(this.facultyDetail.getJob_faculty()=='L')
                {
                    checkFacultyLogin(this.facultyDetail);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Dtabase - facultyDetailTable : "+e);
        }
    }
    public void insertFacultyDetailsTable(FacultyDetail facultyDetail)
    {
        try {
            System.out.println("insert fun");
            this.facultyDetail = facultyDetail;
            PreparedStatement pst = con.prepareStatement("insert into FacultyDetail values(?,?,?,?,?)");
            //String query = "insert into FacultyDetail values(?,?,?,?,?)";
            pst.setString(1,facultyDetail.getEmail_faculty());
            //System.out.println("insert - > "+facultyDetail.getEmail_faculty());
            pst.setString(2,facultyDetail.getName_faculty());
            pst.setLong(3,facultyDetail.getMobile_faculty());
            pst.setString(4,facultyDetail.getPassword_faculty());
            pst.setString(5,facultyDetail.getGender_faculty());
            int res = pst.executeUpdate();
            con.setAutoCommit(true);
            //System.out.println("Value-inserted");
        }
        catch (Exception e)
        {
            System.out.println("DataBase - insertFacultyDetailsTable : "+e);
        }
    }
    public void checkFacultyLogin(FacultyDetail facultyDetail)
    {
        this.facultyDetail = facultyDetail;
        try
        {
            System.out.println("checkLogin-faculty");
            String email = facultyDetail.getEmail_faculty();
            String pwd = facultyDetail.getPassword_faculty();
            Statement st = con.createStatement();
            String query = "select Faculty_password from FacultyDetail where Faculty_Email = '"+email+"'";
            ResultSet res = st.executeQuery(query);
            if(res.next())
            {
                String crtPass = res.getString(1) ;
                if(pwd.equals(crtPass))
                {
                    //this.facultyDetail = new FacultyDetail();
                    this.facultyDetail.setCheckFacultyLogin(true);
                }
                else {
                    this.facultyDetail.setCheckFacultyLogin(false);
                }
            }
            else {
                this.facultyDetail.setCheckFacultyLogin(false);
            }
        }
        catch (Exception e)
        {
            System.out.println("Database - checkFacultyLogin : "+e);
        }
    }
    public void courseDetailTable(ExistingSubjectList existingSubjectList)
    {
        try {
            this.existingSubjectList = existingSubjectList;
            Statement st = con.createStatement();
            String query = "select table_name from all_tables where table_name = 'COURSEDETAIL'";
            System.out.println("database - create - courseDetail");
            ResultSet res = st.executeQuery(query);
            Boolean avaliable = res.next();
            if(!avaliable)
            {
                query = "create table CourseDetail(" +
                        "courseID number(10) primary key not null," +
                        "courseName varchar2(40)," +
                        "totolQuestion number(10))";
                st.executeUpdate(query);
                if(existingSubjectList.getJob()=='A')
                {
                    insertCourseDetailTable(existingSubjectList);
                }
                if(existingSubjectList.getJob()=='U')
                {
                    countCourseDetails(this.existingSubjectList);
                }
            }
            if(avaliable)
            {
                if(existingSubjectList.getJob()=='A')
                {
                    insertCourseDetailTable(existingSubjectList);
                }
                if(existingSubjectList.getJob()=='U')
                {
                    countCourseDetails(this.existingSubjectList);
                }
            }

        }
        catch (Exception e)
        {
            System.out.println("Database - courseDetailTable : "+e);
        }

    }
    public void insertCourseDetailTable(ExistingSubjectList existingSubjectList)
    {
        this.existingSubjectList = existingSubjectList;
        try {
            System.out.println("database -insertCourseDetailTable : success ");
            PreparedStatement pst = con.prepareStatement("insert into CourseDetail values(?,?,?)");
            int count = countCourseDetails(this.existingSubjectList);
            pst.setInt(1,count);
            System.out.println(count);
            pst.setString(2,existingSubjectList.getCourse());
            pst.setInt(3,existingSubjectList.getTotalQuestions());
            int res = pst.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println("database - insertCourseDetailTable : "+e);
        }
    }
    public int countCourseDetails(ExistingSubjectList existingSubjectList)
    {
        int count = 0;
        this.existingSubjectList = existingSubjectList;
        ArrayList<String> courseList = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            String query = "select * from CourseDetail";
            ResultSet res = st.executeQuery(query);
            while (res.next())
            {
                courseList.add(res.getString(2));
                count = res.getInt(1);
            }
            this.existingSubjectList.setCourseList(courseList);
            System.out.println("count = "+count);
            System.out.println(courseList);

            return count+1;
        }
        catch (Exception e) {
            System.out.println("database - countCourseDetails : "+e);
        }
        return 0;

    }
    public FacultyQuestion getCourseId(ExistingSubjectList existingSubjectList)
    {
        try
        {
            this.existingSubjectList = existingSubjectList;
            Statement st = con.createStatement();
            //DatabaseMetaData a = con.getMetaData();
            String query = "select courseId from courseDetail where courseName = '"+this.existingSubjectList.getCourse()+"'";
            System.out.println("database - create");
            ResultSet res = st.executeQuery(query);
            Boolean avaliable = res.next();
            if(avaliable)
            {
                this.facultyQuestion = new FacultyQuestion();
                this.facultyQuestion.setCourseId(res.getInt(1));
                System.out.println(this.facultyQuestion.getCourseId());
               // selectQuestion(this.facultyQuestion);
            }
                return this.facultyQuestion;
        }
        catch (Exception e)
        {
            System.out.println("DataBase - getCourseId : "+e);
            return null;
        }
    }
    public void QuestionTable(FacultyQuestion facultyQuestion)
    {
        try
        {
            this.facultyQuestion = facultyQuestion;
            Statement st = con.createStatement();
            String query = "select table_name from all_tables where table_name = 'QUESTION'";
            System.out.println("database - create");
            ResultSet res = st.executeQuery(query);
            Boolean avaliable = res.next();
            if(!avaliable)
            {
                query = "create table question(" +
                        "QueId number(10), " +
                        "courseID number(10)," +
                        "questionDescription varchar2(200)," +
                        "option1 varchar2(100)," +
                        "option2 varchar2(100)," +
                        "option3 varchar2(100)," +
                        "option4 varchar2(100)," +
                        "crtAns varchar2(100))";
                st.executeUpdate(query);
                System.out.println("res-create");
                if(facultyQuestion.getJob()=='I')
                {
                    insertQuestionTable(this.facultyQuestion);
                }
            }
            if(avaliable)
            {
                if(facultyQuestion.getJob()=='I')
                {
                    insertQuestionTable(this.facultyQuestion);
                }
                if(facultyQuestion.getJob()=='U')
                {
                    updateQuestion(this.facultyQuestion);
                }
            }


        }
        catch (Exception e)
        {
            System.out.println("Dtabase - facultyDetailTable : "+e);
        }
    }
    public void insertQuestionTable(FacultyQuestion facultyQuestion)
    {
        this.facultyQuestion = facultyQuestion;
        try {
            System.out.println("database -insertQuestionTable : success ");
            PreparedStatement pst = con.prepareStatement("insert into  question values(?,?,?,?,?,?,?,?)");
            pst.setInt(1,facultyQuestion.getQuestionId());
            pst.setInt(2,countCourseDetails(new ExistingSubjectList())-1);
            System.out.println(countCourseDetails(new ExistingSubjectList()));
            pst.setString(3,facultyQuestion.getQuestion());
            pst.setString(4,facultyQuestion.getOption1());
            pst.setString(5,facultyQuestion.getOption2());
            pst.setString(6,facultyQuestion.getOption3());
            pst.setString(7,facultyQuestion.getOption4());
            pst.setString(8,facultyQuestion.getAnswer());
            int res = pst.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println("database - insertQuestionTable : "+e);
        }
    }
    public void updateQuestion(FacultyQuestion facultyQuestion)
    {
        this.facultyQuestion = facultyQuestion;
        try
        {
            int queId = this.facultyQuestion.getQuestionId();
            int courseId = this.facultyQuestion.getCourseId();
            String que = this.facultyQuestion.getQuestion();
            String opt1 = this.facultyQuestion.getOption1();
            String opt2 = this.facultyQuestion.getOption2();
            String opt3 = this.facultyQuestion.getOption3();
            String opt4 = this.facultyQuestion.getOption4();
            String ans = this.facultyQuestion.getAnswer();
            System.out.println("database - updateQuestion : success ");
            PreparedStatement pst = con.prepareStatement("update question set questionDescription = ?," +
                    "option1 = ?, option2 = ?,option3 = ?,option4 = ?,crtAns = ? where QueId = ? and " +
                    "courseID = ?");
            pst.setString(1,que);
            pst.setString(2,opt1);
            pst.setString(3,opt2);
            pst.setString(4,opt3);
            pst.setString(5,opt4);
            pst.setString(6,ans);
            pst.setInt(7,queId);
            pst.setInt(8,courseId);
            int res = pst.executeUpdate();
            System.out.println("questionTable - update - success - : "+res);
        }
        catch (Exception e)
        {
            System.out.println("DataBase - updateQuestion : "+e);
        }
    }
    public FacultyQuestion selectQuestion(FacultyQuestion facultyQuestion)
    {
        //this.existingSubjectList = courseList();
        this.facultyQuestion = facultyQuestion;
        ArrayList<Integer> queId = new ArrayList<>();
        ArrayList<Integer> courseId = new ArrayList<>();
        ArrayList<String > que = new ArrayList<>();
        ArrayList<String> opt1 = new ArrayList<>();
        ArrayList<String> opt2 = new ArrayList<>();
        ArrayList<String> opt3 = new ArrayList<>();
        ArrayList<String > opt4 = new ArrayList<>();
        ArrayList<String> ans = new ArrayList<>();
        try {
            //this.existingSubjectList = existingSubjectList;
            Statement st = con.createStatement();
            //DatabaseMetaData a = con.getMetaData();
            String query = "select * from question where courseId = '"+this.facultyQuestion.getCourseId()+"'";
            System.out.println("database - create");
            ResultSet res = st.executeQuery(query);
           while(res.next())
            {
                queId.add(res.getInt(1));
                courseId.add(res.getInt(2));
                que.add(res.getString(3));
                opt1.add(res.getString(4));
                opt2.add(res.getString(5));
                opt3.add(res.getString(6));
                opt4.add(res.getString(7));
                ans.add(res.getString(8));
            }
           this.facultyQuestion.setQuestionIdList(queId);
           this.facultyQuestion.setCourseIdList(courseId);
           this.facultyQuestion.setQuestionList(que);
           this.facultyQuestion.setOption1List(opt1);
           this.facultyQuestion.setOption2List(opt2);
           this.facultyQuestion.setOption3List(opt3);
           this.facultyQuestion.setOption4List(opt4);
           this.facultyQuestion.setAnswerList(ans);
            System.out.println("DataBase - que list : " +this.facultyQuestion.getQuestionList());
            return this.facultyQuestion;
        }
        catch (Exception e)
        {
            System.out.println("Database - selectQuestion : "+e);
            return null;
        }
    }
    public void StudentDetailTable(StudentDetail studentDetail)
    {
        try
        {
            this.studentDetail = studentDetail;
            Statement st = con.createStatement();
            String query = "select table_name from all_tables where table_name = 'STUDENTDETAIL'";
            System.out.println("database - create");
            ResultSet res = st.executeQuery(query);
            Boolean avaliable = res.next();
            if(!avaliable)
            {
                System.out.println("u;p");
                query = "create table StudentDetail(" +
                        "student_Email varchar2(35) primary key NOT NULL, " +
                        "student_RegNo varchar2(20) UNIQUE," +
                        "student_Name varchar2(30)," +
                        "student_Mobile number(10)," +
                        "student_password varchar2(30)," +
                        "student_Gender varchar2(10))";
                st.executeUpdate(query);
                System.out.println("res-create");
                if(this.studentDetail.getJob_student()=='R')
                {
                    System.out.println("R-facutyDetail");
                    insertStudentDetailsTable(this.studentDetail);
                }
            }
            if(avaliable)
            {
                if(this.studentDetail.getJob_student()=='R')
                {
                    System.out.println("R-facutyDetail");
                    insertStudentDetailsTable(this.studentDetail);
                }
                if(this.studentDetail.getJob_student()=='L')
                {
                    checkStudentLogin(this.studentDetail);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Dtabase - studentDetailTable : "+e);
        }
    }
    public void insertStudentDetailsTable(StudentDetail studentDetail)
    {
        try {
            System.out.println("insert fun");
            this.studentDetail = studentDetail;
            PreparedStatement pst = con.prepareStatement("insert into StudentDetail values(?,?,?,?,?,?)");
            pst.setString(1,studentDetail.getEmail_student());
            pst.setString(2,studentDetail.getName_student());
            pst.setString(3,studentDetail.getRegNo_student());
            pst.setLong(4,studentDetail.getMobile_student());
            pst.setString(5,studentDetail.getPassword_student());
            pst.setString(6,studentDetail.getGender_student());
            int res = pst.executeUpdate();
            con.setAutoCommit(true);
        }
        catch (Exception e)
        {
            System.out.println("DataBase - insertstudentDetailsTable : "+e);
        }
    }
    public void checkStudentLogin(StudentDetail studentDetail)
    {
        this.studentDetail = studentDetail;
        try
        {
            System.out.println("checkLogin-Student");
            String email = studentDetail.getEmail_student();
            String pwd = studentDetail.getPassword_student();
            Statement st = con.createStatement();
            String query = "select student_password from StudentDetail where student_Email = '"+email+"'";
            ResultSet res = st.executeQuery(query);
            if(res.next())
            {
                String crtPass = res.getString(1) ;
                if(pwd.equals(crtPass))
                {
                    //this.facultyDetail = new FacultyDetail();
                    this.studentDetail.setCheckStudentLogin(true);
                }
                else {
                    this.studentDetail.setCheckStudentLogin(false);
                }
            }
            else {
                this.studentDetail.setCheckStudentLogin(false);
            }
        }
        catch (Exception e)
        {
            System.out.println("Database - checkStudentLogin : "+e);
        }
    }
   public void studentAnswerTable(StudentAnswer studentAnswer)
    {
        try
        {
            this.studentAnswer = studentAnswer;
            Statement st = con.createStatement();
            String query = "select table_name from all_tables where table_name = 'STUDENTANSWER'";
            System.out.println("database - create");
            ResultSet res = st.executeQuery(query);
            Boolean avaliable = res.next();
            if(!avaliable)
            {
                System.out.println("u;p");
                query = "create table StudentAnswer(" +
                        "queID number(10), " +
                        "courseID number(10)," +
                        "studentEmail varchar2(30)," +
                        "answer varchar2(50)," +
                        "correctAnswer varchar2(50))";
                st.executeUpdate(query);
                System.out.println("res-create");
                if(this.studentAnswer.getJob()=='I')
                {
                    //System.out.println("R-facutyDetail");
                    insertStudentAnswer(this.studentAnswer);
                }
            }
            if(avaliable)
            {
                if(this.studentAnswer.getJob()=='I')
                {
                    insertStudentAnswer(this.studentAnswer);
                }
                if(this.studentAnswer.getJob()=='R')
                {
                    studentResultCalculation(this.studentAnswer);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Dtabase - facultyDetailTable : "+e);
        }
    }
    public String getCrtAnswer(int queID,int courseID)
    {
        //this.facultyQuestion = new FacultyQuestion();
        try
        {
            Statement st = con.createStatement();
            String query = "select crtAns from question where courseID = '"+courseID+"' and QueId = '"+queID+"'";
            ResultSet res = st.executeQuery(query);
            if(res.next())
            {
                String crtAns = res.getString(1) ;
                return crtAns;
            }
            else {
                return null;
            }
        }
        catch (Exception e)
        {
            System.out.println("Database - checkStudentLogin : "+e);
            return null;
        }
    }
    public void insertStudentAnswer(StudentAnswer studentAnswer)
    {
        try {
            System.out.println("insert fun - insert Student Answer");
            this.studentAnswer = studentAnswer;
            PreparedStatement pst = con.prepareStatement("insert into StudentAnswer values(?,?,?,?,?)");
            pst.setInt(1,studentAnswer.getQueId());
            pst.setInt(2,studentAnswer.getCourseId());
            pst.setString(3,studentAnswer.getEmail());
            pst.setString(4,studentAnswer.getAnswer());
            pst.setString(5,getCrtAnswer(studentAnswer.getQueId(),studentAnswer.getCourseId()));
            int res = pst.executeUpdate();
            System.out.println("Success - "+res);
            con.setAutoCommit(true);
        }
        catch (Exception e)
        {
            System.out.println("DataBase - insertstudentDetailsTable : "+e);
        }
    }
    public void studentResultCalculation(StudentAnswer studentAnswer)
    {
        this.studentAnswer = studentAnswer;
        int mark = 0;
        int totalQuestion = 0;
        try
        {
            Statement st = con.createStatement();
            String query = "select answer,correctAnswer from StudentAnswer where studentEmail = " +
                    "'"+this.studentAnswer.getEmail()+"' and courseID = '"+this.studentAnswer.getCourseId()+"'";
            ResultSet res = st.executeQuery(query);
            while(res.next())
            {
               totalQuestion += 1;
               String answer = res.getString(1);
               String crtAns = res.getString(2);
                System.out.println("Answer - "+answer);
                System.out.println("crtAns - "+crtAns);
               if(answer.equals(crtAns))
               {
                   mark += 1;
               }
            }
            System.out.println(mark);
            System.out.println(totalQuestion);
            this.studentAnswer.setMark(mark);
            this.studentAnswer.setTotalQuestion(totalQuestion);
        }
        catch (Exception e)
        {
            System.out.println("Database -  : studentResultCalculation"+e);
        }
    }
}
