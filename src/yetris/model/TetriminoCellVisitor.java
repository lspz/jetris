package yetris.model;

public interface TetriminoCellVisitor{
  void visit(int gridX, int gridY, Boolean hasCell, TetriminoType type);
}
