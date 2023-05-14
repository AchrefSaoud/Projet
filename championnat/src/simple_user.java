import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class simple_user extends JFrame {
    public  void show(){
        JLabel title = new JLabel("Page main du choix d'un simple user", SwingConstants.CENTER);
        title.setFont(new Font("TimesRoman", 50, 23));
        JFrame f = new JFrame("Simple user");
        f.add(title, BorderLayout.NORTH); 

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 800, 40));
        JButton b1 = new JButton("Voir joueurs");
        b1.setPreferredSize(new Dimension(200, 80)); 
        JButton b2 = new JButton("Voir championnat");
        b2.setPreferredSize(new Dimension(200, 80));
        JButton b3 = new JButton("Voir arbitres");
        b3.setPreferredSize(new Dimension(200, 80));
        JButton b4 = new JButton("Voir equipes");
        b4.setPreferredSize(new Dimension(200, 80));
        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        panel.add(b4);
        f.add(panel, BorderLayout.CENTER); 
        f.setSize(1000, 700);
        f.setLocationRelativeTo(null); 
        f.setVisible(true);
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view_arb a1=new view_arb();
                a1.show();
            }
        });
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view_eq a2=new view_eq();
                a2.show();
            }
        });
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view_eq a2=new view_eq();
                a2.show();

            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view_championnat a=new view_championnat();
            }
        });
    }

}


