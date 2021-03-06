package jetris;

import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import jetris.model.Model;
import jetris.model.Tetrimino;
import jetris.view.MainView;

public class Controller implements KeyListener, ActionListener
{

  private enum GameState {STOPPED, STARTED, PAUSED, GAME_OVER}

  Model model;
  MainView view;
  Timer timer;
  GameState gameState;
  int currentTickInterval;

  public Controller(Model model, MainView view){
    this.model = model;
    this.view = view;

    view.setController(this);
    view.refresh();

    gameState = GameState.STOPPED;
  }

  public void start(){
    model.restartGame();
    view.initStartGame();
    view.refresh();
    view.requestFocus();

    currentTickInterval = model.getTickInterval();

    scheduleTimer();

    gameState = GameState.STARTED;
  }

  public void stop(){
    if (timer != null){
      timer.cancel();
    }
    gameState = GameState.STOPPED;
  }

  public void tick(){
    if (gameState == GameState.STARTED) {
      Boolean canContinue = model.tick();
      if (!canContinue) {
        gameState = GameState.GAME_OVER;
        view.gameOver();
        timer.cancel();
        return;
      }

      Integer[] lines = model.checkForLines();
      if (lines.length > 0) {
        view.animateLines(lines);
      }

      view.refresh();

      if (currentTickInterval != model.getTickInterval()){
        currentTickInterval = model.getTickInterval();
        timer.cancel();
        scheduleTimer();
      }
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String cmd = e.getActionCommand();
    if (cmd.equals("restart")){
      if (gameState == GameState.STOPPED){
        start();
      }
      else {
        gameState = GameState.PAUSED;
        view.gamePaused(true); 

        if (view.showYesNoPrompt("Are you sure?", "Restart Game")){
          stop();
          start();
        }

        view.gamePaused(false); 
        gameState = GameState.STARTED;
      }
    }

    else if (cmd.equals("options")){
      GameState originalState = gameState;
      gameState = GameState.PAUSED;
      view.gamePaused(true); 
      
      if (view.showOptionDialog()){
        view.reloadOptions(); 
      }

      view.gamePaused(false); 
      gameState = originalState;
    }
  }


  @Override 
  public void keyPressed(KeyEvent e){

    int keyCode = e.getKeyCode();

    if (gameState == GameState.STARTED){
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
    
    if (keyCode == KeyEvent.VK_SPACE) {
      if (gameState == GameState.STOPPED){
        start();
      } 
      else if (gameState != GameState.GAME_OVER) {
        gameState = (gameState == GameState.STARTED ? GameState.PAUSED : GameState.STARTED);
        view.gamePaused(gameState == GameState.PAUSED); 
      }

    }
  }

  @Override 
  public void keyReleased(KeyEvent e){
  }

  @Override 
  public void keyTyped(KeyEvent e){
  }

  private void scheduleTimer(){
    this.timer = new Timer();
    timer.scheduleAtFixedRate(
      new TimerTask(){ public void run() {tick();} }, 
      0, 
      currentTickInterval
    );
  }
}