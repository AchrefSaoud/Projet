import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
public class view_eq {
    public void show() {
        JFrame f=new JFrame(); 
        String[] columnnames={"id equipe","nom equipe"};
        try{  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con=connection.getConnection();
            int numberofrow=0;
            PreparedStatement req=con.prepareStatement("select count(*) from equipe");
            ResultSet r_count=req.executeQuery();

            if(r_count.next()){
                numberofrow=r_count.getInt(1);
            }else{
                numberofrow=0;
            }
            req.close();
            PreparedStatement pstmt=con.prepareStatement("select * from equipe");
            ResultSet rs=pstmt.executeQuery();
            String[][] data=new String[numberofrow][2];
            int i=0;
            while(rs.next()){
                data[i][0]=rs.getString(1);
                data[i][1]=rs.getString(2);
                i++;
            }
            JTable t=new JTable(data,columnnames);
            JScrollPane sp = new JScrollPane(t);
            f.setTitle("view Equipe");
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
