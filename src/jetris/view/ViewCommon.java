package jetris.view;

import java.util.EnumMap;
import java.awt.Color;
import jetris.model.TetriminoType;

public final class ViewCommon {
  private ViewCommon() {}

  private static EnumMap<TetriminoType, Color> colorMap;

  public static EnumMap<TetriminoType, Color> getColorMap(){
    if (colorMap == null){
      colorMap = new EnumMap<TetriminoType, Color>(TetriminoType.class);
      colorMap.put(TetriminoType.NONE, Color.BLACK);
      colorMap.put(TetriminoType.I, new Color(221, 30, 47));
      colorMap.put(TetriminoType.J, new Color(235, 176, 53));
      colorMap.put(TetriminoType.L, new Color(6, 162, 203));
      colorMap.put(TetriminoType.O, new Color(33, 133,89));
      colorMap.put(TetriminoType.S, new Color(208, 198, 177));
      colorMap.put(TetriminoType.Z, new Color(208, 198, 100));
      colorMap.put(TetriminoType.T, new Color(62, 102, 87));
    }

    return colorMap;
  }
}