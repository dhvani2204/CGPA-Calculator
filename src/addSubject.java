import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class addSubject extends JDialog {
    private JTextField cC;
    private JTextField cT;
    private JTextField nOc;
    private JButton addButton;
    private JButton cancelButton;
    private JPanel Jp;
    private String branch;
    private String semester;

    public addSubject(String semester, String branch) {
        //super((Frame)parent);
        setTitle("Add Subject");
        setContentPane(Jp);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        //setLocationRelativeTo((Component) parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.semester=semester;
        this.branch=branch;


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSub();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        //JButton backButton = new JButton("Back");
        //backButton.addActionListener(backActionListener);
        //add(backButton, BorderLayout.PAGE_END);
        setVisible(true);
    }

    private void addSub() {
        String cid = cC.getText();
        String course_title = cT.getText();
        int credits = Integer.parseInt(nOc.getText());

        if (cid.isEmpty() || course_title.isEmpty() || credits == 0) {
            JOptionPane.showMessageDialog(this, "Please enter all fields", "Try again", JOptionPane.ERROR_MESSAGE);
            return;
        }
        sub = addUserToDatabase(cid, course_title, String.valueOf(credits),semester, branch);
        if (sub != null) {
                JOptionPane.showMessageDialog(this,
                        "New subject:" + sub.course_title,
                        "Successful Registration",
                        JOptionPane.INFORMATION_MESSAGE);
            }
         else {
            JOptionPane.showMessageDialog(this, "Failed to register new user", "Try again", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Subject sub;

    private Subject addUserToDatabase(String cid, String course_title, String credits,String semester, String branch) {
        Subject sub = null;
        final String DB_URL = "jdbc:mysql://localhost:3306/submanage?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "DhvaniP@22";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO subjects (cid,course_title,credits,semester,branch) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, cid);
            preparedStatement.setString(2, course_title);
            preparedStatement.setString(3, credits);
            preparedStatement.setString(4, semester);
            preparedStatement.setString(5, branch);

            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                sub = new Subject();
                sub.cid = cid;
                sub.course_title = course_title;
                sub.credits = Integer.parseInt(credits);
            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sub;
    }

    public static void main(String[] args) {
       // addSubject myForm1 = new addSubject(branch, semester);
       // Subject sub = myForm1.sub;
        //if (sub != null) {
          //  System.out.println("Successful addition of:" + sub.course_title);
        //} else {
          //  System.out.println("registration canceled");
        //}
    }
}
