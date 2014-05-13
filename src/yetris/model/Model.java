package yetris.model;

import java.awt.Point;
import java.awt.Rectangle;

public class Model 
{
  final int WIDTH = 10;
  final int HEIGHT = 18;
  final int START_X = 2;
  final int START_Y = 0; 
  final int FREE_SCORE_INTERVAL = 100;

  private Config config;

  private TetriminoFactory tetriminoFactory;

  private Grid grid; // This doesn't include currently falling tetrimino
  private Tetrimino nextTetrimino;
  private Tetrimino activeTetrimino;
  
  private int tickCounter;
  private int score;
  private int level;
  private int lines;

  public Model(){
    config = new Config();
    grid = new Grid(WIDTH, HEIGHT);
    tetriminoFactory = new TetriminoFactory();

    nextTetrimino = null;
    activeTetrimino = null;
    tickCounter = 0;
    score = 0;
    level = 0;
    lines = 0;

    nextTetrimino = tetriminoFactory.createRandom();
  }

  public void tick(){
    int linesRemoved = grid.checkForLines();
    incLines(linesRemoved);

    if (activeTetrimino == null) {
      activeTetrimino = nextTetrimino; 
      activeTetrimino.getPos().setLocation(START_X, START_Y); 
      
      nextTetrimino = tetriminoFactory.createRandom();
      return;
    }

    if (grid.canKeepFalling(activeTetrimino)) { 
      tryMove(0, 1);
    } 
    else {
      grid.insertTetrimino(activeTetrimino);
      activeTetrimino = null;
    }

    tickCounter++;

    updateScore();
  }

  public void tryRotate(){
    if (activeTetrimino != null) {
      // huh? this doesnt work right
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

  public void incLines(int val){
    lines += val;
    incScore(val * val * 20);
  }

  public void incScore(int val){
    score += val;
  }

  public void restartGame(){
    score = 0;
    tickCounter = 0;
    activeTetrimino = null;
    grid.reset();
  }

  private void updateScore(){
    if ((tickCounter % FREE_SCORE_INTERVAL) == 0){
      incScore(5);
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
}