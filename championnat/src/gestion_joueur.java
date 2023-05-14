import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.swing.*;
public class gestion_joueur extends JFrame implements ActionListener {

    private JPanel navPanel, contentPanel, addPanel, modifyPanel, deletePanel;
    private JButton addButton, modifyButton, deleteButton,ViewButton;
    
    public gestion_joueur() {
        setTitle("Gestion Joueur");
        setSize(580, 500);
        setLayout(new BorderLayout());

        navPanel = new JPanel();
        navPanel.setLayout(new GridLayout(4, 1, 0, 10));
        navPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 20)); 

        addButton = new JButton("Ajouter Joueur");
        addButton.addActionListener(this);
        navPanel.add(addButton);
        modifyButton = new JButton("Modifier Joueur");
        modifyButton.addActionListener(this);
        navPanel.add(modifyButton);

        deleteButton = new JButton("Supprimer Joueur");
        deleteButton.addActionListener(this);
        navPanel.add(deleteButton);



        ViewButton = new JButton("Voir Joueur");
        ViewButton.addActionListener(this);
        navPanel.add(ViewButton);
        add(navPanel, BorderLayout.WEST);
            contentPanel = new JPanel();
                contentPanel.setLayout(new CardLayout());
                contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 20));

                addPanel = new JPanel();
addPanel.setLayout(new GridLayout(7, 2, 5, 30)); 

addPanel.add(new JLabel("ID Joueur:"));
JTextField addTextFieldID = new JTextField(20);
addPanel.add(addTextFieldID);

addPanel.add(new JLabel("Nom Joueur:"));
JTextField addTextFieldName = new JTextField(20);
addPanel.add(addTextFieldName);

addPanel.add(new JLabel("Prenom Joueur:"));
JTextField addTextFieldStartDate = new JTextField(20);
addPanel.add(addTextFieldStartDate);

addPanel.add(new JLabel("Date de naissance:"));
JTextField addTextFieldEndDate = new JTextField(20);
addPanel.add(addTextFieldEndDate);

addPanel.add(new JLabel("Position joueur:"));
JTextField addTextFieldPosition = new JTextField(20);
addPanel.add(addTextFieldPosition);

addPanel.add(new JLabel("ID Equipe:"));
JTextField addTextFieldTeamID = new JTextField(20);
addPanel.add(addTextFieldTeamID);

JButton submitButton = new JButton("Ajouter Joueur");
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
        addTextFieldPosition.setText("");
        addTextFieldTeamID.setText("");
    }
});
submitButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String id=addTextFieldID.getText();
        String nom=addTextFieldName.getText();
        String prenom=addTextFieldStartDate.getText();
        String date=addTextFieldEndDate.getText();
        String position=addTextFieldPosition.getText();
        String id_equipe=addTextFieldTeamID.getText();
        if((id_equipe.length()<=8 && isInteger(id_equipe))&&(id.length()<=8 &&isInteger(id))&& (!nom.equals("")&&(!prenom.equals("")) && Isdate(date)&& !position.equals(""))){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");  
                Connection con=connection.getConnection();
                PreparedStatement pstmt=con.prepareStatement("INSERT INTO joueur VALUES (?,?,?,?,?)");
                pstmt.setInt(1, Integer.parseInt(id));
                pstmt.setString(2,nom);
                pstmt.setString(3,prenom);
                pstmt.setString(4,date);
                pstmt.setString(5,position);
                pstmt.executeUpdate();
                pstmt.close();
                PreparedStatement j=con.prepareStatement("INSERT INTO joue VALUES (?,?)");
                j.setInt(1, Integer.parseInt(id));
                j.setInt(2, Integer.parseInt(id_equipe));
                j.executeUpdate();
                j.close();
                JOptionPane.showMessageDialog(null, "Joueur ajouté");
                con.close();
            } catch (SQLException | ClassNotFoundException er) {
                System.out.println(er);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Verifier votre donnees");
        }
    }
});

                contentPanel.add(addPanel, "Add");
            
                modifyPanel = new JPanel();
                modifyPanel.setLayout(new GridLayout(7, 2, 5, 30)); 

                modifyPanel.add(new JLabel("ID Joueur:"));
                JTextField modifyTextFieldID = new JTextField(20);
                modifyPanel.add(modifyTextFieldID);
 
             /*     modifyPanel.add(new JLabel("Nouveau Nom Joueur:"));
                JTextField modifyTextFieldName = new JTextField(20);
                modifyPanel.add(modifyTextFieldName);

                modifyPanel.add(new JLabel("Nouveau Prenom Joueur:"));
                JTextField modifyTextFieldStartDate = new JTextField(20);
                    modifyPanel.add(modifyTextFieldStartDate);
                   modifyPanel.add(new JLabel("Nouveau date de naissance:"));
                    JTextField modifyTextFieldEndDate = new JTextField(20);
                    modifyPanel.add(modifyTextFieldEndDate);
                */
            
                modifyPanel.add(new JLabel("Nouveau Position joueur"));
                JTextField modifyTextFieldPostion = new JTextField(20);
                modifyPanel.add(modifyTextFieldPostion);
                modifyPanel.add(new JLabel("Nouveau ID equipe"));
                JTextField modifyTextFieldIDequip = new JTextField(20);
                modifyPanel.add(modifyTextFieldIDequip);


                JButton submitButton2 = new JButton("Modifier Joueur");
                modifyPanel.add(submitButton2);
                JButton cancelButton2 = new JButton("Annuler");
                modifyPanel.add(cancelButton2);
                cancelButton2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {    
                        modifyTextFieldID.setText("");
                        modifyTextFieldPostion.setText("");
                        modifyTextFieldIDequip.setText("");
                    }
                });
                /*&& isInteger(id_equipe) && id_equipe.length()>=8 && !position.equals("") */
                submitButton2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {    
                        String id=modifyTextFieldID.getText();
                        String id_equipe=modifyTextFieldIDequip.getText();
                        String position=modifyTextFieldPostion.getText();
                        if(isInteger(id) && id.length()<=8){
                            try {
                                Class.forName("com.mysql.cj.jdbc.Driver");  
                                Connection con=connection.getConnection();
                                if(isInteger(id_equipe) && id_equipe.length()<=8){
                                    PreparedStatement r1=con.prepareStatement("update joue set id_équipe=(?) where id_joueur=(?)");
                                    r1.setInt(1, Integer.parseInt(id_equipe));
                                    r1.setInt(2, Integer.parseInt(id));
                                    r1.executeUpdate();
                                    r1.close();
                                }
                                if(!position.equals("")){
                                    PreparedStatement r2=con.prepareStatement("update joueur set poste=(?) where id_joueur=(?)");
                                    r2.setString(1, position);
                                    r2.setInt(2, Integer.parseInt(id));
                                    r2.executeUpdate();
                                    r2.close();
                                }
                            } catch (SQLException | ClassNotFoundException er) {
                                System.out.println(er);
                                JOptionPane.showMessageDialog(null, "Operation Impossible");
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "Verifier votre donnees");
                        }
                    }
                });


            contentPanel.add(modifyPanel, "Modify");
            deletePanel = new JPanel();
            deletePanel.setLayout(new GridLayout(2, 2,5,340));
            deletePanel.add(new JLabel("ID Joueur:"));
            JTextField deleteTextFieldID = new JTextField(20);
            deletePanel.add(deleteTextFieldID);

            JButton deleteButton = new JButton("Supprimer Joueur");
            deletePanel.add(deleteButton);
            JButton cancelButton3 = new JButton("Annuler");
            
            deletePanel.add(cancelButton3);
            cancelButton3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {    
                    deleteTextFieldID.setText("");
                }
            });
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {    
                    String id=deleteTextFieldID.getText();
                    if(isInteger(id)&&(id.length()<=8)){
                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");  
                            Connection con=connection.getConnection();
                            PreparedStatement pstmt=con.prepareStatement("delete from joueur where id_joueur=(?)");
                            pstmt.setInt(1,Integer.parseInt(deleteTextFieldID.getText()));
                            pstmt.executeUpdate();
                            System.out.println("vlaues deleted");
                            pstmt.close();
                            con.close();                   
                        } catch (SQLException | ClassNotFoundException er) {
                            JOptionPane.showMessageDialog(null, "ce id n'exist pas");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Verifier votre donnees");
                    }
                }
            });

            contentPanel.add(deletePanel, "Delete");

            add(contentPanel, BorderLayout.CENTER);
            setVisible(true);
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
            //3ayat lel view frame hna
            view_joueur a=new view_joueur();
            a.show();
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
    public static boolean Isdate(String a) {
        try {
            LocalDate.parse(a);
            return true;
        } catch (DateTimeParseException ex) {
            return false;
        }
    }
}