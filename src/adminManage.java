import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;

public class adminManage extends JDialog {

    private static final String URL = "jdbc:mysql://localhost:3306/SubManage";
    private static final String USER = "root";
    private static final String PASSWORD = "DhvaniP@22";
    private JPanel jP;
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDel;
    private JComboBox cB;
    private JComboBox cB1;

    public adminManage() {
        setTitle("Manage Subjects");
        setContentPane(jP);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    String semester= cB.getSelectedItem().toString();
                    String branch = cB1.getSelectedItem().toString();
                addSubject as=new addSubject(semester,branch);
                as.setVisible(true);

            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSubject us = new updateSubject();
                us.setVisible(true);
            }
        });
        btnDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSubject ds = new deleteSubject();
                ds.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        new adminManage();
    }
}