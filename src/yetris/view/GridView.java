package yetris.view;

import java.util.EnumMap;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;

import yetris.model.*; 

// 0, 0 coord is top left

public class GridView extends JPanel {

  
  private class TetriminoPainter implements TetriminoCellVisitor{
    Graphics g;
    public TetriminoPainter(Graphics g){
      this.g = g;
    }
    @Override
    public void visit(int gridX, int gridY, Boolean hasCell, TetriminoType type){
      if (hasCell){
        paintCell(g, gridX, gridY, type);
      }
    }  
  }

  final int BLOCK_SIZE = 32;
  final double CELL_ANCHOR = 0.5;
  final double CELL_ANCHOR_OFFSET = CELL_ANCHOR * BLOCK_SIZE;

  private Model model;
  private Grid grid;
  private EnumMap<TetriminoType, Color> colorMap;

  public GridView(Model model){
    super();
    
    this.model = model; 
    this.grid = model.getGrid();

    Dimension dimension = new Dimension(BLOCK_SIZE * grid.getWidth(), BLOCK_SIZE * grid.getHeight());
    setPreferredSize(dimension);
    setMinimumSize(dimension);

    initColorMap();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Tetrimino activeTetrimino = model.getActiveTetrimino(); 
    if (activeTetrimino != null) {
      activeTetrimino.iterateCells(new TetriminoPainter(g));
    }


    for (int x = 0; x < grid.getWidth() ; x++) {
      for (int y = 0; y < grid.getHeight() ; y++){
        if (grid.getCell(x, y) != TetriminoType.NONE){
          paintCell(g, x, y, grid.getCell(x, y));
        }
      }
    }
  }

  private void paintBackground(Graphics g){
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, getWidth(), getHeight());
  }
  
  private void paintCell(Graphics g, int x, int y, TetriminoType type){
    g.setColor(colorMap.get(type));
    g.fillRect(getAbsPos(x), getAbsPos(y), BLOCK_SIZE, BLOCK_SIZE);
    g.setColor(Color.BLACK);
    g.drawRect(getAbsPos(x), getAbsPos(y), BLOCK_SIZE, BLOCK_SIZE);
  }

  private void initColorMap() {
    colorMap = new EnumMap<TetriminoType, Color>(TetriminoType.class);
    colorMap.put(TetriminoType.I, Color.BLUE);
    colorMap.put(TetriminoType.J, Color.YELLOW);
    colorMap.put(TetriminoType.L, Color.CYAN);
    colorMap.put(TetriminoType.O, Color.GREEN);
    colorMap.put(TetriminoType.S, Color.RED);
    colorMap.put(TetriminoType.Z, Color.GRAY);
    colorMap.put(TetriminoType.T, Color.DARK_GRAY);
  }

  // We can use this for both x/y as block size is the same
  private int getAbsPos(int relPos){
    return relPos * BLOCK_SIZE;
  }

}