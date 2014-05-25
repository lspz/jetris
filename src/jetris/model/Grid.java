package jetris.model;
import java.util.ArrayList;
import java.awt.Point;
import java.awt.Rectangle;

public class Grid 
{

  private TetriminoType[][] internalGrid; // Defines which type occupy particular cell
  private Rectangle gridBound;

  public Grid(int width, int height) {
    internalGrid = new TetriminoType[width][height];   

    this.gridBound = new Rectangle(0, 0, width, height);

    reset();
  } 

  public void reset(){
    for (int x = 0; x < gridBound.width; x++) {
      for (int y = 0; y < gridBound.height; y++){
        internalGrid[x][y] = TetriminoType.NONE;
      }
    }
  }

  public Boolean canKeepFalling(Tetrimino tetrimino){
    Point futurePos = new Point(tetrimino.getPos());
    futurePos.translate(0, 1);

    return canMoveTo(tetrimino, futurePos);
  }

  public Boolean canMoveTo(Tetrimino tetrimino, Point pos){
    return canOccupySpace(pos, tetrimino.getTranslatedBound(pos), tetrimino.getState().getGrid());
  }

  public Boolean canOccupySpace(Tetrimino tetrimino){
    return canOccupySpace(tetrimino.getPos(), tetrimino.getTranslatedBound(), tetrimino.getState().getGrid());
  }

  public Boolean canOccupySpace(Point pos, Rectangle tetriminoBound, int[][] tetriminoGrid){
    return gridBound.contains(tetriminoBound) && !isOverlapping(pos, tetriminoGrid);
  }

  public Boolean isOverlapping(Point pos, int[][] tetriminoGrid){
    for (int x = 0; x < tetriminoGrid.length; x++ ){
      for (int y = 0; y < tetriminoGrid[x].length; y++){
        int gridX = pos.x + x;
        int gridY = pos.y + y;
        if ( gridBound.contains(gridX, gridY) &&
             (internalGrid[gridX][gridY] != TetriminoType.NONE) && 
             (tetriminoGrid[x][y] != 0)
           ) {
          return true;
        }
      }
    }
    return false;
  }

  public void insertTetrimino(Tetrimino tetrimino){
    int[][] curState = tetrimino.getState().getGrid();
    for (int x = 0; x < tetrimino.getWidth(); x++) {
      for (int y = 0; y < tetrimino.getHeight(); y++){
        int gridX = tetrimino.getPos().x + x;
        int gridY = tetrimino.getPos().y + y;
        if (gridBound.contains(gridX, gridY) && curState[x][y] == 1) {
          internalGrid[gridX][gridY] = tetrimino.getType();
        }
      }
    }
  }

  public Integer[] checkForLines(){
    ArrayList<Integer> lines = new ArrayList<Integer>();

    for (int y = 0; y < gridBound.height; y++){
      if (hasLineFullyOccupied(y)) {
        removeLine(y);
        lines.add(y);
      } 
    }  

    Integer[] result = new Integer[lines.size()];
    return lines.toArray(result);
  }

  private Boolean hasLineFullyOccupied(int y){
    for (int x = 0; x < gridBound.width; x++) {
      if (internalGrid[x][y] == TetriminoType.NONE){
        return false;
      }
    }
    return true;
  }

  private void removeLine(int lineToRemove){
    for (int y = lineToRemove; y > 0; y--){  
      copyRow(y-1, y);
    }
    for (int x = 0; x < gridBound.width; x++) {
      internalGrid[x][0] = TetriminoType.NONE;
    }
  }

  private void copyRow(int fromY, int toY){
    for (int x = 0; x < gridBound.width; x++) {
      internalGrid[x][toY] = internalGrid[x][fromY];
    }
  }

  public TetriminoType getCell(int x, int y){
    return internalGrid[x][y];
  }

  public int getWidth(){
    return gridBound.width;
  }

  public int getHeight(){
    return gridBound.height;
  }

}