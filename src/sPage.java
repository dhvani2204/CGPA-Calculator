import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class sPage extends JDialog {
    private JButton btnCGPA;
    private JButton btnGPA;
    private JPanel jP;
    private JList list1;
    private String branch;
    private String semester;
    public sPage(String branch,String semester) {

        setTitle("Calculate CGPA and GPA");
        setContentPane(jP);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.branch=branch;
        this.semester=semester;

        btnCGPA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calCgpa form1 = new calCgpa();
                form1.setVisible(true);
            }
        });
        btnGPA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calGpa form2 = new calGpa();
                form2.setVisible(true);
            }
        });

        final String DB_URL="jdbc:mysql://localhost:3306/submanage?serverTimezone=UTC";
        final String USERNAME="root";
        final String PASSWORD="DhvaniP@22";
        try{
            Connection conn= DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Statement stmt=conn.createStatement();
            String sql="SELECT*FROM subjects WHERE branch=? AND semester=?";
            PreparedStatement preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setString(1,branch);
            preparedStatement.setString(2,semester);
            ResultSet rs=preparedStatement.executeQuery();

            DefaultListModel<String> model = new DefaultListModel<>();

            // Add each row of data to the model
            while (rs.next()) {
                // You can customize the way the data is displayed in the JList by modifying this line
                String row = rs.getString("cid") + "   " + rs.getString("course_title")+"   "+rs.getString("credits")+"   "+rs.getString("branch")+"   "+rs.getString("semester");
                model.addElement(row);
            }

            // Set the model for the JList
            list1.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     /**     DefaultTableModel model = new DefaultTableModel();

            // Set the column names
            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(meta.getColumnName(i));
            }

            // Add the data rows
            while (rs.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    rowData[i - 1] = rs.getObject(i);
                }
                model.addRow(rowData);
            }

            // Set the table model
            table1.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
**/

    public static void main(String[] args) {
        //EventQueue.invokeLater(() -> new sPage();
    }
}
