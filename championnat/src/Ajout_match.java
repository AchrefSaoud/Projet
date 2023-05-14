import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Ajout_match extends JFrame {

    public Ajout_match() {
        setTitle("Gestion du match");
        setPreferredSize(new Dimension(700, 450));
        setResizable(false);

        JLabel titleLabel = new JLabel("Ajouter un match", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel label6 = new JLabel("ID Championnat ");
        JTextField textField6 = new JTextField(30);
        JLabel label1 = new JLabel("Equipe locaux ");
        JTextField textField1 = new JTextField(30);

        JLabel label2 = new JLabel("Equipe visiteur ");
        JTextField textField2 = new JTextField(30);

        JLabel label3 = new JLabel("Arbitre principal ");
        JTextField textField3 = new JTextField(30);

        JLabel label4 = new JLabel("Arbitre de ligne ");
        JTextField textField4 = new JTextField(30);

        JLabel label5 = new JLabel("Arbitre videos ");
        JTextField textField5 = new JTextField(30);

        JButton submitButton = new JButton("Debut match");
        JButton cancelButton = new JButton("Anuller");

        JPanel panel = new JPanel();
        panel.setLayout(null);

        titleLabel.setBounds(30, 10, 630, 30);
        label6.setBounds(300, 60, 100, 30);
        textField6.setBounds(270, 100, 150, 30);

        label1.setBounds(20, 160, 100, 25);
        textField1.setBounds(120, 160, 150, 25);
        
        label2.setBounds(380, 160, 100, 25);
        textField2.setBounds(480, 160, 150, 25);
        
        label3.setBounds(180, 210, 100, 25);
        textField3.setBounds(280, 210, 150, 25);
        
        label4.setBounds(180, 260, 100, 25);
        textField4.setBounds(280, 260, 150, 25);
        
        label5.setBounds(180, 310, 100, 25);
        textField5.setBounds(280, 310, 150, 25);
        
        submitButton.setBounds(140, 360, 150, 35);
        cancelButton.setBounds(420, 360, 150, 35);
        
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    String id_champ=textField6.getText();
                    String eq1=textField1.getText();
                    String eq2=textField2.getText();
                    String arb1=textField3.getText();
                    String arb2=textField4.getText();
                    String arb3=textField5.getText();
                    if(id_champ.length()<=8 && isInteger(id_champ) &&!eq1.equals("") && !eq2.equals("") && arb1.length()<=8 && isInteger(arb1) && arb2.length()<=8 && isInteger(arb2)&&arb3.length()<=8 && isInteger(arb3)){
                        try {
                            int id_eq1=0,id_eq2=0;
                            Class.forName("com.mysql.cj.jdbc.Driver");  
                            Connection con=connection.getConnection();
                            PreparedStatement req_eq1=con.prepareStatement("select id_equipe from equipe where nom_equipe=(?)");
                            req_eq1.setString(1,eq1);
                            ResultSet res_eq1=req_eq1.executeQuery();
                            PreparedStatement req_eq2=con.prepareStatement("select id_equipe from equipe where nom_equipe=(?)");
                            req_eq2.setString(1,eq2);
                            ResultSet res_eq2=req_eq2.executeQuery();
                            if(res_eq1.next()){
                                id_eq1=res_eq1.getInt(1);
                            }
                            if(res_eq2.next()){
                                id_eq2=res_eq2.getInt(1);
                            }                            
                            System.out.println(id_eq1);
                            System.out.println(id_eq2);
                            req_eq1.close();
                            req_eq2.close();
                            PreparedStatement pstmt=con.prepareStatement("insert into match_football values (default,?,?,?,?,?)");
                            pstmt.setInt(1,Integer.parseInt(id_champ));
                            pstmt.setInt(2,id_eq1);
                            pstmt.setInt(3,id_eq2);
                            pstmt.setInt(4,0);
                            pstmt.setInt(5,0);
                            pstmt.executeUpdate();
                            pstmt.close();
                            JOptionPane.showMessageDialog(null, "Match en jeux");
                            PreparedStatement r_id_match=con.prepareStatement("select id_match from match_football where id_championnat=(?) and id_equipe_visiteur=(?) and id_equipe_locaux=(?)");
                            r_id_match.setInt(1,Integer.parseInt(id_champ));
                            r_id_match.setInt(2,id_eq1);
                            r_id_match.setInt(3,id_eq2);
                            ResultSet rs=r_id_match.executeQuery();
                            int id_match=0;
                            if(rs.next()){
                                id_match=rs.getInt(1);
                            }
                            System.out.println(id_match);
                            PreparedStatement a_arb1=con.prepareStatement("insert into observer values(?,?,?,?)");
                            a_arb1.setInt(1,Integer.parseInt(id_champ));
                            a_arb1.setInt(2,Integer.parseInt(arb1));
                            a_arb1.setInt(3,id_match);
                            a_arb1.setInt(4,1);
                            a_arb1.executeUpdate();   
                            a_arb1.close();                          
                            PreparedStatement a_arb2=con.prepareStatement("insert into observer values(?,?,?,?)");
                            a_arb2.setInt(1,Integer.parseInt(id_champ));
                            a_arb2.setInt(2,Integer.parseInt(arb2));
                            a_arb2.setInt(3,id_match);
                            a_arb2.setInt(4,2);     
                            a_arb2.executeUpdate();
                            a_arb2.close();      
                            PreparedStatement a_arb3=con.prepareStatement("insert into observer values(?,?,?,?)");
                            a_arb3.setInt(1,Integer.parseInt(id_champ));
                            a_arb3.setInt(2,Integer.parseInt(arb3));
                            a_arb3.setInt(3,id_match);
                            a_arb3.setInt(4,3);
                            a_arb3.executeUpdate();
                            a_arb3.close(); 
                            JOptionPane.showMessageDialog(null, "Stafe d arbitre ajouté");
                            Gestion_match a=new Gestion_match(eq1,eq2,id_match,Integer.parseInt(id_champ),id_eq1,id_eq2);
                            con.close();
                        } catch(SQLException | ClassNotFoundException er){
                            JOptionPane.showMessageDialog(null, "Operation failed");
                            System.out.println(er);
                        }

                    }else{
                        JOptionPane.showMessageDialog(null, "Verifier votre donnees");
                    }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                        textField1.setText("");
                        textField2.setText("");
                        textField3.setText("");
                        textField4.setText("");
                        textField5.setText("");
                        textField6.setText("");
            }
        });


        panel.add(titleLabel);
        panel.add(textField6);
        panel.add(label6);
        panel.add(label1);
        panel.add(textField1);
        panel.add(label2);
        panel.add(textField2);
        panel.add(label3);
        panel.add(textField3);
        panel.add(label4);
        panel.add(textField4);
        panel.add(label5);
        panel.add(textField5);
        panel.add(submitButton);
        panel.add(cancelButton);
        

        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public static boolean isInteger(String a) {
        try{
            Integer.parseInt(a);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}