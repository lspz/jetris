package yetris.model;

import java.awt.Point;
import java.awt.Rectangle;

import yetris.util.CircularList;

public class Tetrimino implements Cloneable 
{

  protected CircularList<TetriminoState> stateList;  
  private Point pos; // grid coordinate based on topleft anchor
  private TetriminoType type;
  private int width;
  private int height;

  public Tetrimino(TetriminoType type, CircularList<TetriminoState> stateList, int width, int height){
    this.stateList = stateList;
    this.type = type;
    this.pos = new Point(0,0);

    this.width = width;
    this.height = height;
  }

  public void rotateRight(){
    stateList.next();
  } 

  public void rotateLeft(){
    stateList.prev();
  } 

  public void iterateCells(TetriminoCellVisitor visitor){
    int[][] curState = getState().getGrid();
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++){
        int gridX = pos.x + x;
        int gridY = pos.y + y;
        visitor.visit(gridX, gridY, curState[x][y] == 1, getType());
      }
    }
  }

  @Override 
  public Tetrimino clone() {
    return new Tetrimino(type, stateList, width, height);
  }

  public Rectangle getBound(){
    Rectangle bound = new Rectangle(getState().getBound());
    bound.translate(pos.x, pos.y);
    return bound;
  } 

  public TetriminoState getState(){
    return stateList.get();
  }
  
  public Point getPos() {
    return this.pos;
  }

  public TetriminoType getType() {
    return this.type;
  }

  public int getWidth() {
    return this.width;
  }

  public int getHeight() {
    return this.height;
  }

}
