package quizSystem;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class UpdateExistingCourse {
    Client client;
    JLabel label;
    JFrame frame;
    ArrayList<String> avaliableCourseList;
    ExistingSubjectList existingSubjectList;
    JComboBox<String> textField4;
    FacultyQuestion facultyQuestion;
    JButton button;
    String [] arr ;
    public UpdateExistingCourse(Client client,ArrayList<String> ans)
    {
        this.client = client;
        this.avaliableCourseList = ans;
        this.arr = new String[this.avaliableCourseList.size()+1];
        arr[0] = "-";
        for(int i=0;i<avaliableCourseList.size();i++)
        {
            arr[i+1] = avaliableCourseList.get(i);
            System.out.println(arr[i]);
        }
        createUI();
    }
    public void createUI()
    {
        createLabel();
        createComboBox();
        createButton();
        createFrame();
        addElements();
        createMouseClickedListener();
    }
    public void createFrame()
    {
        frame = new JFrame("CourseList");
        frame.setFont(new Font("Times New Roman",Font.BOLD,16));
        frame.setSize(1100,600);
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
    public void createButton()
    {
        button = new JButton("UPDATE");
        button.setBounds(320,120,180,30);
        button.setFont(new Font("Times New Roman",Font.BOLD,16));
    }
    public void createLabel()
    {
        label = new JLabel("LIST OF EXISTING COURSES");
        label.setBounds(100,50,700,30);
        label.setFont(new Font("Times New Roman",Font.BOLD,40));
        label.setForeground(Color.white);
    }
    public void createComboBox()
    {
    textField4 = new JComboBox(this.arr);
    textField4.setBounds(100,120,180,30);
    textField4.setFont(new Font("Times New Roman",Font.PLAIN,16));
    }
    public void addElements()
    {
        this.frame.add(label);
        this.frame.add(textField4);
        frame.add(button);
    }
    public void createMouseClickedListener()
    {
        button.addActionListener(e -> {
            this.client = new Client("localhost",6666);
            checkValid();
            this.client.sendExistingSubjectList(this.existingSubjectList);
            this.facultyQuestion = new FacultyQuestion();
            this.facultyQuestion = this.client.receiveFacultyQuestion();
            ArrayList<Integer> queId = this.facultyQuestion.getQuestionIdList();
            ArrayList<Integer> courseId = this.facultyQuestion.getCourseIdList();
            ArrayList<String> que = this.facultyQuestion.getQuestionList();
            ArrayList<String> opt1 = this.facultyQuestion.getOption1List();
            ArrayList<String> opt2 = this.facultyQuestion.getOption2List();
            ArrayList<String> opt3 = this.facultyQuestion.getOption3List();
            ArrayList<String> opt4 = this.facultyQuestion.getOption4List();
            ArrayList<String> ans = this.facultyQuestion.getAnswerList();
            System.out.println(queId);
            System.out.println(courseId);
            System.out.println(que);
            System.out.println(opt1);
            System.out.println(opt2);
            System.out.println(opt3);
            System.out.println(opt4);
            System.out.println(ans);
            for(int i=que.size()-1;i>=0;i--)
            {
                new UpdateQuestion(this.client,queId.get(i),courseId.get(i),que.get(i),opt1.get(i),
                        opt2.get(i),opt3.get(i),opt4.get(i),ans.get(i));
            }
            this.frame.dispose();

        });

    }
    public void checkValid()
    {
        boolean d=Valid();
        if(d)
        {
            this.existingSubjectList = new ExistingSubjectList();
            this.existingSubjectList.setJob('S');
            String gen=(String) this.textField4.getSelectedItem();
            this.existingSubjectList.setCourse(gen);
            System.out.println("selected item "+this.existingSubjectList.getCourse());

        }
    }
    public Boolean Valid()
    {
        boolean value = true;
        if(String.valueOf(textField4.getSelectedItem())=="-") {
            value=false;
        }
        return value;
    }

}
