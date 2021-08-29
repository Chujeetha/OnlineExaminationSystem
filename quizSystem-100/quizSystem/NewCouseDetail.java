package quizSystem;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class NewCouseDetail {
    Client client;
    JFrame frame;
    JLabel label1,label2,label3;
    JTextField textField1,textField2;
    JButton button;
    ExistingSubjectList existingSubjectList;
    QuestionCreation questionCreation;
    static int totalQue = 0;
    public NewCouseDetail(Client client)
    {
        this.client = client;
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
        frame = new JFrame("NewCourse_Add");
        frame.setFont(new Font("Times New Roman",Font.BOLD,16));
        frame.setLayout(null);
        frame.setSize(800,600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BufferedImage img=null;
        try {
            img= ImageIO.read(new File("quizImages/samplebackground-2.jpg"));
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
        label1 = new JLabel("COURSE DETAILS");
        label1.setBounds(300,50,400,30);
        label1.setFont(new Font("Times New Roman",Font.BOLD,36));
        label1.setForeground(Color.white);
        label2 = new JLabel("TOTAL QUESTION NUMBER");
        label2.setBounds(70,130,250,30);
        label2.setFont(new Font("Times New Roman",Font.BOLD,16));
        label2.setForeground(Color.white);
        label3 = new JLabel("COURSE NAME");
        label3.setBounds(70,180,250,30);
        label3.setFont(new Font("Times New Roman",Font.BOLD,16));
        label3.setForeground(Color.white);
    }
    public void createTextField()
    {
        textField1 = new JTextField("1");
        textField1.setBounds(350,130,180,30);
        textField1.setFont(new Font("Times New Roman",Font.PLAIN,16));
        textField2 = new JTextField("cat");
        textField2.setBounds(350,180,180,30);
        textField2.setFont(new Font("Times New Roman",Font.PLAIN,16));
    }
    public void createButton()
    {
        button = new JButton("Create");
        button.setBounds(280,240,100,30);
        button.setFont(new Font("Times New Roman",Font.BOLD,16));
    }
    public void createActionListener()
    {
        this.button.addActionListener(e -> {
            existingSubjectList = new ExistingSubjectList();
            //existingSubjectList.setCourseId();
            existingSubjectList.setCourse(textField2.getText());
            existingSubjectList.setTotalQuestions(Integer.parseInt(textField1.getText()));
            existingSubjectList.setJob('A');
            totalQue = Integer.parseInt(textField1.getText());
            //this.client = new Client("localhost",6666);
            this.client.sendExistingSubjectList(this.existingSubjectList);
            this.frame.dispose();
            for(int i=totalQue;i>=1;i--) {
                new QuestionCreation(this.client,i,this.existingSubjectList.getCourse());
            }

        });
    }
    public void addElements()
    {
        frame.add(label1);
        frame.add(label2);
        frame.add(label3);
        frame.add(textField1);
        frame.add(textField2);
        frame.add(button);
    }
}
