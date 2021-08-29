package quizSystem;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class TestCompletedForm {
    JFrame frame;
    JLabel label1,label2;
    JTextField textField;
    JButton button;
    Client client;
    String email;
    int courseId;
    StudentAnswer studentAnswer;
    public TestCompletedForm(Client client,String email,int courseId)
    {
        this.client = client;
        this.email = email;
        this.courseId = courseId;
        createUI();
    }
    public void createUI()
    {
        createLable();
        createTextField();
        createButton();
        createFrame();
        addElements();
        createActionListener();
    }
    public void createFrame()
    {
        frame = new JFrame("Test Completed");
        frame.setFont(new Font("Times New Roman",Font.BOLD,16));
        frame.setSize(800,600);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.toBack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BufferedImage img=null;
        try {
            img= ImageIO.read(new File("quizImages/testComplete.jpg"));
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
    public void createLable()
    {
        label1 = new JLabel("Mail ID");
        label1.setBounds(450,100,70,30);
        label1.setFont(new Font("Times New Roman",Font.BOLD,16));
        label1.setForeground(Color.white);
        label2 = new JLabel("TEST COMPLETED  !!!");
        label2.setBounds(200,175,600,50);
        label2.setForeground(Color.white);
        label2.setFont(new Font("Times New Roman",Font.BOLD,40));
    }
    public void createTextField()
    {
        textField = new JTextField();
        textField.setText(this.email);
        textField.setBounds(520,100,150,30);
        textField.setFont(new Font("Times New Roman",Font.PLAIN,16));
        textField.setEditable(false);
    }
    public void createButton()
    {
        button = new JButton("VIEW RESULT");
        button.setBounds(290,265,200,30);
        button.setFont(new Font("Times New Roman",Font.BOLD,16));
    }
    public void addElements()
    {
        frame.add(label1);
        frame.add(label2);
        frame.add(textField);
        frame.add(button);
    }
    public void createActionListener()
    {
        button.addActionListener(e -> {
            this.client = new Client("localhost",6666);
            this.studentAnswer = new StudentAnswer();
            this.studentAnswer.setEmail(this.email);
            this.studentAnswer.setcourseId(this.courseId);
            this.studentAnswer.setJob('R');
            this.client.sendStudentAnswer(this.studentAnswer);
            this.studentAnswer = this.client.receiveStudentAnswer();
            System.out.println(this.studentAnswer.getMark()+" / "+this.studentAnswer.getTotalQuestion());
            new StudentResult(this.client,this.email,this.studentAnswer.getMark(),this.studentAnswer.getTotalQuestion(),this.courseId);
            this.frame.dispose();
        });
    }
}
