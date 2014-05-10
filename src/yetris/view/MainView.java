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
    sidePaneView = new SidePaneView()
    getContentPane().add(gridView, BorderLayout.CENTER);
    getContentPane().add(sidePaneView, BorderLayout.LEFT);
    
    pack();

    setTitle("Tetris");
    setResizable(false);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
  }

  public void refresh(){
    gridView.repaint();
  }

  public void setController(Controller controller){
    addKeyListener(controller);
    sidePaneView.addActionListener(controller);
  }
}