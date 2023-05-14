import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.*;
public class c_account extends JFrame{
    JTextField t1,t4;
    JPasswordField t2,t3;
    JButton login;
    JButton cancel;
    JButton c;
    public void show(){
        JLabel title=new JLabel("Create account");
        title.setBounds(10,10,210,50);
        title.setFont(new Font("TimesRoman",50,23));
        JFrame f= new JFrame("Create account");    
        JLabel l1=new JLabel("id_User");
        l1.setBounds(10,100,400,30);
        JLabel l2=new JLabel("password");
        l2.setBounds(10,150,400,30);
        JLabel l3=new JLabel("confirm password");
        l3.setBounds(10,200,400,30);
        JLabel l4=new JLabel("type");
        l4.setBounds(10,250,400,30);
        t1=new JTextField();  
        t1.setBounds(250,100, 400,30);  
        t2=new JPasswordField();  
        t2.setBounds(250,150, 400,30);  
        t3=new JPasswordField();  
        t3.setBounds(250,200, 400,30); 
        t4=new JTextField();
        t4.setBounds(250,250, 400,30);
        JButton c=new JButton("Create");
        c.setBounds(250, 300, 200, 60);
        JButton cancel=new JButton("Cancel");
        cancel.setBounds(450, 300, 200, 60);
        f.add(title);
        f.add(l1);
        f.add(t1);
        f.add(l2);
        f.add(t2);  
        f.add(l3);
        f.add(t3);
        f.add(c);
        f.add(l4);
        f.add(t4);
        f.add(cancel);
        f.setBounds(50, 200, 1000, 700);
        f.setLayout(null);  
        f.setVisible(true);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t1.setText("");
                t2.setText("");
                t3.setText("");
                t4.setText("");
            }
        });     
        c.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(verify()){      
                        try{  
                            Class.forName("com.mysql.jdbc.Driver");  
                            Connection con=connection.getConnection();
                            PreparedStatement pstmt=con.prepareStatement("INSERT INTO utilisateur (id_utilisateur, mot_passe,type) VALUES (?, ?,?)");
                            pstmt.setInt(1,Integer.parseInt(t1.getText()));
                            pstmt.setString(2,new String(t2.getPassword()));
                            pstmt.setString(3,t4.getText());
                            pstmt.executeUpdate();
                            System.out.println("vlaues inserted");
                            pstmt.close();
                            con.close();
                            auth a=new auth();
                            a.show();
                            f.dispose();
                        }catch(SQLException | ClassNotFoundException er){
                            System.out.println(er);
                        }
                }else{
                    JOptionPane.showMessageDialog(null, "Verifier votre donnees");
                }
        }
        });
    }
    public boolean isInteger(String a) {
        try{
            Integer.parseInt(a);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public boolean verify() {
        String id=t1.getText();
        String pass1=new String(t2.getPassword());
        String pass2=new String(t3.getPassword());
        String type=t4.getText();
        if(id.length()>8){
            return false;
        }
        if(!isInteger(id)){
            return false;
        }
        if(!pass1.equals(pass2)){
            return false;
        }
        if(!type.equals("admin") && !type.equals("user")){
            return false;
        }
        return true;
    }
}