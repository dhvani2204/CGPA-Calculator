import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class deleteSubject extends JDialog {
    private JPanel jp;
    private JTextField subId;
    private JTextField subN;
    private JButton btnDel;
    private JButton btnCncl;

    public deleteSubject() {
        //super((Frame)parent);
        setTitle("Delete Subject");
        setContentPane(jp);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSub();
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

    private void deleteSub() {
        String ssid = subId.getText();
        String subNm = subN.getText();

        if (ssid.isEmpty() || subNm.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter all fields", "Try again", JOptionPane.ERROR_MESSAGE);
            return;
        }
        sub = deleteSubjectFromDatabase(ssid, subNm);
        if (sub != null) {
            JOptionPane.showMessageDialog(this,
                    "Subject Deleted",
                    "Successful Deletion",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Failed to delete subject!", "Try again", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Subject sub;

    private Subject deleteSubjectFromDatabase(String ssid, String subNm) {
        Subject sub = null;
        final String DB_URL = "jdbc:mysql://localhost:3306/submanage?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "DhvaniP@22";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "DELETE FROM subjects WHERE cid = ? AND course_title= ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, ssid);
            preparedStatement.setString(2, subNm);

            int deletedRows = preparedStatement.executeUpdate();
            if (deletedRows > 0) {
                JOptionPane.showMessageDialog(this,
                        "Subject deleted!",
                        "Successful Deletion",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            preparedStatement.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sub;
    }
    public static void main(String[] args) {
        new deleteSubject();
    }
}
