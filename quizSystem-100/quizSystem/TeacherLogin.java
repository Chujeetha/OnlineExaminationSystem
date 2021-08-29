package quizSystem;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;


public class TeacherLogin {
    Client client;
    JFrame frame;
    JLabel label1,label2,label3,label4,label5;
    JTextField textField1;
    JPasswordField textField2;
    JButton button1,button2;
    FacultyDetail facultyDetail;
   public TeacherLogin(Client client)
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
        frame = new JFrame("TEACHER LOGIN");
        frame.setFont(new Font("Times New Roman",Font.BOLD,16));
        frame.setSize(1100,600);
        frame.setLayout(null);
        BufferedImage img=null;
        try {
            img=ImageIO.read(new File("quizImages/samplebackground-2.jpg"));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        Image img2=img.getScaledInstance(1100, 600, Image.SCALE_SMOOTH);
        ImageIcon ig=new ImageIcon(img2);
        frame.setContentPane(new JLabel(ig));
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        /*ImageIcon image1= new ImageIcon(new ImageIcon("quizImages/login.png").getImage().getScaledInstance(30, 30,Image.SCALE_DEFAULT));

        ImageIcon image= new ImageIcon("quizImages/quizFacultyLogin_1.jpg");
        JLabel imageLabel = new JLabel(image);
        imageLabel.setBounds(600,50,200,100);
        imageLabel.setVisible(true);
        frame.add(imageLabel);*/
        button1.setBackground(Color.WHITE);
        button1.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button1.setBackground(Color.WHITE);
                button1.setBackground(Color.ORANGE);
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
                button2.setBackground(Color.ORANGE);
            }
            public void mouseExited(MouseEvent e) {
                button2.setBackground(UIManager.getColor("control"));
                button2.setBackground(Color.WHITE);
            }
        });
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public void createLable()
    {
        label1 = new JLabel("TEACHER LOGIN");
        label1.setBounds(100,40,400,40);
        label1.setFont(new Font("Times New Roman",Font.BOLD,40));
        label1.setForeground(Color.white);
        label2 = new JLabel("E - MAIL");
        label2.setBounds(100,150,100,40);
        label2.setFont(new Font("Times New Roman",Font.BOLD,16));
        label2.setForeground(Color.white);
        label3 = new JLabel("PASSWORD ");
        label3.setBounds(100,230,100,40);
        label3.setFont(new Font("Times New Roman",Font.BOLD,16));
        label3.setForeground(Color.white);
        label4 = new JLabel("Do you have an Account? ");
        label4.setBounds(100,370,200,40);
        label4.setFont(new Font("Times New Roman",Font.BOLD,16));
        label4.setForeground(Color.white);
        label5 = new JLabel("ENTER VALID CREDENTIALS");
        label5.setBounds(450,180,250,30);
        label5.setFont(new Font("Times New Roman",Font.BOLD,16));
        label5.setForeground(Color.white);
        label5.setBorder(BorderFactory.createSoftBevelBorder(1));
        label5.setVisible(false);
    }
    public void createTextField()
    {
        textField1 = new JTextField("aaju9876@gmail.com");
        textField1.setBounds(230,150,180,40);
        textField1.setFont(new Font("Times New Roman",Font.PLAIN,16));
        textField2 = new JPasswordField("Aaju@123");
        textField2.setBounds(230,230,180,40);
        textField2.setFont(new Font("Times New Roman",Font.PLAIN,16));
    }
    public void createButton()
    {
        button1 = new JButton("LOGIN");
        button1.setBounds(150,300,200,40);
        button1.setFont(new Font("Times New Roman",Font.BOLD,16));
        button2 = new JButton("REGISTER");
        button2.setBounds(300,370,200,40);
        button2.setFont(new Font("Times New Roman",Font.BOLD,16));
    }
    public void createActionListener()
    {
        this.button1.addActionListener(e -> {
            facultyDetail = new FacultyDetail();
            facultyDetail.setEmail_faculty(textField1.getText());
            facultyDetail.setPassword_faculty(textField2.getText());
            facultyDetail.setJob_faculty('L');
            this.client = new Client("localhost",6666);
            this.client.sendFacultyDetail(this.facultyDetail);
            this.facultyDetail = this.client.receiveFacultDetail();
            System.out.println(facultyDetail.getCheckFacultyLogin());
            if(this.facultyDetail.getCheckFacultyLogin())
            {
                new TeacherMainPage(this.client);
                this.frame.dispose();
            }
            else
            {
                label5.setVisible(true);
            }
        });
        this.button2.addActionListener(e -> {
            new TeacherRegister(this.client);
            this.frame.dispose();
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
