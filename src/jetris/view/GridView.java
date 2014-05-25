package jetris.view;

import java.lang.Math;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.FontMetrics;
import java.awt.RenderingHints;
import java.awt.Insets;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

import jetris.model.*; 

// 0, 0 coord is top left

public class GridView extends JPanel {

  private static final int BLOCK_SIZE = 28;
  private static final int BORDER_INSET = 5;
  private static final Color BG_COLOR = Color.BLACK;
  private static final Color GRID_LINE_COLOR = new Color(22,22,22);

  private Model model;
  private Grid grid;

  private String currentGridText;


  public GridView(Model model){
    super();
    
    this.model = model; 
    this.grid = model.getGrid();

    //setBorder(BorderFactory.createEmptyBorder(BORDER_INSET, BORDER_INSET, BORDER_INSET, BORDER_INSET));
    Border border1 = BorderFactory.createLineBorder(getBackground(), BORDER_INSET);
    //setBorder(border1); 
    Border border2 = BorderFactory.createEtchedBorder();
    setBorder(BorderFactory.createCompoundBorder(border1, border2));

    currentGridText = "";
  }

  public void animateLines(Integer[] lines){
    //Graphics g = getGraphics();
    try {
      paintLines(lines, Color.WHITE);
      Thread.sleep(300);
      paintLines(lines, BG_COLOR);
      Thread.sleep(300);
      paintLines(lines, Color.WHITE);
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void setGridText(String text){
    currentGridText = text;
  }

  private void drawText(Graphics2D g, String text){
    Font font = new Font("tahoma", Font.BOLD, 24);
    g.setFont(font);

    FontMetrics fm = g.getFontMetrics();
    Insets insets = getInsets();

    int stringWidth = fm.stringWidth(text);
    int stringHeight = fm.getHeight();
    
    int x = (getSize().width/2) - (stringWidth/2) - insets.left;
    int y = getAbsPos((int)Math.floor(grid.getHeight()/2) - 1);

    if (x < 0) { x = 0;}

    g.setRenderingHint(
        RenderingHints.KEY_TEXT_ANTIALIASING,
        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

    int rectX = x;
    int rectY = y - stringHeight + 5;
    int rectWidth = stringWidth + insets.left + insets.right;
    int rectHeight = stringHeight + insets.top + insets.bottom;

    g.setColor(new Color(6, 18, 46));
    g.fillRect(rectX, rectY , rectWidth , rectHeight);
    g.setColor(Color.LIGHT_GRAY);
    g.drawRect(rectX, rectY , rectWidth , rectHeight);
    g.drawString(text, x + insets.left, y + insets.bottom);
  }

  @Override
  public Dimension getPreferredSize(){
    Insets insets = getInsets();
    return new Dimension(
      // huh? add 1px else, the grid will overlap with border
      (BLOCK_SIZE * grid.getWidth()) + insets.left + insets.right + 1, 
      (BLOCK_SIZE * grid.getHeight()) + insets.top + insets.bottom + 1
    );
  }
  
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    paintBackground(g);

    paintActiveTetrimino(g);

    if (!currentGridText.equals("")){
      drawText((Graphics2D) g, currentGridText);
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
    g.setColor(BG_COLOR);
    g.fillRect(getInsets().left, getInsets().top, getWidth(), getHeight());

    if (model.getConfig().showGridLine){
      g.setColor(GRID_LINE_COLOR);
      Insets insets = getInsets();
      for (int x = 0; x < grid.getWidth() ; x++) {
        g.drawLine((x * BLOCK_SIZE) + insets.left , insets.top, (x * BLOCK_SIZE) + insets.left, getHeight() - insets.bottom);
      }
      for (int y = 0; y < grid.getHeight() ; y++) {
        g.drawLine(insets.left , (y * BLOCK_SIZE) + insets.top, getWidth() - insets.left, (y * BLOCK_SIZE) + insets.top);
      }
    }
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
    g.setColor(model.getConfig().showGridLine ? GRID_LINE_COLOR : BG_COLOR);
    g.drawRect(getAbsPos(x), getAbsPos(y), BLOCK_SIZE, BLOCK_SIZE);
  }

  // We can use this for both x/y as block size is the same
  // huh? we're using same insets for x and y. this is wrong
  private int getAbsPos(int relPos){
    return getInsets().left + (relPos * BLOCK_SIZE);
  }

}