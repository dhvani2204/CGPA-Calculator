import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class signUp extends JDialog {

    private JTextField tfName;
    private JTextField tfPrn;
    private JTextField tfBranch;
    private JTextField tfBatch;
    private JPasswordField pF;
    private JPasswordField pF2;
    private JButton signUpButton;
    private JButton cancelButton;
    private javax.swing.JPanel JPanel;


    public signUp(ActionListener  backActionListener) {
        //super((Frame)parent);
        setTitle("Create a new account");
        setContentPane(JPanel);
        setMinimumSize(new Dimension(500,600));
        setModal(true);
        //setLocationRelativeTo((Component) parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        JButton backButton = new JButton("Back");
        backButton.addActionListener(backActionListener);
        //add(backButton, BorderLayout.PAGE_END);

        setVisible(true);
    }

    private void registerUser() {
        String name = tfName.getText();
        String prn = tfPrn.getText();
        String branch = tfBranch.getText();
        String batch = tfBatch.getText();
        String password = String.valueOf(pF.getPassword());
        String confirmPassword = String.valueOf(pF2.getPassword());

        if (name.isEmpty() || prn.isEmpty() || branch.isEmpty() || batch.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter all fields", "Try again", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(!password.equals(confirmPassword)){
            JOptionPane.showMessageDialog(this,"Confirm Password does not match","Try again", JOptionPane.ERROR_MESSAGE);
            return;
        }
        user= addUserToDatabase(name,prn,branch,batch,password);
        if(user!=null){
            if(user!=null){
                JOptionPane.showMessageDialog(this,
                        "New user:"+user.name,
                        "Successful Registration",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Failed to register new user", "Try again", JOptionPane.ERROR_MESSAGE);
        }
    }
public User user;
    private User addUserToDatabase(String name, String prn, String branch, String batch, String password) {
      User user=null;
      final String DB_URL="jdbc:mysql://localhost:3306/calc?serverTimezone=UTC";
      final String USERNAME="root";
      final String PASSWORD="DhvaniP@22";

      try{
          Connection conn= DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);

          Statement stmt=conn.createStatement();
          String sql="INSERT INTO users(name,prn,branch,batch,password)"+"VALUES(?,?,?,?,?)";
          PreparedStatement preparedStatement=conn.prepareStatement(sql);
          preparedStatement.setString(1,name);
          preparedStatement.setString(2,prn);
          preparedStatement.setString(3,branch);
          preparedStatement.setString(4,batch);
          preparedStatement.setString(5,password);

          int addedRows=preparedStatement.executeUpdate();
          if(addedRows>0){
              user=new User();
              user.name=name;
              user.prn=prn;
              user.branch=branch;
              user.batch=batch;
              user.password=password;
          }
          stmt.close();
          conn.close();
      }catch(Exception e){
          e.printStackTrace();
      }
      return user;
    }

    public static void main (String[]args){
            signUp myForm = new signUp(null);
            User user=myForm.user;
            if(user!=null){
                System.out.println("Successful registration of:"+user.name);
            }
            else{
                System.out.println("registration canceled");
            }
        }

}
