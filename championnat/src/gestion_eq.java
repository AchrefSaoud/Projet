import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class gestion_eq {
	public void show() {
		    JTextField t1,t2,t3,t4,t6;
		    JLabel title1=new JLabel("Ajouter equipe");
		    title1.setBounds(10,10,210,50);
		    title1.setFont(new Font("TimesRoman",50,23));
		    JFrame f= new JFrame("Gestion equipe");    
		    JLabel l1=new JLabel("id_Equipe");
		    l1.setBounds(10,100,400,30);
		    JLabel l2=new JLabel("NOM Equipe");
		    l2.setBounds(10,150,400,30);
		    t1=new JTextField();  
		    t1.setBounds(250,100, 400,30);  
		    t2=new JTextField();  
		    t2.setBounds(250,150, 400,30);
		    JLabel title2=new JLabel("Modifier equipe");
		    title2.setBounds(10,250,210,50);
		    title2.setFont(new Font("TimesRoman",50,23));
		    JLabel l3=new JLabel("NOM Equipe");
		    l3.setBounds(10,300,400,30);
		    t3=new JTextField();  
		    t3.setBounds(250,300,400,30);
		    JLabel l4=new JLabel("Id_Equipe");
		    l4.setBounds(10,350,400,30);
		    t4=new JTextField();  
		    t4.setBounds(250,350,400,30);   
		    JButton c=new JButton("Create Team");
		    c.setBounds(250, 200, 200, 60);
		    JButton c2=new JButton("Cancel");
		    c2.setBounds(450, 200, 200, 60);
		    JButton c3=new JButton("cancel");
		    c3.setBounds(450, 400, 200, 60);
		    JButton c4=new JButton("Modify Team");
		    c4.setBounds(250, 400, 200, 60);
		    JLabel title3=new JLabel("Supprimer equipe");
		    title3.setBounds(10,450,210,50);
		    title3.setFont(new Font("TimesRoman",50,23));
		    JLabel l6=new JLabel("Id_Equipe");
		    l6.setBounds(10,500,400,30);
		    t6=new JTextField();  
		    t6.setBounds(250,500,400,30);   
		    JButton c5=new JButton("cancel");
		    c5.setBounds(450, 550, 200, 60);
		    JButton c6=new JButton("Delete Team");
		    c6.setBounds(250, 550, 200, 60);
            JButton view_eqB=new JButton("View Equipe");
            view_eqB.setBounds(750, 300, 200, 60);
            view_eqB.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    view_eq a=new view_eq();
                    a.show();
                }
            });
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
            f.add(view_eqB);
		    f.setBounds(50, 200, 1000, 700);
		    f.setLayout(null);  
		    f.setVisible(true);

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
                            PreparedStatement pstmt=con.prepareStatement("INSERT INTO equipe (id_equipe , nom_equipe) VALUES ( ? , ?)");
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
					if(t4.getText().length()<=8 && isInteger(t4.getText())){      
                        try{  
                            Class.forName("com.mysql.cj.jdbc.Driver");  
                            Connection con=connection.getConnection();
                            PreparedStatement pstmt=con.prepareStatement("update equipe set nom_equipe=(?) where id_equipe=(?)");
                            pstmt.setInt(2,Integer.parseInt(t4.getText()));
                            pstmt.setString(1,t3.getText());
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
                            PreparedStatement pstmt=con.prepareStatement("delete from equipe where id_equipe=(?)");
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
