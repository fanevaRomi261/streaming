package listener;

import java.awt.event.*;
import affichage.*;
import javax.swing.JButton;

public class Listener implements MouseListener{
    ClientFrame frame;

    public Listener(ClientFrame frame) {
        this.frame = frame;
    }

    @Override

    public void mouseClicked(MouseEvent e) {
        if((JButton)e.getSource()==frame.getValider()){
            frame.setChoix(frame.getJ().getSelectedIndex());
        }
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    // TODO Auto-generated method stub
    
    }
    @Override
    public void mouseExited(MouseEvent e) {
    // TODO Auto-generated method stub
    
    }
    @Override
    public void mousePressed(MouseEvent e) {
    // TODO Auto-generated method stub
    
    }
    @Override
    public void mouseReleased(MouseEvent e) {
    // TODO Auto-generated method stub
    }
    
}
