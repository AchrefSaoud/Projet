import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.*;
public class ajouter_eq_champion extends JFrame {
    int nombre_equipe;
    int id_ch;
    JFrame f;
    ajouter_eq_champion(int nombre_equipe,int id_ch){
        this.nombre_equipe=nombre_equipe;
        this.id_ch=id_ch;
    }
    public void show(){
        f=new JFrame();
        JTextField equipesIdfield[];
        equipesIdfield=new JTextField[nombre_equipe];
        JLabel labels[];
        labels=new JLabel[nombre_equipe];
        int y=20;
        int i=1;
        for (int j = 0; j < nombre_equipe; j++) {
            equipesIdfield[j]=new JTextField();
            labels[j]=new JLabel("id de Equipe"+i);
            i++;
        }
        for (int j = 0; j < nombre_equipe; j++) {
            equipesIdfield[j].setBounds(250,y,400,30);
            labels[j].setBounds(10,y,400,30);
            y+=50;
            f.add(equipesIdfield[j]);
            f.add(labels[j]);
        }
        JButton ajouter=new JButton("Ajouter");
        ajouter.setBounds(250, y, 200, 30);
        JButton cancel=new JButton("Cancel");   
        cancel.setBounds(450, y, 200, 30);  
        cancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                for (int j = 0; j<nombre_equipe;j++){
                    equipesIdfield[j].setText("");
                }    
            }
        });
        ajouter.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(allfieldvalide(equipesIdfield)){
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");  
                        Connection con=connection.getConnection();
                        for (int j = 0; j < nombre_equipe; j++) {
                            PreparedStatement ptsmt=con.prepareStatement("INSERT INTO jouer_au_championnat VALUES (?,?)");
                            ptsmt.setInt(2, id_ch);
                            ptsmt.setInt(1, Integer.parseInt(equipesIdfield[j].getText()));
                            ptsmt.executeUpdate();
                            ptsmt.close();
                        }
                        JOptionPane.showMessageDialog(null, "Les équipes ont été ajoutées avec succès au championnat");
                        f.dispose();
                        con.close();
                    } catch (SQLException | ClassNotFoundException er){
                        JOptionPane.showMessageDialog(null, "Operation Impossible");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "verifier votre donneés");
                }
            }
        });
        f.add(ajouter);  
        f.add(cancel);  
        f.setBounds(0, 0, 1000, 700);
        f.setLayout(null);  
        f.setVisible(true);   
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
    }
    public boolean allfieldvalide(JTextField t[]) {
        for(int i=0;i<t.length;i++){
            if(!isInteger(t[i].getText()) || t[i].getText().length()>8){
                return false;
            }
        }
        return true;
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