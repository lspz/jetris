package yetris.view;

import java.util.EnumMap;
import java.awt.Color;
import yetris.model.TetriminoType;

public final class ViewCommon {
  private ViewCommon() {}

  private static EnumMap<TetriminoType, Color> colorMap;

  public static EnumMap<TetriminoType, Color> getColorMap(){
    if (colorMap == null){
      colorMap = new EnumMap<TetriminoType, Color>(TetriminoType.class);
      colorMap.put(TetriminoType.I, Color.BLUE);
      colorMap.put(TetriminoType.J, Color.YELLOW);
      colorMap.put(TetriminoType.L, Color.CYAN);
      colorMap.put(TetriminoType.O, Color.GREEN);
      colorMap.put(TetriminoType.S, Color.RED);
      colorMap.put(TetriminoType.Z, Color.GRAY);
      colorMap.put(TetriminoType.T, Color.WHITE);
    }

    return colorMap;
  }
}