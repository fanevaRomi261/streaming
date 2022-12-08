package affichage;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTable;
import listener.*;

public class ClientFrame extends JFrame{

    // JComboBox j;
    JList j;
    JButton valider;
    int choix=-1;

    public ClientFrame(String[] hira){

        // j=new JComboBox<>(hira);

        j=new JList<>(hira);
        valider=new JButton("valider");

        Listener l=new Listener(this);

        j.setBounds(0, 0, 400, 400);
        valider.setBounds(400,400,75,50);
        valider.addMouseListener(l);

        this.add(valider);
        this.add(j);
        this.setSize(500,500);
        this.setLayout(null);
        this.setVisible(true);
    }

    public JList getJ(){
        return this.j;
    }

    public void setJ(JList j) {
        this.j = j;
    }

    public JButton getValider() {
        return valider;
    }

    public void setValider(JButton valider) {
        this.valider = valider;
    }

    public int getChoix() {
        return choix;
    }

    public void setChoix(int choix) {
        this.choix = choix;
    }
}
