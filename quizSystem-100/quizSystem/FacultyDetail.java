package quizSystem;
import java.io.*;
public class FacultyDetail implements Serializable {
    private String email_faculty;
    private String name_faculty;
    private String password_faculty;
    private long mobile_faculty;
    private String gender_faculty;
    private char job_faculty;
    private Boolean checkFacultyLogin;
    public void setEmail_faculty(String email_faculty) {
        this.email_faculty = email_faculty;
    }
    public void setName_faculty(String name_faculty) {
        this.name_faculty = name_faculty;
    }
    public void setPassword_faculty(String password_faculty) {
        this.password_faculty = password_faculty;
    }
    public void setMobile_faculty(long mobile_faculty){
        this.mobile_faculty = mobile_faculty;
    }
    public void setGender_faculty(String gender_faculty){
        this.gender_faculty = gender_faculty;
    }
    public void setJob_faculty(char job_faculty){this.job_faculty = job_faculty;}
    public void setCheckFacultyLogin(Boolean checkFacultyLogin){this.checkFacultyLogin = checkFacultyLogin;}
    public String getEmail_faculty(){
        return email_faculty;
    }
    public String getName_faculty(){
        return name_faculty;
    }
    public String getPassword_faculty(){
        return password_faculty;
    }
    public long getMobile_faculty(){
        return mobile_faculty;
    }
    public String getGender_faculty(){
        return gender_faculty;
    }
    public char getJob_faculty(){return job_faculty;}
    public Boolean getCheckFacultyLogin(){return checkFacultyLogin;}
}
