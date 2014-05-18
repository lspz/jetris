package yetris.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import yetris.model.Model; 
import yetris.Controller;

public class MainView extends JFrame {

  private Model model;
  private GridView gridView;
  private SidePaneView sidePaneView;

  public MainView(Model model){
    super();

    //setUndecorated(true);

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

  public void initStartGame(){
    sidePaneView.initStartGame();
    setStatusMsg("");
  }

  public void refresh(){
    gridView.repaint();
    sidePaneView.refresh();
  }

  public void animateLines(Integer[] lines){
    gridView.animateLines(lines);  
  }

  public void gameOver(){
    setStatusMsg("GAME OVER");
    gridView.drawText("GAME OVER");
  }

  public void gamePaused(Boolean isPaused){
    if (isPaused){
      setStatusMsg("PAUSED");  
      gridView.drawText("PAUSED");
    }
    else {
      setStatusMsg("");
      gridView.repaint();
    }
  }

  public void setController(Controller controller){
    addKeyListener(controller);
    sidePaneView.addActionListener(controller);
  }

  public void setStatusMsg(String msg){
    setTitle("Tetris" + (msg !="" ? " - " : "") + msg);  
  }
}