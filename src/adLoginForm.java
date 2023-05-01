import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class adLoginForm extends JDialog{
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton loginButton;
    private JButton cancelButton;
    private javax.swing.JPanel JPanel;

    public adLoginForm(ActionListener parent) {
        //super((Frame) parent);
        setTitle("Login");
        setContentPane(JPanel);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        //setLocationRelativeTo((Component) parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String AdID= textField1.getText();
                String password=String.valueOf(passwordField1.getPassword());

                user1=getAuthenticateUser(AdID,password);
                if(user1!=null){
                    adminManage am=new adminManage();
                    am.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(adLoginForm.this,"ID or Password Invalid","Try again",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }
    public User1 user1;
    private User1 getAuthenticateUser(String AdID,String password){
        User1 user1=null;
        final String DB_URL="jdbc:mysql://localhost:3306/gpacalc?serverTimezone=UTC";
        final String USERNAME="root";
        final String PASSWORD="DhvaniP@22";
        try{
            Connection conn= DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Statement stmt=conn.createStatement();
            String sql="SELECT*FROM student WHERE id=? AND password=?";
            PreparedStatement preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setString(1,AdID);
            preparedStatement.setString(2,password);

            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                user1=new User1();
                user1.name1=resultSet.getString("name");
                user1.AdID=resultSet.getString("id");
                user1.Dept=resultSet.getString("Department");
                user1.pswd=resultSet.getString("password");
            }
            stmt.close();
            conn.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return user1;
    }
    public static void main(String[] args){
        adLoginForm loginForm=new adLoginForm(null);
        User1 user1=loginForm.user1;
        if(user1!=null){
            System.out.println("Successful Authentication of:"+user1.name1);
            System.out.println("    Admin ID: "+ user1.AdID);
            System.out.println("    Department: "+user1.Dept);
        }
        else{
            System.out.println("Authentication cancelled");
        }
    }
}