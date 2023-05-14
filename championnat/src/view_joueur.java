import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
public class view_joueur{
    public void show() {
        JFrame f=new JFrame(); 
        try{  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=connection.getConnection();
            int numberofrow=0;
            PreparedStatement req=con.prepareStatement("select count(*) from joueur");
            String[] columnnames={"id joueur","nom joueur","Prenom","Poste","Date naissance","nom equipe"};
            ResultSet r_count=req.executeQuery();
            String[][] data;
            if(r_count.next()){
                numberofrow=r_count.getInt(1);
            }else{
                numberofrow=0;
            }
            req.close();
            PreparedStatement pstmt=con.prepareStatement("SELECT jou.id_joueur,jou.nom_joueur,jou.prénom_joueur,jou.poste,jou.date_naissance,e.nom_equipe from joueur as jou , equipe as e,joue as j where(jou.id_joueur=j.id_joueur)and(e.id_equipe=j.id_équipe);");
            ResultSet rs=pstmt.executeQuery();
            data=new String[numberofrow][8];
            int i=0;
            while(rs.next()){
                data[i][0]=rs.getString(1);
                data[i][1]=rs.getString(2);
                data[i][2]=rs.getString(3);
                data[i][3]=rs.getString(4);
                data[i][4]=rs.getString(5);
                data[i][5]=rs.getString(6);
                i++;
            }
            JTable t=new JTable(data,columnnames);
            JScrollPane sp = new JScrollPane(t);
            f.setTitle("Voir Equipe");
            f.setBounds(50, 200, 1000, 700);
            f.add(sp);
            f.setVisible(true);
            System.out.println("vlaues selected");
            pstmt.close();
            con.close();
        }catch(SQLException | ClassNotFoundException er){
            System.out.println(er);
        }
    }
}
