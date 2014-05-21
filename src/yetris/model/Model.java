package yetris.model;

import java.awt.Point;
import java.awt.Rectangle;
import java.lang.Math;

public class Model 
{
  final int WIDTH = 10;
  final int HEIGHT = 18;
  final int START_X = 2;
  final int START_Y = 0; 
  final int FREE_SCORE_INTERVAL = 60;

  private Config config;

  private TetriminoFactory tetriminoFactory;

  private Grid grid; // This doesn't include currently falling tetrimino
  private Tetrimino nextTetrimino;
  private Tetrimino activeTetrimino;
  
  private int tickCounter;
  private int score;
  private int level;
  private int lines;
  private int tickInterval; // in ms

  public Model(){
    config = new Config();
    grid = new Grid(WIDTH, HEIGHT);
    tetriminoFactory = new TetriminoFactory();

    nextTetrimino = null;
    activeTetrimino = null;
    tickCounter = 0;
    score = 0;
    level = 1;
    lines = 0;

    nextTetrimino = tetriminoFactory.createRandom();
  }

  // False indicate game over
  public Boolean tick(){
    if (activeTetrimino == null) {
      activeTetrimino = nextTetrimino; 
      activeTetrimino.getPos().setLocation(START_X, START_Y); 
      if (!grid.canOccupySpace(activeTetrimino)){
        return false;
      }
      
      nextTetrimino = tetriminoFactory.createRandom();
      return true;
    }

    if (grid.canKeepFalling(activeTetrimino)) { 
      tryMove(0, 1);
    } 
    else {
      grid.insertTetrimino(activeTetrimino);
      activeTetrimino = null;
    }

    tickCounter++;
    afterTick();

    return true;
  }

  public void tryRotate(){
    if (activeTetrimino != null) {
      TetriminoState rotatedState = activeTetrimino.getStateForRotateRight();
      Rectangle rotatedBound = new Rectangle(rotatedState.getBound());
      rotatedBound.translate(activeTetrimino.getPos().x, activeTetrimino.getPos().y);

      if (grid.canOccupySpace(activeTetrimino.getPos(), rotatedBound, rotatedState.getGrid())){
        activeTetrimino.rotateRight();
      }
    }
  }

  public void tryMove(int dx, int dy){
    if (activeTetrimino == null) {return;}
      
    Point moveToPos = new Point(activeTetrimino.getPos());
    moveToPos.translate(dx, dy);

    if (grid.canMoveTo(activeTetrimino, moveToPos)) {
      activeTetrimino.getPos().translate(dx, dy);
    }
  }

  public Integer[] checkForLines(){
    Integer[] result = grid.checkForLines();
    incLines(result.length);
    return result;
  }

  public void incLines(int val){
    if (val==0) { return ;}
    lines += val;
    incScore(((int) Math.pow(2.7, val)) * 20);

    System.out.println("Add Lines: " + Integer.toString(val));
  }

  public void incScore(int val){
    score += val;
    
    System.out.println("Add Score: " + Integer.toString(val) );
  }

  public void restartGame(){
    score = 0;
    tickCounter = 0;
    activeTetrimino = null;
    level = config.startFromLvl;
    grid.reset();
  }

  private void afterTick(){
    if ((tickCounter % FREE_SCORE_INTERVAL) == 0){
      incScore(5);
    }
    int progress = (tickCounter * 50) + (score) - (level * 5000);
    if (progress > 0){
      level++;
    }  
  }

  public Config getConfig() {
    return this.config;
  }

  public Tetrimino getNextTetrimino() {
    return this.nextTetrimino;
  }

  public Tetrimino getActiveTetrimino() {
    return this.activeTetrimino;
  }

  public Grid getGrid() {
      return this.grid;
  }

  public int getScore() {
    return this.score;
  }

  public int getLevel() {
    return this.level;
  }

  public int getLines() {
    return this.lines;
  }

  public int getTickInterval() {
    return 1400 - (level * 120);
  }
}