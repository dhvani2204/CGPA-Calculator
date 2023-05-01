import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.DecimalFormat;

public class calCgpa extends JDialog {
    private JTextField t1;
    private JTextField t2;
    private JTextField t3;
    private JTextField t4;
    private JTextField t5;
    private JTextField t6;
    private JTextField t7;
    private JTextField t8;
    private JTextField t16;
    private JTextField t9;
    private JTextField t10;
    private JTextField t11;
    private JTextField t12;
    private JTextField t13;
    private JTextField t14;
    private JTextField t15;
    private JTextField[] gpaFields={t1,t2,t3,t4,t5,t6,t7,t8};
    private JTextField[] creditsField={t9,t10,t11,t12,t13,t14,t15,t16};
    private JButton btnCal;
    private JButton btnE;
    private JPanel jPanel;

    public calCgpa(){
        setTitle("Calculate CGPA");
        setContentPane(jPanel);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnCal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cal();
            }
        });
        btnE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }
    private void cal() {
        Integer totalCredits = 0;
        Integer credit = 0;
        double totalGpa = 0;
        boolean validCredits = true;
        for (int i = 0; i < gpaFields.length; i++) {
            double gpas = Double.parseDouble(gpaFields[i].getText());
            validCredits = true;
            do {
                int credits = Integer.parseInt(creditsField[i].getText());
                try {
                    credit = Integer.parseInt(String.valueOf(credits));
                } catch (NumberFormatException nfe) {
                    System.out.println("Please enter valid integer");
                    validCredits = false;
                }
            } while (validCredits != true);
            totalCredits += credit;
            totalGpa += gpas;
        }
        // Calculate CGPA
        DecimalFormat df = new DecimalFormat("0.0");
        Double cgpa = Double.valueOf(totalGpa) / 8.0;
        JOptionPane.showMessageDialog(this, "Your CGPA is: " + cgpa + "\n" + "Total Credits" + totalCredits);
    }
    public static void main(String[] args) {
        new calCgpa();
    }
}

