import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class adSignForm extends JDialog {
    private JTextField tfn1;
    private JTextField tfAd1;
    private JTextField tfD;
    private JPasswordField tFP2;
    private JPasswordField tfP1;
    private JButton btnS;
    private JButton btnCncl;
    private JPanel Jp;

    public adSignForm(ActionListener parent) {
        //super((Frame) parent);
        setTitle("Create a new account");
        setContentPane(Jp);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        //setLocationRelativeTo((Component) parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
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
    private void registerUser() {
        String name1 = tfn1.getText();
        String AdID = tfAd1.getText();
        String Dept = tfD.getText();
        String pswd = String.valueOf(tfP1.getPassword());
        String confirmPassword = String.valueOf(tFP2.getPassword());

        if (name1.isEmpty() || AdID.isEmpty() || Dept.isEmpty() || pswd.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter all fields", "Try again", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(!pswd.equals(confirmPassword)){
            JOptionPane.showMessageDialog(this,"Confirm Password does not match","Try again", JOptionPane.ERROR_MESSAGE);
            return;
        }
        user1= addUserToDatabase(AdID,name1,Dept,pswd);
        if(user1!=null){
            if(user1!=null){
                JOptionPane.showMessageDialog(this,
                        "New user:"+user1.name1,
                        "Successful Registration",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Failed to register new user", "Try again", JOptionPane.ERROR_MESSAGE);
        }
    }
    public User1 user1;
    private User1 addUserToDatabase(String AdID,String name1,String Dept, String pswd) {
        User1 user1=null;
        final String DB_URL="jdbc:mysql://localhost:3306/gpacalc?serverTimezone=UTC";
        final String USERNAME="root";
        final String PASSWORD="DhvaniP@22";

        try{
            Connection conn= DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);

            Statement stmt=conn.createStatement();
            String sql="INSERT INTO student(id,name,Department,password)"+"VALUES(?,?,?,?)";
            PreparedStatement preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setString(1,AdID);
            preparedStatement.setString(2,name1);
            preparedStatement.setString(3,Dept);
            preparedStatement.setString(4,pswd);

            int addedRows=preparedStatement.executeUpdate();
            if(addedRows>0){
                user1=new User1();
                user1.name1=name1;
                user1.AdID=AdID;
                user1.Dept=Dept;
                user1.pswd=pswd;
            }
            stmt.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return user1;
    }
    public static void main (String[]args){
        adSignForm myForm = new adSignForm(null);
        User1 user1=myForm.user1;
        if(user1!=null){
            System.out.println("Successful registration of:"+user1.name1);
        }
        else{
            System.out.println("registration canceled");
        }
    }
}
