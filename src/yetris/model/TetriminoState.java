package yetris.model;

import java.awt.Rectangle;

public class TetriminoState {
  private int[][] grid;
  private Rectangle bound;

  public TetriminoState(int[][] grid){
    this.grid = grid;


    int minX, minY, maxX, maxY, width, height;
    minX = minY = 100;
    maxX = maxY = -1;

    for (int x = 0; x < grid.length; x++) {
      for (int y = 0; y < grid[x].length ; y++){
        if (grid[x][y] == 1){
          minX = Math.min(minX, x);
          minY = Math.min(minY, y);
          maxX = Math.max(maxX, x);
          maxY = Math.max(maxY, y);
        }
      }
    }

    width = maxX - minX + 1;
    height = maxY - minY + 1;
    bound = new Rectangle(minX, minY, width, height);
  }

  public int[][] getGrid(){
    return this.grid;
  }

  // Bound is the actual occupied area (e.g: all row/col has at least one cell)
  public Rectangle getBound() {
    return this.bound;
  }
}