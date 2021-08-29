package quizSystem;

import javax.imageio.ImageIO;
import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Properties;

public class StudentResult {
    Client client;
    String  email;
    int mark;
    int totalQue;
    int courseId;
    JFrame frame;
    JLabel label1,label2,label3,label4,label5;
    JTextField textField1,textField2,textField3,textField4,textField5;
    JButton b1,b2;
    StudentAnswer studentAnswer;
    public StudentResult(Client client,String email,int mark,int totalQue,int courseId)
    {
        this.client = client;
        this.courseId = courseId;
        this.email = email;
        this.mark = mark;
        this.totalQue = totalQue;
        createUI();
    }
    public void createUI()
    {
        createLabel();
        createTextField();
        createButton();
        createFrame();
        addElements();
        createActionListener();
    }
    public void createFrame()
    {
        frame = new JFrame("Result");
        frame.setSize(800,600);
        frame.setFont(new Font("Times New Roman",Font.BOLD,16));
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BufferedImage img=null;
        try {
            img= ImageIO.read(new File("quizImages/studentback-2.jpg"));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        Image img2=img.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
        ImageIcon ig=new ImageIcon(img2);
        frame.setContentPane(new JLabel(ig));
        frame.setLocationRelativeTo(null);
        frame.pack();
        b2.setBackground(Color.WHITE);
        b2.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                b2.setBackground(Color.WHITE);
                b2.setBackground(Color.lightGray);
            }
            public void mouseExited(MouseEvent e) {
                b2.setBackground(UIManager.getColor("control"));
                b2.setBackground(Color.WHITE);
            }
        });
    }
    public void createLabel()
    {
        label1 = new JLabel("RESULT");
        label2 = new JLabel("E-mail");
        label3 = new JLabel("Course ID");
        label4 = new JLabel("Total Number of Question");
        label5 = new JLabel("Marks Obtained");

        label1.setBounds(330,70,300,30);
        label1.setFont(new Font("Times New Roman",Font.BOLD,40));
        label2.setBounds(10,20,70,30);
        label2.setFont(new Font("Times New Roman",Font.BOLD,16));
        label3.setBounds(650,20,100,30);
        label3.setFont(new Font("Times New Roman",Font.BOLD,16));
        label4.setBounds(250,220,180,30);
        label4.setFont(new Font("Times New Roman",Font.BOLD,16));
        label5.setBounds(250,270,180,30);
        label5.setFont(new Font("Times New Roman",Font.BOLD,16));
    }
    public void createTextField()
    {
        textField1 = new JTextField();
        textField2 = new JTextField();
        textField3 = new JTextField();
        textField4 = new JTextField();
        textField5 = new JTextField();

        textField1.setBounds(80,20,200,30);
        textField1.setFont(new Font("Times New Roman",Font.PLAIN,16));
        textField2.setBounds(750,20,30,30);
        textField2.setFont(new Font("Times New Roman",Font.PLAIN,16));
        textField3.setBounds(440,220,200,30);
        textField3.setFont(new Font("Times New Roman",Font.PLAIN,16));
        textField4.setBounds(440,270,200,30);
        textField4.setFont(new Font("Times New Roman",Font.PLAIN,16));


        textField1.setText(this.email);
        textField2.setText(String.valueOf(this.courseId));
        textField3.setText(String.valueOf(this.totalQue));
        textField4.setText(String.valueOf(this.mark));
        String res = String.valueOf(mark)+"/"+String.valueOf(totalQue);
        b1 = new RoundButton(res);
        b1.setBounds(650,110,100,100);
        b1.setFont(new Font("Times New Roman",Font.BOLD,30));
        System.out.println(res);


        textField1.setEditable(false);
        textField2.setEditable(false);
        textField3.setEditable(false);
        textField4.setEditable(false);


    }
    public void createButton()
    {
        b2 = new JButton("LOGOUT");
        b2.setBounds(330,350,160,30);
        b2.setFont(new Font("Times New Roman",Font.BOLD,16));
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
        frame.add(b1);
        frame.add(b2);
    }

    public void createActionListener()
    {
        b2.addActionListener(e -> {
            final String username = "19eucb021@skcet.ac.in";
            final String password = "JAIsakthi00&";
            try {
                Properties props = new Properties();
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.port", "587");
                props.put("mail.smtp.ssl.trust", "smtp.gmail.com");


                Session session = Session.getInstance(props,new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
                Message message = new MimeMessage(session);
                message.setSubject("Result");
                message.setFrom(new InternetAddress(username));
                message.setText("E-MAIL : "+this.email+"\n"+"Course Id : "+this.courseId+"\n"+"Total Number of Questions : "+this.totalQue+"\n"+"Marks obtained : "+this.mark);
                message.setRecipient(RecipientType.TO,new InternetAddress(textField1.getText()));

                JOptionPane.showMessageDialog(null, "Sent");
                Transport.send(message);

            }
            catch(Exception e1) {
                System.out.println(e1);
            }
            new Home().createUI();
            this.frame.dispose();
        });
    }
}
