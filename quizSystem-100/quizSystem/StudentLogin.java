package quizSystem;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
public class StudentLogin {
    Client client;
    JFrame frame;
    JLabel label1,label2,label3,label4,label5;
    JTextField textField1;
    JPasswordField textField2;
    JButton button1,button2;
    StudentDetail studentDetail;
    public StudentLogin(Client client)
    {
        this.client = client;
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
        frame = new JFrame("Student Login");
        frame.setFont(new Font("Times New Roman",Font.BOLD,16));
        frame.setSize(800,600);
        frame.setLayout(null);
        frame.setVisible(true);


        BufferedImage img=null;
        try {
            img=ImageIO.read(new File("quizImages/studentback-2.jpg"));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        Image img2=img.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
        ImageIcon ig=new ImageIcon(img2);

        button1.setBackground(Color.WHITE);
        button1.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button1.setBackground(Color.WHITE);
                button1.setBackground(Color.GRAY);
            }
            public void mouseExited(MouseEvent e) {
                button1.setBackground(UIManager.getColor("control"));
                button1.setBackground(Color.WHITE);
            }
        });
        button2.setBackground(Color.WHITE);
        button2.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button2.setBackground(Color.WHITE);
                button2.setBackground(Color.GRAY);
            }
            public void mouseExited(MouseEvent e) {
                button2.setBackground(UIManager.getColor("control"));
                button2.setBackground(Color.WHITE);
            }
        });

        frame.setContentPane(new JLabel(ig));
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public void createLable()
    {
        label1 = new JLabel("STUDENT LOGIN");
        label1.setBounds(250,70,600,40);
        label1.setFont(new Font("Times New Roman",Font.BOLD,30));
        label2 = new JLabel("E - MAIL");
        label2.setBounds(200,170,100,40);
        label2.setFont(new Font("Times New Roman",Font.BOLD,16));
        label3 = new JLabel("PASSWORD ");
        label3.setBounds(200,250,100,40);
        label3.setFont(new Font("Times New Roman",Font.BOLD,16));
        label4 = new JLabel("Do you have an account? ");
        label4.setBounds(170,360,190,40);
        label4.setFont(new Font("Times New Roman",Font.PLAIN,17));
        label5 = new JLabel("ENTER VALID CREDENTIAL");
        label5.setBorder(BorderFactory.createSoftBevelBorder(1));
        label5.setVisible(false);
        label5.setBounds(500,210,200,30);
        label5.setFont(new Font("Times New Roman",Font.BOLD,14));
        label5.setVisible(false);
    }
    public void createTextField()
    {
        textField1 = new JTextField("jai9876@gmail.com");
        textField1.setBounds(310,170,180,40);
        textField1.setFont(new Font("Times New Roman",Font.PLAIN,16));
        textField2 = new JPasswordField("Jai@1234");
        textField2.setBounds(310,250,180,40);
        textField2.setFont(new Font("Times New Roman",Font.PLAIN,16));
    }
    public void createButton()
    {
        button1 = new JButton("LOGIN");
        button1.setBounds(250,320,100,30);
        button1.setFont(new Font("Times New Roman",Font.BOLD,16));
        button1.setBorder(new RoundedBorder(10)) ;
        button2= new JButton("REGISTER");

        button2.setBounds(370,365,120,30);
        button2.setFont(new Font("Times New Roman",Font.BOLD,16));

        button2.setBorder(new RoundedBorder(10)) ;
    }
    public void createActionListener()
    {
        this.button2.addActionListener(e -> {
            this.client = new Client("localhost",6666);
            new StudentRegister(this.client);
            this.frame.dispose();
        });
        this.button1.addActionListener(e -> {
            this.client = new Client("localhost",6666);
            this.studentDetail = new StudentDetail();
            this.studentDetail.setEmail_student(textField1.getText());
            this.studentDetail.setPassword_student(textField2.getText());
            this.studentDetail.setJob_student('L');
            this.client.sendStudentDetail(this.studentDetail);
            this.studentDetail = this.client.receiveStudentDetail();
            System.out.println(this.studentDetail.getCheckStudentLogin());
            if(this.studentDetail.getCheckStudentLogin())
            {
                new StudentMainPage(this.client,textField1.getText());
                this.frame.dispose();
            }
            else
            {
                label5.setVisible(true);
            }
        });
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
        frame.add(button1);
        frame.add(button2);
    }
}
