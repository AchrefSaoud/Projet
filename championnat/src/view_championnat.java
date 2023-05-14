import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class view_championnat {
     JFrame frame;
     JTextField idField;

    public view_championnat() {
        frame = new JFrame("Voir championnat");
        frame.setSize(400, 200); 
        frame.setResizable(false); 
        JLabel idLabel = new JLabel("ID championnat:");
        idField = new JTextField(10);
        JButton viewButton = new JButton("Voir");
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isInteger(idField.getText())&& idField.getText().length()<=8){
                    statistique a=new statistique(Integer.parseInt(idField.getText()));
                }
            }
        });
        JButton cancelButton = new JButton("Annuler");
        
			cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
					idField.setText("");
					
                }
            });
        JPanel panel = new JPanel();
        panel.setLayout(null);
        idLabel.setBounds(10, 30, 170, 25);
        idField.setBounds(130, 30, 150, 25);
        viewButton.setBounds(110, 70, 80, 25);
        cancelButton.setBounds(200, 70, 100, 25);
        panel.add(idLabel);
        panel.add(idField);
        panel.add(viewButton);
        panel.add(cancelButton);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
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