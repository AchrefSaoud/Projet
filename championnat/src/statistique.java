import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class statistique extends JFrame {

    private JTable table1, table2, table3;
    int id;
    public statistique(int id) {
        this.id=id;
        setTitle("Championnat");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        // create data for the tables
        String[][] butteur =new String[100][3];

        String[][] lig_t= new String[100][3];

        String[][] assissteur= new String[100][3];

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=connection.getConnection();
            PreparedStatement ptsmt=con.prepareStatement("SELECT j.nom_joueur, COUNT(*) AS goals FROM joueur j JOIN but b ON j.id_joueur = b.id_butteur where b.id_championnat=? GROUP BY j.id_joueur, j.nom_joueur, j.prénom_joueur ORDER BY goals DESC;");
            ptsmt.setInt(1,id);
            ResultSet rs=ptsmt.executeQuery();
            int i=0;
            int j=1;
            while(rs.next()) {
                butteur[i][0]=j+"";
                butteur[i][1]=rs.getString(1);
                butteur[i][2]=rs.getString(2);
                i++;
                j+=1;
            }
            rs.close();
            con.close();
        } catch (SQLException | ClassNotFoundException er){
            System.out.println(er);
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=connection.getConnection();
            PreparedStatement ptsmt=con.prepareStatement("call classify_teams_by_total_goals(?);");
            ptsmt.setInt(1,id);
            ResultSet rs=ptsmt.executeQuery();
            int i=0;
            int j=1;
            while(rs.next()) {
                lig_t[i][0]=j+"";
                lig_t[i][1]=rs.getString(1);
                lig_t[i][2]=rs.getString(2);
                i++;
                j+=1;
            }
            rs.close();
            con.close();
        } catch (SQLException | ClassNotFoundException er){
            System.out.println(er);
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=connection.getConnection();
            PreparedStatement ptsmt=con.prepareStatement("SELECT j.nom_joueur, COUNT(*) AS assists FROM joueur j JOIN but b ON j.id_joueur = b.id_assisteur  where id_championnat=(?) GROUP BY j.id_joueur, j.nom_joueur, j.prénom_joueur ORDER BY assists DESC;");
            ptsmt.setInt(1,id);
            ResultSet rs=ptsmt.executeQuery();
            int i=0;
            int j=1;
            while(rs.next()) {
                assissteur[i][0]=j+"";
                assissteur[i][1]=rs.getString(1);
                assissteur[i][2]=rs.getString(2);
                i++;
                j+=1;
            }
            rs.close();
            con.close();
        } catch (SQLException | ClassNotFoundException er){
            System.out.println(er);
        }
        table1 = new JTable(butteur, new String[]{"Classement", "Nom du joueur", "Buts marqués"});
        table1.setEnabled(false); 
        table2 = new JTable(assissteur, new String[]{"Classement", "Nom du joueur", "Passes décisives"});
        table2.setEnabled(false);
        table3 = new JTable(lig_t, new String[]{"Classement", "Nom de l'équipe", "total but"});
        table3.setEnabled(false);

        // add the tables to the frame
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(createTablePanel(table3, "Tableau de la ligue"));
        panel.add(createTablePanel(table1, "Meilleurs buteurs"));
        panel.add(createTablePanel(table2, "Meilleurs passeurs"));
        
        add(panel);

        setVisible(true);
    }

    private JPanel createTablePanel(JTable table, String title) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(title));
        panel.add(new JScrollPane(table));
        return panel;
    }

}