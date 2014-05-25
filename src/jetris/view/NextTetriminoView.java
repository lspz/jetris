package jetris.view;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;

import jetris.model.TetriminoType;
import jetris.model.Tetrimino;
import jetris.model.Model;

public class NextTetriminoView extends JPanel {

  private static final int BLOCK_SIZE = 15;
  private final int MARGIN = 1;

  Model model;

  public NextTetriminoView(Model model) {
    super();
    this.model = model;
    
    setOpaque(false);

    if (model.getConfig().debug){
      setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK));
    }
  }

  public void refresh(){
    repaint();
    revalidate();
  }

  @Override
  public Dimension getPreferredSize(){
    Tetrimino tetrimino = model.getNextTetrimino();
    if (tetrimino != null ) {
      return new Dimension(
        (BLOCK_SIZE * tetrimino.getWidth()) + MARGIN, 
        (BLOCK_SIZE * tetrimino.getHeight()) + MARGIN);
    }
    else {
      return new Dimension(100, 100); // Should ideally never reach here
    }
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Tetrimino tetrimino = model.getNextTetrimino();
    if (tetrimino == null ) {return;}

    int[][] gridState = tetrimino.getState().getGrid();
    for (int x = 0; x < gridState.length ; x++) {
      for (int y = 0; y < gridState[x].length ; y++){ 
        if (gridState[x][y] == 1){
          paintCell(g, x, y, tetrimino.getType());
        }
      }
    }
  }

  private void paintCell(Graphics g, int x, int y, TetriminoType type){
    //System.out.println(x + " -:::- " + y);
    g.setColor(ViewCommon.getColorMap().get(type));
    g.fillRect(getAbsPos(x), getAbsPos(y), BLOCK_SIZE, BLOCK_SIZE);
    g.setColor(Color.BLACK);
    g.drawRect(getAbsPos(x), getAbsPos(y), BLOCK_SIZE, BLOCK_SIZE);
  }

  // We can use this for both x/y as block size is the same
  private int getAbsPos(int relPos){
    return relPos * BLOCK_SIZE;
  }
} 
