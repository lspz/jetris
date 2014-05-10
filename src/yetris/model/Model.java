package yetris.model;

import java.awt.Point;

public class Model 
{
  final int WIDTH = 8;
  final int HEIGHT = 20;
  final int START_X = 2;
  final int START_Y = 0; 

  private TetriminoFactory tetriminoFactory;

  // Game states
  private Grid grid; // This doesn't include currently falling tetrimino
  private Tetrimino activeTetrimino;
  private int tickCounter;
  private int score;

  public Model(){
    grid = new Grid(WIDTH, HEIGHT);
    tetriminoFactory = new TetriminoFactory();

    activeTetrimino = null;
    tickCounter = 0;
    score = 0;
  }

  public void tick(){
    if (activeTetrimino == null) {
      activeTetrimino = tetriminoFactory.createRandom();
      activeTetrimino.getPos().setLocation(START_X, START_Y); 
      return;
    }

    if (grid.canKeepFalling(activeTetrimino)) { 
      tryMove(0, 1);
    } 
    else {
      //System.out.println("Cant go down");
      grid.insertTetrimino(activeTetrimino);
      activeTetrimino = null;
    }

    tickCounter++;
  }

  public void tryRotate(){
    if (activeTetrimino != null) {
      activeTetrimino.rotateRight();
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

  public void incScore(int val){
    score += val;
  }

  public void restartGame(){
    score = 0;
    tickCounter = 0;
    activeTetrimino = null;
    grid.reset();
  }

  public Tetrimino getActiveTetrimino() {
      return this.activeTetrimino;
  }
  public Grid getGrid() {
      return this.grid;
  }
}