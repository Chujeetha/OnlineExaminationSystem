package quizSystem;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class TeacherRegister {
    Client client;
    JFrame frame;
    String[] gend= {"-","Male","Female","Others"};
    JLabel label1 = new JLabel("FACULTY REGISTRATION FORM");
    JLabel label2 = new JLabel("E-MAIL");
    JLabel label3 = new JLabel("NAME");
    JLabel label4 = new JLabel("MOBILE");
    JLabel label5 = new JLabel("PASSWORD");
    JLabel label6 = new JLabel("GENDER");
    JTextField textField1 = new JTextField("apple@gmail.com");
    JTextField textField2 = new JTextField("apple");
    JTextField textField3 = new JTextField("9876543210");
    JTextField passwordField = new JPasswordField("Apple@123");
    JComboBox textField4 = new JComboBox(gend);
    JButton button1;
    FacultyDetail facultyDetail;
  public  TeacherRegister(Client client)
  {
      this.client = client;
      createUI();
  }
  public void createUI()
  {
      createLabel();
      createTextField();
      createButton();
      createActionListener();
      createFrame();
      addElements();
      System.out.println("success");
  }
  public void createFrame()
  {
      frame = new JFrame("Teacher Register");
      frame.setFont(new Font("Times New Roman",Font.BOLD,16));
      frame.setSize(1100,800);
      frame.setLayout(null);
      frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      BufferedImage img=null;
      try {
          img= ImageIO.read(new File("quizImages/samplebackground-2.jpg"));
      }
      catch(Exception e) {
          e.printStackTrace();
      }
      Image img2=img.getScaledInstance(1100, 600, Image.SCALE_SMOOTH);
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
  }
  public void createLabel()
  {
     // label1 = new JLabel("FACULTY REGISTRATION FORM");
      label1.setFont(new Font("Times New Roman",Font.BOLD,40));
      label1.setBounds(150,70,900,30);
      label1.setForeground(Color.white);

      label2.setBounds(100,150,100,30);
      label2.setFont(new Font("Times New Roman",Font.BOLD,16));
      label2.setForeground(Color.white);
     // label3 = new JLabel("NAME");
      label3.setBounds(100,200,100,30);
      label3.setFont(new Font("Times New Roman",Font.BOLD,16));
      label3.setForeground(Color.white);
     // label4 = new JLabel("MOBILE");
      label4.setBounds(100,250,100,30);
      label4.setFont(new Font("Times New Roman",Font.BOLD,16));
      label4.setForeground(Color.white);
      //label5 = new JLabel("PASSWORD");
      label5.setBounds(100,300,100,30);
      label5.setFont(new Font("Times New Roman",Font.BOLD,16));
      label5.setForeground(Color.white);
      //label6 = new JLabel("GENDER");
      label6.setBounds(100,350,100,30);
      label6.setFont(new Font("Times New Roman",Font.BOLD,16));
      label6.setForeground(Color.white);
  }
  public void createTextField()
  {

      textField1.setBounds(210,150,180,30);
      textField1.setFont(new Font("Times New Roman",Font.PLAIN,16));

      textField2.setBounds(210,200,180,30);
      textField2.setFont(new Font("Times New Roman",Font.PLAIN,16));

      textField3.setBounds(210,250,180,30);
      textField3.setFont(new Font("Times New Roman",Font.PLAIN,16));

      passwordField.setBounds(210,300,180,30);
      passwordField.setFont(new Font("Times New Roman",Font.PLAIN,16));

      textField4.setBounds(210,350,180,30);
      textField4.setFont(new Font("Times New Roman",Font.PLAIN,16));
  }
  public void createButton()
  {
      button1 = new JButton("REGISTER");
      button1.setBounds(200,400,180,30);
      button1.setFont(new Font("Times New Roman",Font.BOLD,16));

  }
  public void createActionListener()
  {
      button1.addActionListener(e -> {
          checkValid();
          this.client = new Client("localhost",6666);
          new TeacherLogin(this.client);
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
      frame.add(textField1);
      frame.add(textField2);
      frame.add(textField3);
      frame.add(textField4);
      frame.add(passwordField);
      frame.add(button1);
  }
  public void checkValid()
  {
     boolean d=valid();
      if(d){
      try{
      facultyDetail = new FacultyDetail();
      facultyDetail.setEmail_faculty(textField1.getText());
      //System.out.println("aaa"+facultyDetail.getEmail_faculty());
      facultyDetail.setName_faculty(textField2.getText());
      String mobile = textField3.getText();
      long mob = Long.parseLong(mobile);
      facultyDetail.setMobile_faculty(mob);
      facultyDetail.setPassword_faculty(passwordField.getText());
      String gen=(String) this.textField4.getSelectedItem();

      facultyDetail.setGender_faculty(gen);
      facultyDetail.setJob_faculty('R');
      this.client.sendFacultyDetail(this.facultyDetail);
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

  }}



