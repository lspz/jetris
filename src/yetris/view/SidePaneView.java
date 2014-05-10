package yetris.view;

import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

public class SidePaneView extends JPanel {

  JButton btnRestart; 
  
  public SidePaneView(){
    super();

    btnRestart = new JButton('Restart');
    btnRestart.setActionCommand('restart');
    add(btnRestart);
  }

  public void addActionListener(ActionListener al){
    btnRestart.addActionListener(al);
  }
}