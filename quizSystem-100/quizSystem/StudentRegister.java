package quizSystem;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class StudentRegister {
    Client client;
    JFrame frame;
    String[] gend= {"-","Male","Female","Others"};
    JLabel label1 = new JLabel("STUDENT REGISTRATION FORM");
    JLabel label2 = new JLabel("E-MAIL");
    JLabel label3 = new JLabel("NAME");
    JLabel label7 = new JLabel("REGISTER NUMBER");
    JLabel label4 = new JLabel("MOBILE");
    JLabel label5 = new JLabel("PASSWORD");
    JLabel label6 = new JLabel("GENDER");
    JTextField textField1 = new JTextField("blue@gmail.com");
    JTextField textField2 = new JTextField("Blue");
    JTextField textField5 = new JTextField("01");
    JTextField textField3 = new JTextField("9876543210");
    JTextField passwordField = new JPasswordField("Blue@1234");
    JComboBox textField4 = new JComboBox(gend);
    JButton button1;
    StudentDetail studentDetail;
    public  StudentRegister(Client client)
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
        System.out.println("success");
    }
    public void createFrame()
    {
        frame = new JFrame("Student Register");
        frame.setFont(new Font("Times New Roman",Font.BOLD,16));
        frame.setSize(800,600);
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
        frame.setVisible(true);
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
    }
    public void createLabel()
    {
        label1.setBounds(270,20,300,30);
        label1.setFont(new Font("Times New Roman",Font.BOLD,16));

        label2.setBounds(150,70,200,30);
        label2.setFont(new Font("Times New Roman",Font.BOLD,16));
        // label3 = new JLabel("NAME");
        label3.setBounds(150,120,200,30);
        label3.setFont(new Font("Times New Roman",Font.BOLD,16));
        // label4 = new JLabel("MOBILE");
        label7.setBounds(150,170,200,30);
        label7.setFont(new Font("Times New Roman",Font.BOLD,16));
        //label5 = new JLabel("PASSWORD");
        label4.setBounds(150,220,200,30);
        label4.setFont(new Font("Times New Roman",Font.BOLD,16));
        //label6 = new JLabel("GENDER");
        label5.setBounds(150,270,200,30);
        label5.setFont(new Font("Times New Roman",Font.BOLD,16));
        label6.setBounds(150,320,200,30);
        label6.setFont(new Font("Times New Roman",Font.BOLD,16));
    }
    public void createTextField()
    {

        textField1.setBounds(370,70,180,30);
        textField1.setFont(new Font("Times New Roman",Font.PLAIN,16));

        textField2.setBounds(370,120,180,30);
        textField2.setFont(new Font("Times New Roman",Font.PLAIN,16));

        textField5.setBounds(370,170,180,30);
        textField5.setFont(new Font("Times New Roman",Font.PLAIN,16));

        passwordField.setBounds(370,270,180,30);
        passwordField.setFont(new Font("Times New Roman",Font.PLAIN,16));

        textField3.setBounds(370,220,180,30);
        textField3.setFont(new Font("Times New Roman",Font.PLAIN,16));

        textField4.setBounds(370,320,180,30);
        textField4.setFont(new Font("Times New Roman",Font.PLAIN,16));
    }
    public void createButton()
    {
        button1 = new JButton("REGISTER");
        button1.setBounds(280,400,150,30);
        button1.setFont(new Font("Times New Roman",Font.BOLD,16));
    }
    public void createActionListener()
    {
        button1.addActionListener(e -> {
            checkValid();
            this.client = new Client("localhost",6666);
            new StudentLogin(this.client);
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
        frame.add(label6);
        frame.add(label7);
        frame.add(textField1);
        frame.add(textField2);
        frame.add(textField3);
        frame.add(textField4);
        frame.add(textField5);
        frame.add(passwordField);
        frame.add(button1);
    }
    public void checkValid()
    {
        boolean d=valid();
        if(d){
            try{
                this.studentDetail = new StudentDetail();
                this.studentDetail.setEmail_student(textField1.getText());
                this.studentDetail.setName_student(textField2.getText());
                this.studentDetail.setRegNo_student(textField5.getText());
                String mobile = textField3.getText();
                long mob = Long.parseLong(mobile);
                this.studentDetail.setMobile_student(mob);
                this.studentDetail.setPassword_student(passwordField.getText());
                String gen=(String) this.textField4.getSelectedItem();

                this.studentDetail.setGender_student(gen);
                this.studentDetail.setJob_student('R');
                this.client.sendStudentDetail(this.studentDetail);
            }
            catch(Exception e){
                System.out.println(e);
                e.printStackTrace();
            }}}

    public boolean valid() {
        boolean value = true;
        String regex = "^[a-z0-9+_.-]+@[a-z0-9.-]+$";
        String regex1 = "\\d{10}";
        StringBuffer invalid = new StringBuffer();
        if (textField2.getText().length() == 0) {
            invalid.append("please enter name\n");
            JOptionPane.showMessageDialog(button1, invalid);
            value = false;

        }


        if (passwordField.getText().length() == 0) {
            invalid.append("please enter password\n");
            JOptionPane.showMessageDialog(button1, invalid);
            value = false;
        }
        if (!textField3.getText().matches(regex1)) {
            invalid.append("enter valid number\n");
            JOptionPane.showMessageDialog(button1, invalid);
            value = false;
        }


        if (!textField1.getText().matches(regex)) {
            invalid.append("email is invalid\n");
            JOptionPane.showMessageDialog(button1, invalid);
            value = false;
        }


        if(String.valueOf(textField4.getSelectedItem())=="-") {
            invalid.append("please select gender\n");
            JOptionPane.showMessageDialog(button1, invalid);
            value=false;
        }
        return value;

    }
}
