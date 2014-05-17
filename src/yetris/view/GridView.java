package yetris.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

import yetris.model.*; 

// 0, 0 coord is top left

public class GridView extends JPanel {

  private static final int BLOCK_SIZE = 32;
  
  private Model model;
  private Grid grid;


  public GridView(Model model){
    super();
    
    this.model = model; 
    this.grid = model.getGrid();

    setBorder(BorderFactory.createEtchedBorder());
  }

  public void animateLines(Integer[] lines){
    try {
      paintLines(lines, Color.WHITE);
      Thread.sleep(300);
      paintLines(lines, Color.BLACK);
      Thread.sleep(300);
      paintLines(lines, Color.WHITE);
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Dimension getPreferredSize(){
    return new Dimension(BLOCK_SIZE * grid.getWidth(), BLOCK_SIZE * grid.getHeight());
  }
  
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    paintBackground(g);

    paintActiveTetrimino(g);


    for (int x = 0; x < grid.getWidth() ; x++) {
      for (int y = 0; y < grid.getHeight() ; y++){
        if (grid.getCell(x, y) != TetriminoType.NONE){
          paintCell(g, x, y, grid.getCell(x, y));
        }
      }
    }
  }

  private void paintBackground(Graphics g){
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, getWidth(), getHeight());
  }
  
  private void paintActiveTetrimino(Graphics g){
    Tetrimino tetrimino = model.getActiveTetrimino(); 
    if (tetrimino == null) { return;}

    int[][] curState = tetrimino.getState().getGrid();
    for (int x = 0; x < curState.length; x++) {
      for (int y = 0; y < curState[x].length; y++){
        if (curState[x][y] == 1) {
          int gridX = x + tetrimino.getPos().x;

          int gridY = y + tetrimino.getPos().y;
          paintCell(g, gridX, gridY, tetrimino.getType());
        }
      }
    }

    if (model.getConfig().debug){
      Rectangle bound = tetrimino.getTranslatedBound();
      g.setColor(Color.WHITE);
      g.drawRect(getAbsPos(bound.x), getAbsPos(bound.y), bound.width * BLOCK_SIZE, bound.height * BLOCK_SIZE);
    }
  }

  private void paintLines(Integer[] lines, Color color){
    Graphics g = getGraphics();
    for (int i = 0; i < lines.length; i++) {
      for (int x = 0; x < grid.getWidth(); x++) {
        paintCell(g, x, lines[i], color);
      }
    }
  }
  private void paintCell(Graphics g, int x, int y, TetriminoType type){
    paintCell(g, x, y, ViewCommon.getColorMap().get(type));
  }

  private void paintCell(Graphics g, int x, int y, Color color){
    g.setColor(color);
    g.fillRect(getAbsPos(x), getAbsPos(y), BLOCK_SIZE, BLOCK_SIZE);
    g.setColor(Color.BLACK);
    g.drawRect(getAbsPos(x), getAbsPos(y), BLOCK_SIZE, BLOCK_SIZE);
  }

  // We can use this for both x/y as block size is the same
  private int getAbsPos(int relPos){
    return relPos * BLOCK_SIZE;
  }

}