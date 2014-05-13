package yetris;

import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import yetris.model.Model;
import yetris.model.Tetrimino;
import yetris.view.MainView;

public class Controller implements KeyListener, ActionListener
{

  private final int INVERVAL_IN_MS = 1000;

  Model model;
  MainView view;
  Timer timer;

  private Boolean isSuspended;

  public Controller(Model model, MainView view){
    this.model = model;
    this.view = view;

    view.setController(this);

    isSuspended = false;
  }

  public void start(){
    model.restartGame();
    view.restartGame();
    view.refresh();
    view.requestFocus();

    this.timer = new Timer();
    timer.scheduleAtFixedRate(
      new TimerTask(){ public void run() {tick();} }, 
      0, 
      INVERVAL_IN_MS
    );
  }

  public void stop(){
    timer.cancel();
    timer = null;
  }

  public void tick(){
    if (!isSuspended) {
      model.tick();
      view.refresh();
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String cmd = e.getActionCommand();
    if (cmd == "restart"){
      stop();
      start();
    }
  }

  @Override 
  public void keyPressed(KeyEvent e){

    int keyCode = e.getKeyCode();

    if (keyCode == KeyEvent.VK_UP) {
      model.tryRotate();
      view.refresh();
    } 
    else if (keyCode == KeyEvent.VK_LEFT) {
      model.tryMove(-1, 0);
      view.refresh();
    }
    else if (keyCode == KeyEvent.VK_RIGHT) {
      model.tryMove(1, 0);
      view.refresh();
    } 
    else if (keyCode == KeyEvent.VK_DOWN) {
      model.tryMove(0, 1);
      view.refresh();   
    }
    else if (keyCode == KeyEvent.VK_SPACE) {
      isSuspended = !isSuspended;
      view.setStatusMsg(isSuspended ? "(PAUSED)" : "");
    }
  }

  @Override 
  public void keyReleased(KeyEvent e){
  }

  @Override 
  public void keyTyped(KeyEvent e){
  }
}