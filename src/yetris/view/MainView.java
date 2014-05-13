package yetris.view;

import java.awt.BorderLayout;
import javax.swing.JFrame;

import yetris.model.Model; 
import yetris.Controller;

public class MainView extends JFrame {

  private Model model;
  private GridView gridView;
  private SidePaneView sidePaneView;

  public MainView(Model model){
    super();

    this.model = model; 
    
    gridView = new GridView(model);
    sidePaneView = new SidePaneView(model);
    getContentPane().add(gridView, BorderLayout.CENTER);
    getContentPane().add(sidePaneView, BorderLayout.WEST);
    
    pack();

    setTitle("Tetris");
    setResizable(false);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setFocusable(true);
  }

  public void restartGame(){
    sidePaneView.btnRestart.setText("Restart");
  }

  public void refresh(){
    gridView.repaint();
    sidePaneView.refresh();
  }

  public void setController(Controller controller){
    addKeyListener(controller);
    sidePaneView.addActionListener(controller);
  }

  public void setStatusMsg(String msg){
    setTitle("Tetris" + (msg !="" ? " - " : "") + msg);  
  }
}