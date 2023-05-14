import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.*;
public class auth extends JFrame{
    static JTextField t1;
    static JPasswordField t2;
    JButton login;
    JButton cancel;
    JButton c_account;
    public void show(){
        JLabel title=new JLabel("Gestion chompiannats");
        title.setBounds(10,10,210,50);
        title.setFont(new Font("TimesRoman",50,23));
        JFrame f= new JFrame("Login");  
        JTextField t1,t2;  
        JLabel l1=new JLabel("id_User");
        l1.setBounds(10,100,400,30);
        JLabel l2=new JLabel("password");
        l2.setBounds(10,150,400,30);
        t1=new JTextField();  
        t1.setBounds(250,100, 400,30);  
        t2=new JPasswordField();  
        t2.setBounds(250,150, 400,30);  
        JButton login=new JButton("Login");
        login.setBounds(250, 200, 200, 60);
        JButton cancel=new JButton("Cancel");
        cancel.setBounds(450, 200, 200, 60);
        JButton c_account=new JButton("Create account");
        c_account.setBounds(250, 270, 400, 60);
        f.add(title);
        f.add(l1);
        f.add(t1);
        f.add(l2);
        f.add(t2);  
        f.add(login);
        f.add(cancel);
        f.add(c_account);
        f.setBounds(50, 200, 900, 600);
        f.setLayout(null);  
        f.setVisible(true);    
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t1.setText("");
                t2.setText("");
            }
        });
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(t1.getText().length()<=8 && isInteger(t1.getText())){
                    try{  
                        Class.forName("com.mysql.cj.jdbc.Driver");  
                        Connection con=connection.getConnection();
                        PreparedStatement pstmt=con.prepareStatement("SELECT * FROM championnat.utilisateur where id_utilisateur=(?) and mot_passe=(?)");
                        pstmt.setInt(1, Integer.parseInt(t1.getText()));
                        pstmt.setString(2, new String(((JPasswordField) t2).getPassword()));
                        ResultSet rs=pstmt.executeQuery();
                        if(rs.next()){
                              if(rs.getString(3).equals("admin")){
                                admin ad=new admin();
                                ad.show();
                              }else{
                                simple_user ad=new simple_user();
                                ad.show();
                              }
                              f.dispose();
                        }else{
                            JOptionPane.showMessageDialog(null, "C'est compte n est pas valide");
                        }
                        pstmt.close();
                        con.close();
                    }catch(SQLException | ClassNotFoundException er){
                        System.out.println(er);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Verifier votre donnees");
                }
            }
        });
        c_account.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c_account a=new c_account();
                a.show();
                f.dispose();
            }
        });
    }
    public static boolean isInteger(String a) {
        try{
            Integer.parseInt(a);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static void main(String[] args) {
        auth a=new auth();
        a.show();
    }
}