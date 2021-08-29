package quizSystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QuestionCreation {
    JFrame frame;
    JLabel l1,l2,l3,l4,l5,l6,l7,l9,l10;
    JTextField t1,t2,t3,t4,t5,t6,t7,t9;
    JButton b1;
    Client client;
    FacultyQuestion facultyQuestion;
    int id;
    String couseName;
    public QuestionCreation(Client client,int id,String couseName )
    {
            this.client = client;
            this.id = id;
            this.couseName = couseName;
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
        frame.setSize(1100,800);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.getContentPane().setBackground(new Color(153, 230, 255));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setForeground(Color.BLUE);
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
        l9=new JLabel("Course Name");
        l10=new JLabel("Question Form");

        l1.setBounds(100, 100, 200, 30);
        l2.setBounds(100, 150, 200, 30);
        l3.setBounds(100, 200, 200, 30);
        l4.setBounds(100, 250, 200, 30);
        l5.setBounds(100, 300, 200, 30);
        l6.setBounds(100, 350, 200, 30);
        l7.setBounds(100, 400, 200, 30);
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
        t2=new JTextField("a for");
        t3=new JTextField("apple");
        t4=new JTextField("bat");
        t5=new JTextField("cat");
        t6=new JTextField("dog");
        t7=new JTextField("apple");
        t9 = new JTextField();


        t1.setBounds(230, 100, 30, 30);
        t2.setBounds(230, 150, 700, 30);
        t3.setBounds(230, 200, 700, 30);
        t4.setBounds(230, 250, 700, 30);
        t5.setBounds(230, 300, 700, 30);
        t6.setBounds(230, 350, 700, 30);
        t7.setBounds(230, 400, 700, 30);
        t9.setBounds(950,50,100,30);

        t1.setFont(new Font("Times New Roman",Font.PLAIN,16));
        t1.setText(String.valueOf(id));
        t1.setEditable(false);
        t2.setFont(new Font("Times New Roman",Font.PLAIN,16));
        t3.setFont(new Font("Times New Roman",Font.PLAIN,16));
        t4.setFont(new Font("Times New Roman",Font.PLAIN,16));
        t5.setFont(new Font("Times New Roman",Font.PLAIN,16));
        t6.setFont(new Font("Times New Roman",Font.PLAIN,16));
        t7.setFont(new Font("Times New Roman",Font.PLAIN,16));
        t9.setFont(new Font("Times New Roman",Font.PLAIN,16));
        t9.setText(this.couseName);
        t9.setEditable(false);
    }
    public void createButton()
    {
        b1=new JButton("Save");

        b1.setBounds(100,470,100,25);

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
            facultyQuestion = new FacultyQuestion();
            facultyQuestion.setQuestionId(Integer.parseInt(t1.getText()));
            facultyQuestion.setQuestion(t2.getText());
            facultyQuestion.setOption1(t3.getText());
            facultyQuestion.setOption2(t4.getText());
            facultyQuestion.setOption3(t5.getText());
            facultyQuestion.setOption4(t6.getText());
            facultyQuestion.setAnswer(t7.getText());
            facultyQuestion.setJob('I');
            this.client = new Client("localhost",6666);
            this.client.sendFacultyQuestion(this.facultyQuestion);
            this.frame.dispose();
        });
    }

}
