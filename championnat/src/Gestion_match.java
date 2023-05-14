import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.security.cert.TrustAnchor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;


public class Gestion_match extends JFrame {
    String eq1;
    String eq2;
    int id_champ;
    int id_match;
    int id_eq1;
    int id_eq2;
    int score_eq1;
    int score_eq2;
    public Gestion_match(String eq1, String eq2,int id_match,int id_champ,int id_eq1, int id_eq2) {
        this.id_eq1=id_eq1;
        this.id_eq1=id_eq2;
        this.eq1=eq1;
        this.eq1=eq2;
        this.id_champ=id_champ;
        this.id_match=id_match;
        score_eq1=0;
        score_eq2=0;
        setTitle("Gestion le moments de match");
        setPreferredSize(new Dimension(650, 700));
        setResizable(false);

        JLabel titleLabel = new JLabel("Gestion le moments de match", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel label1 = new JLabel(eq1);
        label1.setFont(new Font("Arial", Font.BOLD, 15));
        JLabel label2 = new JLabel(eq2);
        label2.setFont(new Font("Arial", Font.BOLD, 15));

        JLabel goalLabel = new JLabel("But : ");
        goalLabel.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel scorerLabel = new JLabel("butteur");
        JLabel passerLabel = new JLabel("passeur");
        JLabel minuteLabel = new JLabel("Minute");
        JLabel equipeLabel2 = new JLabel("Equipe");

        JTextField scorerTextField = new JTextField(30);
        JTextField passerTextField = new JTextField(30);
        JTextField minuteTextField = new JTextField(30);
        JTextField equipeTextField2 = new JTextField(30);

        JButton addButton = new JButton("Ajouter");
        JButton cancelButton = new JButton("Annuler");

        JButton endMatchButton = new JButton("Terminer le match");

        JPanel panel = new JPanel();
        panel.setLayout(null);

        titleLabel.setBounds(-10, 10, 700, 30);

        label1.setBounds(50, 60, 150, 25);
        label2.setBounds(500, 60, 150, 25);

        goalLabel.setBounds(300, 100, 60, 25);

        scorerLabel.setBounds(50, 140, 150, 25);
        scorerTextField.setBounds(50, 170, 150, 25);

        passerLabel.setBounds(250, 140, 150, 25);
        passerTextField.setBounds(250, 170, 150, 25);

        minuteLabel.setBounds(450, 140, 150, 25);
        minuteTextField.setBounds(450, 170, 150, 25);
        equipeLabel2.setBounds(250, 210, 100, 25);
        equipeTextField2.setBounds(250, 240, 150, 25);

        addButton.setBounds(150, 290, 100, 25);
        cancelButton.setBounds(400, 290, 100, 25);


        endMatchButton.setBounds(220, 580, 200, 35);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ekteb lena akel code sql mta3 el button ajouter but
                String butteur=scorerTextField.getText();
                String passer=scorerTextField.getText();
                String minute=minuteTextField.getText();
                String eq_m=equipeTextField2.getText();
                if(isInteger(butteur) && isInteger(passer)&& butteur.length()<=8 && passer.length()<=8  && isInteger(minute) && (eq_m.equals("visiteur") ||eq_m.equals("locaux"))){
                    if(Integer.parseInt(minute)>=0 && Integer.parseInt(minute)<=100){
                        int id_eq_b;
                        if(eq_m.equals("visiteur")){
                            id_eq_b=id_eq2;
                        }else{
                            id_eq_b=id_eq1;
                        }
                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");  
                            Connection con=connection.getConnection();
                            PreparedStatement v_butteur=con.prepareStatement("select id_équipe from joue where id_joueur=?;");
                            v_butteur.setInt(1,Integer.parseInt(butteur));
                            ResultSet rs=v_butteur.executeQuery();
                            int eq_butt_choisi=0;
                            if(rs.next()){
                                eq_butt_choisi=rs.getInt(1);
                            }else{
                                JOptionPane.showMessageDialog(null, "butteur pas dans l equipe");                                
                            }
                            rs.close();
                            PreparedStatement v_assisteur=con.prepareStatement("select id_équipe from joue where id_joueur=?;");
                            v_assisteur.setInt(1,Integer.parseInt(passer));
                            ResultSet t=v_assisteur.executeQuery();
                            int eq_ass_choisi=0;
                            if(t.next()){
                                eq_ass_choisi=t.getInt(1);
                            }else{
                                JOptionPane.showMessageDialog(null, "assisteur pas dans l equipe");                                
                            }
                            t.close();
                            if(id_eq_b==eq_butt_choisi && id_eq_b==eq_ass_choisi){
                                System.out.println("zrfhazrp"+id_match);
                                PreparedStatement pstmt=con.prepareStatement("insert into But values(default,?,?,?,?,?)");
                                pstmt.setInt(1, id_match);
                                pstmt.setInt(2, id_champ);
                                pstmt.setInt(3, Integer.parseInt(butteur));
                                pstmt.setInt(4, Integer.parseInt(passer));
                                pstmt.setInt(5, Integer.parseInt(minute));
                                pstmt.executeUpdate();
                                if(id_eq_b==id_eq1){
                                    score_eq1++;
                                }else{
                                    score_eq2++;
                                }
                            }else{
                                JOptionPane.showMessageDialog(null, "Operation failed");
                            }
                            con.close();
                        } catch(SQLException | ClassNotFoundException er) {
                            JOptionPane.showMessageDialog(null, "Operation failed");
                            System.out.println(er);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Verifier votre donnees");                      
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Verifier votre donnees");
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scorerTextField.setText("");
                passerTextField.setText("");
                minuteTextField.setText("");
                equipeTextField2.setText("");
            }
        });

        endMatchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");  
                    Connection con=connection.getConnection();
                    PreparedStatement t=con.prepareStatement("UPDATE match_football set score_v=(?) , score_l=(?) where id_match=(?) ");
                    t.setInt(1,score_eq1);
                    t.setInt(2,score_eq2);
                    t.setInt(3,id_match);
                    t.executeUpdate();
                    t.close();
                    con.close();
                }catch(SQLException | ClassNotFoundException er) {
                    JOptionPane.showMessageDialog(null, "probleme de termine le match");
                    System.out.println(er);
                }
            }
        });



        panel.add(titleLabel);
        panel.add(label1);
        panel.add(label2);

        panel.add(goalLabel);

        panel.add(scorerLabel);
        panel.add(scorerTextField);

        panel.add(passerLabel);
        panel.add(passerTextField);

        panel.add(minuteLabel);
        panel.add(minuteTextField);

        panel.add(addButton);
        panel.add(cancelButton);

        panel.add(equipeLabel2);
        panel.add(equipeTextField2);

        panel.add(endMatchButton);

        add(panel);
        pack();
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