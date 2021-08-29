package quizSystem;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class TeacherMainPage {
    JFrame frame;
    JButton button1,button2,button3;
    Client client;
    JLabel label;
    ExistingSubjectList existingSubjectList;
    ArrayList<String> avaliableCourseList;
    public TeacherMainPage(Client client)
    {
        this.client = client;
        createUI();
    }
    public void createUI()
    {
        createlabel();
        createButton();
        createFrame();
        addElements();
        createActionListener();
    }
    public void createFrame()
    {
        frame = new JFrame("TeacherMainPage");
        frame.setFont(new Font("Times New Roman",Font.BOLD,16));
        frame.setLayout(null);
        frame.setBounds(0,0,800,600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BufferedImage img=null;
        try {
            img= ImageIO.read(new File("quizImages/tm2.jpg"));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        Image img2=img.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
        ImageIcon ig=new ImageIcon(img2);
        frame.setContentPane(new JLabel(ig));
        frame.setLocationRelativeTo(null);
        frame.pack();
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
        button3.setBackground(Color.WHITE);
        button3.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button3.setBackground(Color.WHITE);
                button3.setBackground(Color.ORANGE);
            }
            public void mouseExited(MouseEvent e) {
                button3.setBackground(UIManager.getColor("control"));
                button3.setBackground(Color.WHITE);
            }
        });
    }
    public void createlabel()
    {
        label = new JLabel("TEACHER MAIN PAGE");
        label.setBounds(250,20,400,30);
        label.setFont(new Font("Times New Roman",Font.BOLD,30));
    }
    public void createButton()
    {
        button1 = new JButton("UPDATE EXISTING COURSE");
        button1.setBounds(20,140,250,30);
        button1.setFont(new Font("Times New Roman",Font.BOLD,16));
        button2 = new JButton("ADD NEW COURSE");
        button2.setBounds(20,190,250,30);
        button2.setFont(new Font("Times New Roman",Font.BOLD,16));
        button3 = new JButton("LOGOUT");
        button3.setBounds(20,240,250,30);
        button3.setFont(new Font("Times New Roman",Font.BOLD,16));
    }
    public void addElements()
    {
        frame.add(label);
        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
    }
    public void createActionListener()
    {
        //this.client = new Client("localhost",6666);
        button2.addActionListener(e -> {
            this.client = new Client("localhost",6666);
            new NewCouseDetail(this.client);
        });
        button1.addActionListener(e -> {
            this.client = new Client("localhost",6666);
            this.existingSubjectList = new ExistingSubjectList();
            this.existingSubjectList.setJob('U');
            this.client.sendExistingSubjectList(this.existingSubjectList);
            this.existingSubjectList = this.client.receiveExistingSubjectList();
            this.avaliableCourseList = this.existingSubjectList.getCourseList();
            System.out.println(this.avaliableCourseList);
            new UpdateExistingCourse(this.client,this.avaliableCourseList);
        });
        button3.addActionListener(e -> {
            new Home().createUI();
            this.frame.dispose();
        });
    }
}
