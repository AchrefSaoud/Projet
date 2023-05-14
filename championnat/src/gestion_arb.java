import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class gestion_arb {
	public void show() {
		    JTextField t1,t2,t3,t4,t6;
		    JLabel title1=new JLabel("Ajouter arbitre");
		    title1.setBounds(10,10,210,50);
		    title1.setFont(new Font("TimesRoman",50,23));
		    JFrame f= new JFrame("Gestion arbitre");    
		    JLabel l1=new JLabel("id_arbitre");
		    l1.setBounds(10,100,400,30);
		    JLabel l2=new JLabel("NOM arbitre");
		    l2.setBounds(10,150,400,30);
		    t1=new JTextField();  
		    t1.setBounds(250,100, 400,30);  
		    t2=new JTextField();  
		    t2.setBounds(250,150, 400,30);
		    JLabel title2=new JLabel("Modifier arbitre");
		    title2.setBounds(10,250,210,50);
		    title2.setFont(new Font("TimesRoman",50,23));
		    JLabel l3=new JLabel("Id_arbitre");
		    l3.setBounds(10,300,400,30);
		    t3=new JTextField();  
		    t3.setBounds(250,300,400,30);
		    JLabel l4=new JLabel("NOM arbitre");
		    l4.setBounds(10,350,400,30);
		    t4=new JTextField();  
		    t4.setBounds(250,350,400,30);   
		    JButton c=new JButton("ajouter arbitre");
		    c.setBounds(250, 200, 200, 60);
		    JButton c2=new JButton("Cancel");
		    c2.setBounds(450, 200, 200, 60);
		    JButton c3=new JButton("cancel");
		    c3.setBounds(450, 400, 200, 60);
		    JButton c4=new JButton("Modifier arbitre");
		    c4.setBounds(250, 400, 200, 60);
		    JLabel title3=new JLabel("Supprimer arbitre");
		    title3.setBounds(10,450,210,50);
		    title3.setFont(new Font("TimesRoman",50,23));
		    JLabel l6=new JLabel("Id_arbitre");
		    l6.setBounds(10,500,400,30);
		    t6=new JTextField();  
		    t6.setBounds(250,500,400,30);   
		    JButton c5=new JButton("cancel");
		    c5.setBounds(450, 550, 200, 60);
		    JButton c6=new JButton("supprimer arbitre");
		    c6.setBounds(250, 550, 200, 60);
            JButton view_arB=new JButton("View arbitre");
            view_arB.setBounds(750, 300, 200, 60);
		    f.add(title1);
		    f.add(title3);
		    f.add(l1);
		    f.add(t1);
		    f.add(l2);
		    f.add(t2);  
		    f.add(c);
		    f.add(c2);
		    f.add(c3);
		    f.add(c4);
		    f.add(c5);
		    f.add(c6);
		    f.add(title2);
		    f.add(l3);
		    f.add(t3);
		    f.add(l4);
		    f.add(t4);
		    f.add(t6);
		    f.add(l6);
            f.add(view_arB);
		    f.setBounds(50, 200, 1000, 700);
		    f.setLayout(null);  
		    f.setVisible(true);
			view_arB.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    view_arb a=new view_arb();
                    a.show();
					f.dispose();
                }
            });
			c2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
					t1.setText("");
					t2.setText("");
                }
            });
			c3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
					t3.setText("");
					t4.setText("");
                }
            });
			c5.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
					t6.setText("");
                }
            });
			c.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
					if(t1.getText().length()<=8 && isInteger(t1.getText())){      
                        try{  
                            Class.forName("com.mysql.cj.jdbc.Driver");  
                            Connection con=connection.getConnection();
                            PreparedStatement pstmt=con.prepareStatement("INSERT INTO arbitre (id_arbitre , nom_arbitre) VALUES ( ? , ?)");
                            pstmt.setInt(1,Integer.parseInt(t1.getText()));
                            pstmt.setString(2,t2.getText());
                            pstmt.executeUpdate();
                            System.out.println("vlaues inserted");
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

			c4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
					if(t3.getText().length()<=8 && isInteger(t3.getText())){      
                        try{  
                            Class.forName("com.mysql.cj.jdbc.Driver");  
                            Connection con=connection.getConnection();
                            PreparedStatement pstmt=con.prepareStatement("update arbitre set nom_arbitre=(?) where id_arbitre=(?)");
                            pstmt.setInt(2,Integer.parseInt(t3.getText()));
                            pstmt.setString(1,t4.getText());
                            pstmt.executeUpdate();
                            System.out.println("vlaues updated");
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

			c6.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
					if(t6.getText().length()<=8 && isInteger(t6.getText())){      
                        try{  
                            Class.forName("com.mysql.cj.jdbc.Driver");  
                            Connection con=connection.getConnection();
                            PreparedStatement pstmt=con.prepareStatement("delete from arbitre where id_arbitre=(?)");
                            pstmt.setInt(1,Integer.parseInt(t6.getText()));
                            pstmt.executeUpdate();
                            System.out.println("vlaues deleted");
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

