package quizSystem;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InstructionPage{
        JLabel l1;
        JTextArea t1;
        JButton b1;
        JFrame frame;
        Client client;
        String email;
    ArrayList<Integer> queId = new ArrayList<>();
    ArrayList<Integer> courseId = new ArrayList<>();
    ArrayList<String> que = new ArrayList<>();
    ArrayList<String> opt1 = new ArrayList<>();
    ArrayList<String> opt2 = new ArrayList<>();
    ArrayList<String> opt3 = new ArrayList<>();
    ArrayList<String> opt4 = new ArrayList<>();
    ArrayList<String> ans = new ArrayList<>();
    public  InstructionPage(Client client,ArrayList<Integer> queId,ArrayList<Integer> courseId,
                            ArrayList<String>que,ArrayList<String>opt1,ArrayList<String>opt2,ArrayList<String>opt3,
                            ArrayList<String> opt4,ArrayList<String> ans,String email)
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
        this.email = email;
        createUI();
    }
    public void createUI()
    {
        createFrame();
        createlable();
        createTextArea();
        createButton();
        addElements();
        createActionListener();
    }
    public void createFrame()
    {
        frame = new JFrame("Instruction page");
        frame.setSize(1000,650);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.getContentPane().setBackground(new Color(153, 230, 255));
    }
    public void createlable()
    {
        l1=new JLabel("Terms and Instructions");
        l1.setBounds(100, 30, 300, 25);
        l1.setFont(new Font("Times New Roman",Font.BOLD,20));
    }
    public void createTextArea()
    {
        t1=new JTextArea();
        t1.setText("\n"+"-> It is NOT advisable to take test in mobile. Use a laptop or desktop instead."+
                "\n"+"\n"+"-> Before attempting the test you must ensure that all programs including email has to be CLOSED."+
                "\n"+"\n"+"-> No tab switches are allowed during the test, it may result in premature submission of the test"+
                "\n"+"\n"+"-> If tab switches counted on its also marked as  MALPRACTICE / PLAGIARISM / COPY."+
                "\n"+"\n"+"-> Any notifications or Pop-ups during the test will be counted as tab switches and may result in"+
                "\n"+"pre-mature closure of your test. Please ensure that its turned off."+
                "\n"+"\n"+"-> Its necessary to make sure that you submitted your test by using SUBMIT button."+
                "\n"+"\n"+"-> Try to complete the test within the allotted time, if the time ends up AUTOMATIC submission will be"+"\n"+"appeared."+
                "\n"+"\n"+"-> Some Anti-virus software will prevent you from login and from taking the test. In such cases disable"+
                "\n"+"the anti-virus and try again." );

        t1.setEditable(false);
        t1.setBounds(100, 80, 800, 350);
        t1.setFont(new Font("Times New Roman",Font.PLAIN,17));
    }
    public void createButton()
    {
        b1=new JButton("Agree and Proceed");

        b1.setBounds(400,450 , 200, 25);
        b1.setFont(new Font("Times New Roman",Font.BOLD,16));
    }
    public void addElements()
    {
        frame.add(l1);
        frame.add(t1);
        frame.add(b1);
    }
    public void createActionListener()
    {
        b1.addActionListener(e -> {
            //this.client = new Client("localhost",6666);
            new TestCompletedForm(this.client,this.email,this.courseId.get(0));
            for(int i=queId.size()-1;i>=0;i--)
            {
                new StudentQuizpage(this.client,queId.get(i),courseId.get(i),que.get(i),
                        opt1.get(i),opt2.get(i),opt3.get(i),opt4.get(i),this.email);
            }
            this.frame.dispose();

        });
    }


}

