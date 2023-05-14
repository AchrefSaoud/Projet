import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.*;

public class gestion_com extends JFrame implements ActionListener {

    private JPanel navPanel, contentPanel, addPanel, modifyPanel, deletePanel;
    private JButton addButton, modifyButton, deleteButton,ViewButton;
    
    public gestion_com() {
        setTitle("Gestion Championnat");
        setSize(580, 500);
        setLayout(new BorderLayout());

        navPanel = new JPanel();
        navPanel.setLayout(new GridLayout(4, 1, 0, 10));
        navPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 20)); 

        addButton = new JButton("Ajouter Championnat");
        addButton.addActionListener(this);
        navPanel.add(addButton);
        modifyButton = new JButton("Modifier Championnat");
        modifyButton.addActionListener(this);
        navPanel.add(modifyButton);

        deleteButton = new JButton("Supprimer Championnat");
        deleteButton.addActionListener(this);
        navPanel.add(deleteButton);

        ViewButton = new JButton("Voir Championnat");
        ViewButton.addActionListener(this);
        navPanel.add(ViewButton);
        add(navPanel, BorderLayout.WEST);
            contentPanel = new JPanel();
                contentPanel.setLayout(new CardLayout());
                contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 20));

                addPanel = new JPanel();
                addPanel.setLayout(new GridLayout(6, 2,5,30)); 

                addPanel.add(new JLabel("ID Championnat:"));
                JTextField addTextFieldID = new JTextField(20);
                addPanel.add(addTextFieldID);
                
                addPanel.add(new JLabel("Nom Championnat:"));
                JTextField addTextFieldName = new JTextField(20);
                addPanel.add(addTextFieldName);
                
                addPanel.add(new JLabel("Date debut:"));
                JTextField addTextFieldStartDate = new JTextField(20);
                addPanel.add(addTextFieldStartDate);
                
                addPanel.add(new JLabel("Date fin:"));
                JTextField addTextFieldEndDate = new JTextField(20);
                addPanel.add(addTextFieldEndDate);
                
                addPanel.add(new JLabel("Nombre d'equipes:"));
                JTextField addTextFieldNumteams = new JTextField(20);
                addPanel.add(addTextFieldNumteams);

                JButton submitButton = new JButton("Ajouter Championnat");
                /**********************************Insert the championship data to the db**************************** */
                submitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {  
                        try {
                            String id=addTextFieldID.getText();
                            String nom=addTextFieldName.getText();
                            String date_debut=addTextFieldStartDate.getText();
                            String date_fin=addTextFieldEndDate.getText();
                            String nombre_equipe=addTextFieldNumteams.getText();
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                            java.util.Date date_d=formatter.parse(date_debut);
                            java.util.Date date_f=formatter.parse(date_fin); 
                            int n_eq;
                            n_eq=isInteger_r(nombre_equipe);
                            if( n_eq>=1 &&n_eq%2==0 && n_eq<=12 && id.length()<=8 && isInteger(id) && !nom.equals("") && date_f.after(date_d)){
                                try {
                                    Class.forName("com.mysql.cj.jdbc.Driver");  
                                    Connection con=connection.getConnection();
                                    PreparedStatement ptsmt=con.prepareStatement("INSERT INTO championnat VALUES (?,?,?,?)");
                                    ptsmt.setInt(1,Integer.parseInt(id));
                                    ptsmt.setString(2,nom);
                                    ptsmt.setString(3,date_debut);
                                    ptsmt.setString(4,date_fin);
                                    ptsmt.executeUpdate();
                                    ptsmt.close();
                                    con.close();
                                    JOptionPane.showMessageDialog(null, "Championnat ajouté");
                                    ajouter_eq_champion a=new ajouter_eq_champion(n_eq,Integer.parseInt(id));
                                    a.show();
                                } catch (SQLException | ClassNotFoundException er) {
                                    JOptionPane.showMessageDialog(null, "Operation Impossible"); 
                                    System.out.println(er);                                  
                                }
                            }else{
                                JOptionPane.showMessageDialog(null, "Operation Impossible");
                            } 
                        } catch (Exception exc) {
                            JOptionPane.showMessageDialog(null, "Date Invalide");
                        }

                    }
                });

                addPanel.add(submitButton);
                JButton cancelButton = new JButton("Annuler");
                addPanel.add(cancelButton);
                cancelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {    
                        addTextFieldID.setText("");
                        addTextFieldName.setText("");
                        addTextFieldStartDate.setText("");
                        addTextFieldEndDate.setText("");
                        addTextFieldNumteams.setText("");
                    }
                });



                contentPanel.add(addPanel, "Add");
            
                modifyPanel = new JPanel();
                modifyPanel.setLayout(new GridLayout(5, 2,5,50)); 

                modifyPanel.add(new JLabel("ID Championnat:"));
                JTextField modifyTextFieldID = new JTextField(20);
                modifyPanel.add(modifyTextFieldID);

                modifyPanel.add(new JLabel("Nouveau Nom Championnat:"));
                JTextField modifyTextFieldName = new JTextField(20);
                modifyPanel.add(modifyTextFieldName);

                modifyPanel.add(new JLabel("Nouveau Date debut:"));
                JTextField modifyTextFieldStartDate = new JTextField(20);
                modifyPanel.add(modifyTextFieldStartDate);

                modifyPanel.add(new JLabel("Nouveau Date fin:"));
                JTextField modifyTextFieldEndDate = new JTextField(20);
                modifyPanel.add(modifyTextFieldEndDate);

                JButton submitButton2 = new JButton("Modifier Championnat");
                modifyPanel.add(submitButton2);
                JButton cancelButton2 = new JButton("Annuler");
                modifyPanel.add(cancelButton2);

                contentPanel.add(modifyPanel, "Modify");

                submitButton2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {    
                        String id=modifyTextFieldID.getText();
                        String date_d=modifyTextFieldStartDate.getText();
                        String date_f=modifyTextFieldEndDate.getText();
                        String name=modifyTextFieldName.getText();
                        if(isInteger(id) && id.length()<=8){
                            if(!date_d.equals("") && !date_f.equals("")){
                                try{
                                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                                    java.util.Date d_d=formatter.parse(date_d);
                                    java.util.Date d_f=formatter.parse(date_f); 
                                    if(d_d.before(d_f)){
                                        try {
                                            Class.forName("com.mysql.cj.jdbc.Driver");  
                                            Connection con=connection.getConnection();
                                            PreparedStatement ptsmt=con.prepareStatement("Update championnat set date_debut=(?),date_fin=(?) where id_championnat=(?)");
                                            ptsmt.setString(1,date_d);
                                            ptsmt.setString(2,date_f);
                                            ptsmt.setInt(3,Integer.parseInt(id));
                                            ptsmt.executeUpdate();
                                            ptsmt.close();
                                            con.close();       
                                            JOptionPane.showMessageDialog(null, "Les données ont été mises à jour avec succès");    
                                        } catch (SQLException | ClassNotFoundException er) {
                                            JOptionPane.showMessageDialog(null, "Verifier votre donnees");  
                                            System.out.println(er);
                                        }
                                    }else{
                                        JOptionPane.showMessageDialog(null, "Verifier votre donnees");                                            
                                    }
                                }catch(Exception er){
                                    JOptionPane.showMessageDialog(null, "Date Invalide");      
                                }
                            }
                            if(!name.equals("")){
                                try {
                                    Class.forName("com.mysql.cj.jdbc.Driver");  
                                    Connection con=connection.getConnection();
                                    PreparedStatement ptsmt=con.prepareStatement("Update championnat set nom_championnat=(?) where id_championnat=(?)");
                                    ptsmt.setString(1,name);
                                    ptsmt.setInt(2,Integer.parseInt(id));
                                    ptsmt.executeUpdate();
                                    ptsmt.close();
                                    con.close();   
                                     JOptionPane.showMessageDialog(null, "Les données ont été mises à jour avec succès");      
                                } catch (SQLException | ClassNotFoundException er) {
                                    JOptionPane.showMessageDialog(null, "Verifier votre donnees");  
                                    System.out.println(er);
                                }
                            }

                        }else{
                            JOptionPane.showMessageDialog(null, "Verifier votre donnees");                            
                        }
                    }
                });

                cancelButton2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {    
                        modifyTextFieldID.setText("");
                        modifyTextFieldStartDate.setText("");
                        modifyTextFieldEndDate.setText("");
                        modifyTextFieldName.setText("");
                    }
                });

                deletePanel = new JPanel();
                
                deletePanel.setLayout(new GridLayout(2, 2,5,340)); 

                deletePanel.add(new JLabel("ID Championnat:"));
                JTextField deleteTextFieldID = new JTextField(20);
                deletePanel.add(deleteTextFieldID);

                JButton deleteButton = new JButton("Supprimer Championnat");
                deletePanel.add(deleteButton);
                JButton cancelButton3 = new JButton("Annuler");
                deletePanel.add(cancelButton3);

                contentPanel.add(deletePanel, "Delete");

                cancelButton3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {    
                        deleteTextFieldID.setText("");
                    }
                });
                deleteButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {    
                        if(isInteger(deleteTextFieldID.getText()) && deleteTextFieldID.getText().length()<=8){      
                            try{  
                                Class.forName("com.mysql.cj.jdbc.Driver");  
                                Connection con=connection.getConnection();
                                PreparedStatement pstmt=con.prepareStatement("delete from championnat where id_championnat=(?)");
                                pstmt.setInt(1,Integer.parseInt(deleteTextFieldID.getText()));
                                pstmt.executeUpdate();
                                pstmt.close();
                                con.close();
                                JOptionPane.showMessageDialog(null, "Le championnat a été supprimé avec succès");
                            }catch(SQLException | ClassNotFoundException er){
                                JOptionPane.showMessageDialog(null, "Verifier votre donnees");
                            }

                    }else{
                        JOptionPane.showMessageDialog(null, "Verifier votre donnees");
                    }
                    }
                });

                JPanel viewPanel = new JPanel();
                viewPanel.setLayout(new GridLayout(2, 2,5,340)); 

                viewPanel.add(new JLabel("ID Championnat:"));
                JTextField viewTextFieldID = new JTextField(20);
                viewPanel.add(viewTextFieldID);

                JButton viewButton = new JButton("Voir Championnat");
                viewPanel.add(viewButton);
                JButton cancelButton4 = new JButton("Annuler");
                viewPanel.add(cancelButton4);

                contentPanel.add(viewPanel, "View");
                add(contentPanel, BorderLayout.CENTER);
                setVisible(true);

                viewButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {  
                            statistique a=new statistique(Integer.parseInt(viewTextFieldID.getText()));
                    }
                });
        }

    public void actionPerformed(ActionEvent e) {
        CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
        if (e.getSource() == addButton) {
            cardLayout.show(contentPanel, "Add");
        } else if (e.getSource() == modifyButton) {
            cardLayout.show(contentPanel, "Modify");
        } else if (e.getSource() == deleteButton) {
            cardLayout.show(contentPanel, "Delete");
        } else if (e.getSource() == ViewButton) {
            cardLayout.show(contentPanel, "View");
        }
    }  
    public static boolean isInteger(String a) {
        try{
            Integer.parseInt(a);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static int isInteger_r(String a) {
        try{
            Integer.parseInt(a);
            return Integer.parseInt(a);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
