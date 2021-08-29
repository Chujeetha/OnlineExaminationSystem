package quizSystem;
import java.io.*;
public class StudentDetail implements Serializable{
    private String name_student;
    private String regNo_student;
    private String gender_student;
    private String email_student;
    private String password_student;
    private long mobile_student;
    private char job_student;
    private Boolean checkStudentLogin;

    public void setEmail_student(String email_student) {
        this.email_student = email_student;
    }
    public void setRegNo_student(String regNo_student) {
        this.regNo_student = regNo_student;
    }
    public void setName_student(String name_student) {this.name_student = name_student;}

    public void setPassword_student(String password_student) {
        this.password_student = password_student;
    }
    public void setMobile_student(long mobile_student) {
        this.mobile_student = mobile_student;
    }
    public void setGender_student(String gender_student) {
        this.gender_student = gender_student;
    }
    public void setJob_student(char job_student){this.job_student = job_student;}
    public void setCheckStudentLogin(boolean checkStudentLogin){this.checkStudentLogin = checkStudentLogin;}

    public String getEmail_student() {
        return email_student;
    }
    public String getRegNo_student() {
        return regNo_student;
    }
    public String getName_student() {
        return name_student;
    }

    public String getPassword_student() {
        return password_student;
    }
    public long getMobile_student() {
        return Long.highestOneBit(mobile_student);
    }
    public String getGender_student() {
        return gender_student;
    }
    public char getJob_student(){return job_student;}
    public Boolean getCheckStudentLogin()
    {
        return checkStudentLogin;
    }
}
