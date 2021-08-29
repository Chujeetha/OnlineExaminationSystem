package quizSystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UpdateQuestion {
    JFrame frame;
    JLabel l1,l2,l3,l4,l5,l6,l7,l9,l10,limg,topl1,topl2;
    JTextField t1,t2,t3,t4,t5,t6,t7,t9;
    JButton b1;
    Client client;
    FacultyQuestion facultyQuestion;
    int queId;
    int courseId;
    String que;
    String opt1;
    String opt2;
    String opt3;
    String opt4;
    String ans;
    public UpdateQuestion(Client client,int queId,int courseId,String que,String opt1,String opt2,String opt3,String opt4,String ans)
    {
        this.client = client;
        this.queId = queId;
        this.courseId = courseId;
        this.que = que;
        this.opt1 = opt1;
        this.opt2 = opt2;
        this.opt3 = opt3;
        this.opt4 = opt4;
        this.ans = ans;
        createUI();
    }
    public void createUI()
    {
        createLabel();
        createTextField();
        createButton();
        createFrame();
        createActionListener();
        addElements();

    }
    public void createFrame()
    {
        frame = new JFrame("QuestionCreation");
        frame.setFont(new Font("Times New Roman",Font.BOLD,16));
        Container cn = frame.getContentPane();
        cn.setBackground(Color.WHITE);
        frame.setSize(1100,700);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.getContentPane().setBackground(new Color(153, 230, 255));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        b1.setBackground(Color.WHITE);
        b1.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                b1.setBackground(Color.WHITE);
                b1.setBackground(Color.ORANGE);
            }
            public void mouseExited(MouseEvent e) {
                b1.setBackground(UIManager.getColor("control"));
                b1.setBackground(Color.WHITE);
            }
        });

    }
    public void createLabel()
    {
        l1=new JLabel("Question No");
        l2=new JLabel("Question");
        l3=new JLabel("Option 1");
        l4=new JLabel("Option 2");
        l5=new JLabel("Option 3");
        l6=new JLabel("Option 4");
        l7=new JLabel("Answer");
        l9=new JLabel("Course ID");
        l10=new JLabel("Question Form");

        l1.setBounds(100, 150, 200, 30);
        l2.setBounds(100, 200, 200, 30);
        l3.setBounds(100, 250, 200, 30);
        l4.setBounds(100, 300, 200, 30);
        l5.setBounds(100, 350, 200, 30);
        l6.setBounds(100, 400, 200, 30);
        l7.setBounds(100, 450, 200, 30);
        l9.setBounds(850,50,200,30);
        l10.setBounds(400,50,400,50);

        l1.setFont(new Font("Times New Roman",Font.BOLD,16));
        l2.setFont(new Font("Times New Roman",Font.BOLD,16));
        l3.setFont(new Font("Times New Roman",Font.BOLD,16));
        l4.setFont(new Font("Times New Roman",Font.BOLD,16));
        l5.setFont(new Font("Times New Roman",Font.BOLD,16));
        l6.setFont(new Font("Times New Roman",Font.BOLD,16));
        l7.setFont(new Font("Times New Roman",Font.BOLD,16));
        l9.setFont(new Font("Times New Roman",Font.BOLD,16));
        l10.setFont(new Font("Times New Roman",Font.BOLD,40));
    }
    public void createTextField()
    {
        t1=new JTextField();
        t2=new JTextField();
        t3=new JTextField();
        t4=new JTextField();
        t5=new JTextField();
        t6=new JTextField();
        t7=new JTextField();
        t9 = new JTextField();


        t1.setBounds(230, 150, 30, 30);
        t2.setBounds(230, 200, 700, 30);
        t3.setBounds(230, 250, 700, 30);
        t4.setBounds(230, 300, 700, 30);
        t5.setBounds(230, 350, 700, 30);
        t6.setBounds(230, 400, 700, 30);
        t7.setBounds(230, 450, 700, 30);
        t9.setBounds(950,50,100,30);

        t1.setFont(new Font("Times New Roman",Font.PLAIN,16));
        t1.setText(String.valueOf(queId));
        t1.setEditable(false);
        t2.setFont(new Font("Times New Roman",Font.PLAIN,16));
        t2.setText(this.que);
        t3.setFont(new Font("Times New Roman",Font.PLAIN,16));
        t3.setText(this.opt1);
        t4.setFont(new Font("Times New Roman",Font.PLAIN,16));
        t4.setText(this.opt2);
        t5.setFont(new Font("Times New Roman",Font.PLAIN,16));
        t5.setText(this.opt3);
        t6.setFont(new Font("Times New Roman",Font.PLAIN,16));
        t6.setText(this.opt4);
        t7.setFont(new Font("Times New Roman",Font.PLAIN,16));
        t7.setText(this.ans);
        t9.setFont(new Font("Times New Roman",Font.PLAIN,16));
        t9.setText(String.valueOf(this.courseId));
        t9.setEditable(false);
    }
    public void createButton()
    {
        b1=new JButton("Save");
        b1.setBounds(100,500,130,30);
        b1.setFont(new Font("Times New Roman",Font.BOLD,16));

    }
    public void addElements()
    {
        frame.add(l1);
        frame.add(l2);
        frame.add(l3);
        frame.add(l4);
        frame.add(l5);
        frame.add(l6);
        frame.add(l7);
        frame.add(l9);
        frame.add(l10);
        frame.add(t1);
        frame.add(t2);
        frame.add(t3);
        frame.add(t4);
        frame.add(t5);
        frame.add(t6);
        frame.add(t7);
        frame.add(t9);
        frame.add(b1);
    }
    public void createActionListener()
    {
        b1.addActionListener(e -> {
            this.client = new Client("localhost",6666);
            this.facultyQuestion = new FacultyQuestion();
            this.facultyQuestion.setQuestionId(Integer.parseInt(t1.getText()));
            this.facultyQuestion.setCourseId(Integer.parseInt(t9.getText()));
            this.facultyQuestion.setQuestion(t2.getText());
            this.facultyQuestion.setOption1(t3.getText());
            this.facultyQuestion.setOption2(t4.getText());
            this.facultyQuestion.setOption3(t5.getText());
            this.facultyQuestion.setOption4(t6.getText());
            this.facultyQuestion.setAnswer(t7.getText());
            this.facultyQuestion.setJob('U');
            this.client.sendFacultyQuestion(this.facultyQuestion);
            this.frame.dispose();
        });
    }
}
