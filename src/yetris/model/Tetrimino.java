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
    //System.out.println("rotate");
    stateList.goNext();
  } 

  public void rotateLeft(){
    stateList.goPrev();
  } 

  @Override 
  public Tetrimino clone() {
    return new Tetrimino(type, stateList.clone(), width, height);
  }


  public Rectangle getTranslatedBound(){
    return getTranslatedBound(this.pos);
  }

  public Rectangle getTranslatedBound(Point pos){
    Rectangle bound = new Rectangle(getState().getBound());
    bound.translate(pos.x, pos.y);
    return bound;
  } 

  public TetriminoState getState(){
    return stateList.getCurrent();
  }
  
  public TetriminoState getStateForRotateRight(){
    return stateList.getNext();
  }

  public TetriminoState getStateForRotateLeft(){
    return stateList.getPrev();
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
