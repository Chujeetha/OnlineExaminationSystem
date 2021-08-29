package quizSystem;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class Home {
    JFrame frame;
    JButton studentLogin, teacherLogin;
    JLabel label;
    Client client;
  public void createUI()
  {
        createClient();
        createButton();
        createLable();
        createFrame();
        createActionListener();
        addElements();
  }
  public void createButton()
  {
        studentLogin = new JButton("STUDENT LOGIN");
        studentLogin.setBounds(50,530,200,40);
        studentLogin.setFont(new Font("Times New Roman",Font.BOLD,16));
        teacherLogin = new JButton("TEACHER LOGIN");
        teacherLogin.setBounds(550,530,200,40);
        teacherLogin.setFont(new Font("Times New Roman",Font.BOLD,16));
  }
  public void createLable()
  {
        label = new JLabel("ONLINE EXAMINATION SYSTEM");
        label.setBounds(70,20,700,50);
        label.setFont(new Font("Times New Roman",Font.BOLD,40));
        //label.setForeground(Color.white);

  }
  public void createFrame()
  {
      frame = new JFrame("Home");
      frame.setLayout(null);
      frame.setBounds(0,0,800,600);
      BufferedImage img=null;
      try {
          img=ImageIO.read(new File("quizImages/home7.jpg"));
      }
      catch(Exception e) {
          e.printStackTrace();
      }
      Image img2=img.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
      ImageIcon ig=new ImageIcon(img2);
      frame.setContentPane(new JLabel(ig));
      frame.setLocationRelativeTo(null);
      frame.pack();

      /*ImageIcon image= new ImageIcon(new ImageIcon("quizImages/login.png").getImage().getScaledInstance(30, 30,Image.SCALE_DEFAULT));
      studentLogin.setIcon(image);*/
      studentLogin.setBackground(Color.WHITE);
      studentLogin.addMouseListener(new MouseAdapter() {
          public void mouseEntered(MouseEvent e) {
              studentLogin.setBackground(Color.WHITE);
              studentLogin.setBackground(Color.ORANGE);
          }
          public void mouseExited(MouseEvent e) {
              studentLogin.setBackground(UIManager.getColor("control"));
              studentLogin.setBackground(Color.WHITE);
          }
      });

      //ImageIcon image2= new ImageIcon(new ImageIcon("quizImages/login.png").getImage().getScaledInstance(30, 30,Image.SCALE_DEFAULT));
      //teacherLogin.setIcon(image2);
      teacherLogin.setBackground(Color.WHITE);
      teacherLogin.addMouseListener(new MouseAdapter() {
          public void mouseEntered(MouseEvent e) {
              teacherLogin.setBackground(Color.WHITE);
              teacherLogin.setBackground(Color.ORANGE);
          }
          public void mouseExited(MouseEvent e) {
              teacherLogin.setBackground(UIManager.getColor("control"));
              teacherLogin.setBackground(Color.WHITE);
          }
      });
      frame.setVisible(true);
      frame.setResizable(false);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  public void addElements()
  {
      frame.add(label);
      frame.add(studentLogin);
      frame.add(teacherLogin);
  }
  public void createActionListener()
  {
      //System.out.println("create action listener");
      this.studentLogin.addActionListener(e ->{
          //client = new Client("localhost",6666);
              new StudentLogin(this.client);
              this.frame.dispose();
          });
      this.teacherLogin.addActionListener(e -> {
          //client = new Client("localhost",6666);
          new TeacherLogin(this.client);
          this.frame.dispose();
      });
  }
  public void createClient()
  {
      this.client = new Client("localhost",6666);
  }
  public static void main(String [] args)
  {
      new Home().createUI();
  }

}
