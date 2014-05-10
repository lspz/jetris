package yetris;

import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import yetris.model.Model;
import yetris.model.Tetrimino;
import yetris.view.MainView;

public class Controller implements KeyListener, ActionListener;
{

  private class GameTicker extends TimerTask {
    Controller controller;
    public GameTicker(Controller controller){
      this.controller = controller;
    }

    @Override
    public void run() {
      this.controller.tick();
    }
  }

  private final int INVERVAL_IN_MS = 1000;

  Model model;
  MainView view;
  Timer timer;

  public Controller(Model model, MainView view){
    this.model = model;
    this.view = view;
    this.timer = new Timer();

    view.setController(this);
  }

  public void start(){
    timer.scheduleAtFixedRate(new GameTicker(this), 0, INVERVAL_IN_MS);
  }

  public void stop(){
    timer.cancel();
  }

  public void suspend(){
    // huh? implement
  }

  public void tick(){
    model.tick();
    view.refresh();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    cmd = e.getActionCommand();
    if (e == 'restart'){
      stop();
      model.restartGame();
      view.refresh();
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
  }

  @Override 
  public void keyReleased(KeyEvent e){
  }

  @Override 
  public void keyTyped(KeyEvent e){
  }
}