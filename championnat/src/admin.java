import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class admin extends JFrame {
    public  void show(){
        JFrame f = new JFrame("admin");
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 800, 40));
        JButton b1 = new JButton("Gestion joueurs");
        b1.setPreferredSize(new Dimension(200, 80)); 
        JButton b2 = new JButton("Gestion Championnat");
        b2.setPreferredSize(new Dimension(200, 80));
        JButton b3 = new JButton("Gestion arbitres");
        b3.setPreferredSize(new Dimension(200, 80));
        JButton b4 = new JButton("Gestion equipes");
        b4.setPreferredSize(new Dimension(200, 80));
        JButton b5 = new JButton("Gestion match");
        b5.setPreferredSize(new Dimension(200, 80));
        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        panel.add(b4);
        panel.add(b5);
        f.add(panel, BorderLayout.CENTER); 
        f.setSize(1000, 700);
        f.setLocationRelativeTo(null); 
        f.setVisible(true);
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gestion_eq ge=new gestion_eq();
                ge.show();
            }
        });
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gestion_arb ge=new gestion_arb();
                ge.show();
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gestion_com a=new gestion_com();
            }
        });
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gestion_joueur a=new gestion_joueur();

            }
        });
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ajout_match a=new Ajout_match();
            }
        });
    }
}