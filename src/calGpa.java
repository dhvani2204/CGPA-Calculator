import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.DecimalFormat;

public class calGpa extends JDialog {
    private JTextField fI2;
    private JTextField fI1;
    private JTextField fI3;
    private JTextField fI5;
    private JTextField fI7;
    private JTextField fI9;
    private JTextField fI11;
    private JTextField fI13;
    private JTextField fI15;
    private JTextField fI17;
    private JTextField fI19;
    private JTextField fI4;
    private JTextField fI6;
    private JTextField fI8;
    private JTextField fI10;
    private JTextField fI12;
    private JTextField fI14;
    private JTextField fI16;
    private JTextField fI18;
    private JTextField fI20;
    private JTextField[] gradeFields={fI1,fI3,fI5,fI7,fI9,fI11,fI13,fI15,fI17,fI19};
    private JTextField[] creditFields={fI2,fI4,fI6,fI8,fI10,fI12,fI14,fI16,fI18,fI20};
    private JButton btnCalc;
    private JButton btnCncl;
    private JPanel jP;

    //GPA===total points/total credits attempted
    //points for a class=grade value*credits
    // Grade value: O=10,A+=9, A=8, B+=7, B=6, C =5, P=4, F=0, AB=0
    public calGpa(){
        setTitle("Calculate GPA");
        setContentPane(jP);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnCalc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculate();
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
    private void calculate() {

        Integer totalCredits = 0;
        Integer totalPoints = 0;
        Integer credit = 0;
        boolean validCredits = true;

        for (int i = 0; i < gradeFields.length; i++) {
            validCredits = true;
            do {
                int credits = Integer.parseInt(creditFields[i].getText());
                try {
                    credit = Integer.parseInt(String.valueOf(credits));
                } catch (NumberFormatException nfe) {
                    System.out.println("Please enter valid integer");
                    validCredits = false;
                }
            } while (validCredits != true);

            String grade = "";
            boolean validGrade = true;
            Integer gradePoints = 0;

            do {
                validGrade = true;
                grade = gradeFields[i].getText();

                if (grade.equals("O")) {
                    gradePoints = 10;
                } else if (grade.equals("A+")) {
                    gradePoints = 9;
                } else if (grade.equals("A")) {
                    gradePoints = 8;
                } else if (grade.equals("B+")) {
                    gradePoints = 7;
                } else if (grade.equals("B")) {
                    gradePoints = 6;
                } else if (grade.equals("C")) {
                    gradePoints = 5;
                } else if (grade.equals("P")) {
                    gradePoints = 4;
                } else if (grade.equals("F") || grade.equals("AB")) {
                    gradePoints = 0;
                } else {
                    System.out.println("You didn't enter a valid grade");
                    validGrade = false;
                }
            } while (validGrade != true);
            Integer points = gradePoints * credit;
            totalCredits += credit;
            totalPoints += points;
        }
        // Calculate GPA
        DecimalFormat df = new DecimalFormat("0.0");
        Double gpa = Double.valueOf(totalPoints) / Double.valueOf(totalCredits);
        JOptionPane.showMessageDialog(this, "Your GPA is: " + gpa + "\n" + "Total Points" + totalPoints + "\n" + "Total Credits" + totalCredits);
    }
            public static void main(String[] args) {
                calGpa gpaCalculator = new calGpa();
            }
    }
