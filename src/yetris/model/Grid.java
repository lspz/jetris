package yetris.model;
import java.awt.Point;
import java.awt.Rectangle;

public class Grid 
{

  private class TetriminoGridInserter implements TetriminoCellVisitor{
    private TetriminoType[][] grid;
    public TetriminoGridInserter(TetriminoType[][] grid){
      this.grid = grid;
    }
    @Override
    public void visit(int gridX, int gridY, Boolean hasCell, TetriminoType type){
      if (hasCell) {
        // huh? this if shouldnt be needed once we fixed the tetrimino going outofbound
        if (gridX < grid.length && gridY < grid[0].length) {
          grid[gridX][gridY] = type;
        }
      }
    }  
  }

  private TetriminoType[][] internalGrid; // Defines which type occupy particular cell
  private TetriminoGridInserter tetriminoInserter;
  private int width;
  private int height;


  public Grid(int width, int height) {
    internalGrid = new TetriminoType[width][height];   
    tetriminoInserter = new TetriminoGridInserter(internalGrid);

    this.width = width;
    this.height = height;

    reset();
  } 

  public void reset(){
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++){
        internalGrid[x][y] = TetriminoType.NONE;
      }
    }
  }

  public Boolean canKeepFalling(Tetrimino tetrimino){
    Point futurePos = new Point(tetrimino.getPos());
    futurePos.translate(0, 1);

    Boolean willHitBottom = (futurePos.y + tetrimino.getBound().height) > height; 
    
    return !willHitBottom && !isOverlapping(tetrimino, futurePos);
  }

  public Boolean canMoveTo(Tetrimino tetrimino, Point pos){
    Rectangle bound = tetrimino.getBound();
    Boolean isBreaching = 
      ((pos.x + bound.x) < 0 ) || ((pos.y + bound.y) < 0 ) ||
      ((pos.x + bound.width) > width ) || ((pos.y + bound.height) > height ); 

    return !isBreaching && !isOverlapping(tetrimino, pos);
  }

  public Boolean isOverlapping(Tetrimino tetrimino, Point pos){
    Rectangle bound = tetrimino.getBound();
    int[][] tetriminoGrid = tetrimino.getState().getGrid();
    for (int x = 0; x < bound.width; x++ ){
      for (int y = 0; y < bound.height; y++){
        if (internalGrid[pos.x + x][pos.y + y] != TetriminoType.NONE && tetriminoGrid[x][y] != 0){
          return true;
        }
      }
    }
    return false;
  }

  // huh? maybe could throw exception
  public void insertTetrimino(Tetrimino tetrimino){
    tetrimino.iterateCells(tetriminoInserter);
  }

  public TetriminoType getCell(int x, int y){
    return internalGrid[x][y];
  }

  public int getWidth() {
      return this.width;
  }

  public int getHeight() {
      return this.height;
  }

}