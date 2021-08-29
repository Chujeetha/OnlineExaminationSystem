package quizSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StudentQuizpage {
    JFrame frame;
    JLabel label1,label2,label3,label4,label5;
    JTextField textField1,textField2,textField3,textField4,textField5,textField6,textField7,textField8;
    JButton button;
    JRadioButton radioButton1,radioButton2,radioButton3,radioButton4;
    Client client;
    String email;
    int queId;
    int courseId;
    String que;
    String opt1;
    String opt2;
    String opt3;
    String opt4;
    ButtonGroup buttonGroup;
    StudentAnswer studentAnswer;
    public StudentQuizpage(Client client,int queId,int courseId,String que,String opt1,String opt2,
                           String opt3,String opt4,String email)
    {
        this.client = client;
        this.queId = queId;
        this.courseId = courseId;
        this.que = que;
        this.opt1 = opt1;
        this.opt2 = opt2;
        this.opt3 = opt3;
        this.opt4 = opt4;
        this.email = email;
        createUI();
    }
    public void createUI()
    {
        createLable();
        createTextField();
        createRadioButton();
        createButton();
        createFrame();
        addElements();
        createActionListener();
    }
    public void createFrame()
    {
        frame = new JFrame("Student quiz page");
        frame.setFont(new Font("Times New Roman",Font.BOLD,16));
        frame.setSize(1100,700);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.getContentPane().setBackground(new Color(153, 230, 255));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setAlwaysOnTop(true);
        button.setBackground(Color.WHITE);
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(Color.WHITE);
               button.setBackground(Color.lightGray);
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(UIManager.getColor("control"));
                button.setBackground(Color.WHITE);
            }
        });
    }
    public void createLable()
    {
        label1=new JLabel("Question No");
        label2=new JLabel("Course ID");
        label3=new JLabel("Question");
        label4=new JLabel("Answer Form");
        label5 = new JLabel("Mail Id");
        label1.setBounds(100, 100, 200, 30);
        label3.setBounds(100, 150, 200, 30);
        label2.setBounds(850,50,200,30);
        label4.setBounds(400,50,400,50);
        label5.setBounds(30,50,80,30);
        label1.setFont(new Font("Times New Roman",Font.BOLD,16));
        label2.setFont(new Font("Times New Roman",Font.BOLD,16));
        label3.setFont(new Font("Times New Roman",Font.BOLD,16));
        label4.setFont(new Font("Times New Roman",Font.BOLD,40));
        label5.setFont(new Font("Times New Roman",Font.BOLD,16));
    }
    public void createTextField()
    {
        textField1 = new JTextField();
        textField2 = new JTextField();
        textField3 = new JTextField();
        textField4 = new JTextField();
        textField5 = new JTextField();
        textField6 = new JTextField();
        textField7 = new JTextField();
        textField8 = new JTextField();
        textField1.setText(String.valueOf(this.queId));
        textField2.setText(this.que);
        textField3.setText(this.opt1);
        textField4.setText(this.opt2);
        textField5.setText(this.opt3);
        textField6.setText(this.opt4);
        textField7.setText(String.valueOf(this.courseId));
        textField8.setText(this.email);

        textField1.setBounds(230, 100,30, 30);
        textField2.setBounds(230,150,700,30);
        textField3.setBounds(230,200,700,30);
        textField4.setBounds(230,250,700,30);
        textField5.setBounds(230,300,700,30);
        textField6.setBounds(230,350,700,30);
        textField7.setBounds(950,50,100,30);
        textField8.setBounds(100,50,180,30);
        textField1.setFont(new Font("Times New Roman",Font.PLAIN,16));
        textField2.setFont(new Font("Times New Roman",Font.PLAIN,16));
        textField3.setFont(new Font("Times New Roman",Font.PLAIN,16));
        textField4.setFont(new Font("Times New Roman",Font.PLAIN,16));
        textField5.setFont(new Font("Times New Roman",Font.PLAIN,16));
        textField6.setFont(new Font("Times New Roman",Font.PLAIN,16));
        textField7.setFont(new Font("Times New Roman",Font.PLAIN,16));
        textField8.setFont(new Font("Times New Roman",Font.PLAIN,16));
        textField1.setEditable(false);
        textField2.setEditable(false);
        textField3.setEditable(false);
        textField3.setEditable(false);
        textField4.setEditable(false);
        textField5.setEditable(false);
        textField6.setEditable(false);
        textField7.setEditable(false);
        textField8.setEditable(false);

        System.out.println(this.courseId);
    }
    public void createRadioButton()
    {
        radioButton1 = new JRadioButton();
        radioButton1.setBounds(150,203,20,20);
        radioButton1.setBackground(new Color(153, 230, 255));
        radioButton1.setForeground(Color.red);
        radioButton2 = new JRadioButton();
        radioButton2.setBounds(150,253,20,20);
        radioButton2.setBackground(new Color(153, 230, 255));
        radioButton3 = new JRadioButton();
        radioButton3.setBounds(150,303,20,20);
        radioButton3.setBackground(new Color(153, 230, 255));
        radioButton4 = new JRadioButton();
        radioButton4.setBounds(150,353,20,20);
        radioButton4.setBackground(new Color(153, 230, 255));
        buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);
        buttonGroup.add(radioButton3);
        buttonGroup.add(radioButton4);
    }
    public void createButton()
    {
        button = new JButton("SUBMIT");
        button.setBounds(200,420,180,30);
        button.setFont(new Font("Times New Roman",Font.BOLD,16));
    }
    public void addElements()
    {
        frame.add(label1);
        frame.add(label2);
        frame.add(label3);
        frame.add(label4);
        frame.add(label5);
        frame.add(textField1);
        frame.add(textField2);
        frame.add(textField3);
        frame.add(textField4);
        frame.add(textField5);
        frame.add(textField6);
        frame.add(textField7);
        frame.add(textField8);
        frame.add(radioButton1);
        frame.add(radioButton2);
        frame.add(radioButton3);
        frame.add(radioButton4);
        frame.add(button);
    }
    public void createActionListener()
    {
        button.addActionListener(e -> {
            this.client = new Client("localhost",6666);
            this.studentAnswer = new StudentAnswer();
            this.studentAnswer.setQueId(Integer.parseInt(textField1.getText()));
            this.studentAnswer.setcourseId(Integer.parseInt(textField7.getText()));
            this.studentAnswer.setEmail(textField8.getText());
            this.studentAnswer.setJob('I');
            if(radioButton1.isSelected())
            {
                this.studentAnswer.setAnswer(textField3.getText());
            }
            else if(radioButton2.isSelected())
            {
                this.studentAnswer.setAnswer(textField4.getText());
            }
            else if(radioButton3.isSelected())
            {
                this.studentAnswer.setAnswer(textField5.getText());
            }
            else if(radioButton4.isSelected())
            {
                this.studentAnswer.setAnswer(textField6.getText());
            }
            this.client.sendStudentAnswer(this.studentAnswer);
            this.frame.dispose();
        });
    }
}
