import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class loginForm extends JDialog {
    private JTextField tFprn;
    private JPasswordField pFL;
    private JButton btnOk;
    private JButton btnCancel;
    private JPanel loginPanel;
    private JComboBox cb1;
    private JComboBox cb2;

    public loginForm(ActionListener parent){
    //super((Frame) parent);
    setTitle("Login");
    setContentPane(loginPanel);
    setMinimumSize(new Dimension(550,600));
    setModal(true);
    //setLocationRelativeTo((Component) parent);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    btnOk.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String prn= tFprn.getText();
            String password=String.valueOf(pFL.getPassword());

            user=getAuthenticateUser(prn,password);
            if(user!=null){
                String branch = cb2.getSelectedItem().toString();
                String semester = cb1.getSelectedItem().toString();
                sPage s=new sPage(branch,semester);
                s.setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(loginForm.this,"Prn or Password Invalid","Try again",JOptionPane.ERROR_MESSAGE);
            }
        }
    });
    btnCancel.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    });
    setVisible(true);
}
public User user;
private User getAuthenticateUser(String prn,String password){
    User user=null;
    final String DB_URL="jdbc:mysql://localhost:3306/calc?serverTimezone=UTC";
    final String USERNAME="root";
    final String PASSWORD="DhvaniP@22";
    try{
        Connection conn= DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
        Statement stmt=conn.createStatement();
        String sql="SELECT*FROM users WHERE prn=? AND PASSWORD=?";
        PreparedStatement preparedStatement=conn.prepareStatement(sql);
        preparedStatement.setString(1,prn);
        preparedStatement.setString(2,password);

        ResultSet resultSet=preparedStatement.executeQuery();
        if(resultSet.next()){
            user=new User();
            user.name=resultSet.getString("name");
            user.prn=resultSet.getString("prn");
            user.branch=resultSet.getString("branch");
            user.batch=resultSet.getString("batch");
            user.password=resultSet.getString("password");
        }
        stmt.close();
        conn.close();
    }
    catch(Exception e){
        e.printStackTrace();
    }
    return user;
}
public static void main(String[] args){
    loginForm loginForm=new loginForm(null);
    User user=loginForm.user;
    if(user!=null){
        System.out.println("Successful Authentication of:"+user.name);
        System.out.println("    Prn: "+user.prn);
        System.out.println("    Branch: "+user.branch);
        System.out.println("    Batch: "+user.batch);
    }
else{
        System.out.println("Authentication cancelled");
    }
}
}