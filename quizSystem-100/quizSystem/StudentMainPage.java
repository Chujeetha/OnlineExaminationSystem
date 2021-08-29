package quizSystem;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class StudentMainPage {
    JFrame frame;
    JLabel label,label1;
    JTextField textField;
    JButton button;
    Client client;
    JComboBox<String> comboBox;
    String email;
    ExistingSubjectList existingSubjectList;
    ArrayList<String> avaliableCourseList = new ArrayList<>();
    String [] arr ;
    FacultyQuestion facultyQuestion;
    public StudentMainPage(Client client,String email)
    {
        this.client = client;
        this.email = email;
        createUI();
    }
    public void createUI()
    {
        createLabel();
        createTextField();
        createComboBox();
        createButton();
        createFrame();
        addElements();
        createActionListener();
    }
    public void createFrame()
    {
        frame = new JFrame("Student Mainpage");
        frame.setFont(new Font("Times New Roman",Font.BOLD,16));
        frame.setSize(800,600);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BufferedImage img=null;
        try {
            img= ImageIO.read(new File("quizImages/studentMain.png"));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        Image img2=img.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
        ImageIcon ig=new ImageIcon(img2);
        frame.setContentPane(new JLabel(ig));
        frame.setLocationRelativeTo(null);
        frame.pack();

        button.setBackground(Color.WHITE);
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(Color.WHITE);
                button.setBackground(Color.ORANGE);
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(UIManager.getColor("control"));
                button.setBackground(Color.WHITE);
            }
        });
    }
    public void createLabel()
    {
        label = new JLabel("Welcome");
        label.setBounds(500,20,70,30);
        label.setFont(new Font("Times New Roman",Font.BOLD,16));
        label.setForeground(Color.white);
        label1 = new JLabel("CHOOSE A COURSE");
        label1.setForeground(Color.white);
        label1.setFont(new Font("Times New Roman",Font.BOLD,30));
        label1.setBounds(100,70,300,30);
    }
    public void createTextField()
    {
        textField = new JTextField();
        textField.setBounds(580,20,180,30);
        textField.setText(email);
        textField.setFont(new Font("Times New Roman",Font.PLAIN,16));
        textField.setEditable(false);
    }
    public void createButton()
    {
        button = new JButton("TAKE TEST");
        button.setBounds(260,140,180,30);
        button.setFont(new Font("Times New Roman",Font.BOLD,16));
    }
    public void addElements()
    {
        frame.add(label);
        frame.add(label1);
        frame.add(textField);
        frame.add(comboBox);
        frame.add(button);
    }

    public void createComboBox()
    {
        this.client = new Client("localhost",6666);
        this.existingSubjectList = new ExistingSubjectList();
        this.existingSubjectList.setJob('U');
        this.client.sendExistingSubjectList(this.existingSubjectList);
        this.existingSubjectList = this.client.receiveExistingSubjectList();
        this.avaliableCourseList = this.existingSubjectList.getCourseList();
        System.out.println(this.avaliableCourseList);
        this.arr = new String[this.avaliableCourseList.size()+1];
        arr[0] = "-";
        for(int i=0;i<avaliableCourseList.size();i++)
        {
            arr[i+1] = avaliableCourseList.get(i);
            System.out.println(arr[i]);
        }
        comboBox = new JComboBox(this.arr);
        comboBox.setBounds(50,140,180,30);
        comboBox.setFont(new Font("Times New Roman",Font.PLAIN,16));
    }
    public void createActionListener()
    {
        button.addActionListener(e -> {
            this.client = new Client("localhost",6666);
            checkValid();
            this.client.sendExistingSubjectList(this.existingSubjectList);
            this.facultyQuestion = new FacultyQuestion();
            this.facultyQuestion = this.client.receiveFacultyQuestion();
            ArrayList<Integer> queId = this.facultyQuestion.getQuestionIdList();
            ArrayList<Integer> courseId = this.facultyQuestion.getCourseIdList();
            ArrayList<String> que = this.facultyQuestion.getQuestionList();
            ArrayList<String> opt1 = this.facultyQuestion.getOption1List();
            ArrayList<String> opt2 = this.facultyQuestion.getOption2List();
            ArrayList<String> opt3 = this.facultyQuestion.getOption3List();
            ArrayList<String> opt4 = this.facultyQuestion.getOption4List();
            ArrayList<String> ans = this.facultyQuestion.getAnswerList();
            System.out.println("Studentmain Page queid- "+queId);
            System.out.println("courseId - "+courseId);
            System.out.println("que - "+que);
            System.out.println("opt1 - "+opt1);
            System.out.println("opt2 - "+opt2);
            System.out.println("opt3 - "+opt3);
            System.out.println("opt4 - "+opt4);
            System.out.println("ans - "+ans);
            new InstructionPage(this.client,queId,courseId,que,opt1,opt2,opt3,opt4,ans,this.email);
            this.frame.dispose();
        });
    }
    public void checkValid()
    {
        boolean d=Valid();
        if(d)
        {
            this.existingSubjectList = new ExistingSubjectList();
            this.existingSubjectList.setJob('S');
            String gen=(String) this.comboBox.getSelectedItem();
            this.existingSubjectList.setCourse(gen);
            System.out.println("selected item "+this.existingSubjectList.getCourse());

        }
    }
    public Boolean Valid()
    {
        boolean value = true;
        if(String.valueOf(comboBox.getSelectedItem())=="-") {
            value=false;
        }
        return value;
    }


}
