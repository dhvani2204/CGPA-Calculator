import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Welcome extends JFrame {
    private JPanel Jpanel;
    private JLabel logo;
    private JLabel heading;
    private JRadioButton btnStudent;
    private JRadioButton btnAdmin;
    private JButton btnLog;
    private JButton btnsignU;

    public Welcome() {
        setTitle("CGPA Calculator for SIT");
        setContentPane(Jpanel);
        setMinimumSize(new Dimension(500, 500));
        //setSize(1000, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        btnsignU.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (btnStudent.isSelected()) {
                    //open student signup
                    signUp signUpForm = new signUp(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            User user = ((signUp)e.getSource()).user;
                        }
                    });
                    signUpForm.setVisible(true);
                } else if (btnAdmin.isSelected()) {
                    // Open the admin signup form
                    adSignForm adminSignForm = new adSignForm(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            User1 user1 = ((adSignForm)e.getSource()).user1;
                        }
                    });

                    adminSignForm.setVisible(true);
                }
            }
        });
        btnLog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (btnStudent.isSelected()) {
                    // Open the student login form
                    loginForm studentLoginForm = new loginForm(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            User user = ((loginForm)e.getSource()).user;
                        }
                    });
                    studentLoginForm.setVisible(true);
                } else if (btnAdmin.isSelected()) {
                    // Open the admin login form
                    adLoginForm adminLoginForm = new adLoginForm(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            User1 user1=((adLoginForm)e.getSource()).user1;
                        }
                    });
                    adminLoginForm.setVisible(true);
                }
            }
        });
        setVisible(true);
    }
    public static void main(String[] args) {
        new Welcome();
    }
}