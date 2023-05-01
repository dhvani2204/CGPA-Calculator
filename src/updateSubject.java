import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
public class updateSubject extends JDialog {
    private JPanel jP;
    private JTextField tf1;
    private JTextField tf2;
    private JTextField tf3;
    private JButton btnUpdate;
    private JButton btnCncl;

    public updateSubject() {
        //super((Frame)parent);
        setTitle("Add Subject");
        setContentPane(jP);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        //setLocationRelativeTo((Component) parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSub();
            }
        });
        btnCncl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }

    private void updateSub() {
        String sid = tf1.getText();
        String subName = tf2.getText();
        int nsc = Integer.parseInt(tf3.getText());

        if (sid.isEmpty() || subName.isEmpty() || nsc == 0) {
            JOptionPane.showMessageDialog(this, "Please enter all fields", "Try again", JOptionPane.ERROR_MESSAGE);
            return;
        }
        sub = updateSubToDatabase(sid, subName, String.valueOf(nsc));
        if (sub != null) {
            JOptionPane.showMessageDialog(this,
                    "subject updated",
                    "Successful Updation",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update subject", "Try again", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Subject sub;

    private Subject updateSubToDatabase(String sid, String subName, String nsc) {
        Subject sub = null;
        final String DB_URL = "jdbc:mysql://localhost:3306/submanage?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "DhvaniP@22";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "UPDATE subjects SET course_title = ?,credits =? WHERE cid= ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, subName);
            preparedStatement.setString(2, nsc);
            preparedStatement.setString(3, sid);

            int updatedRows = preparedStatement.executeUpdate();
            if (updatedRows > 0) {
                JOptionPane.showMessageDialog(this,
                        "Subject updated:",
                        "Successful Updation",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(this,
                        "Failed to update subject:",
                        "Failed Updation",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            preparedStatement.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sub;
    }

    public static void main(String[] args) {
new updateSubject();
    }
}