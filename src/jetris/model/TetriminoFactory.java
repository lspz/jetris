package jetris.model;

import java.util.EnumMap;
import java.util.Random;

import jetris.util.CircularList;

public class TetriminoFactory 
{
  private EnumMap<TetriminoType, Tetrimino> prototypes;
  private TetriminoType[] tetriminoTypes;
  private Random randomiser;

  public TetriminoFactory () {
    prototypes = new EnumMap<TetriminoType, Tetrimino>(TetriminoType.class);
    randomiser = new Random();

    addI();
    addJ();
    addO();
    addS();
    addZ();
    addT();

    tetriminoTypes = prototypes.keySet().toArray(new TetriminoType[0]);
  }

  public Tetrimino create(TetriminoType type) {
    return prototypes.get(type).clone();  
  }

  public Tetrimino createRandom() {
    int i = randomiser.nextInt(prototypes.size()); // Start from 1
    if (tetriminoTypes[i] == TetriminoType.NONE) {
      return createRandom();
    }
    return create(tetriminoTypes[i]);
  }

  // Populate states
  
  private void addI() {
    int[][] state1 = {
      {0, 0, 0, 0},
      {1, 1, 1, 1},
      {0, 0, 0, 0},
      {0, 0, 0, 0}
    };

    int[][] state2 = {
      {0, 1, 0, 0},
      {0, 1, 0, 0},
      {0, 1, 0, 0},
      {0, 1, 0, 0}
    };

    CircularList<TetriminoState> stateList = new CircularList<TetriminoState>();
    stateList.add(new TetriminoState(state1));
    stateList.add(new TetriminoState(state2));

    prototypes.put(
      TetriminoType.I, 
      new Tetrimino(TetriminoType.I, stateList, 4, 4)
    );
  }

  private void addJ() {
    int[][] state1 = {
      {0, 1, 0},
      {0, 1, 0},
      {1, 1, 0}
    };

    int[][] state2 = {
      {1, 0, 0},
      {1, 1, 1},
      {0, 0, 0},
    };

    int[][] state3 = {
      {0, 1, 1},
      {0, 1, 0},
      {0, 1, 0}
    };

    int[][] state4 = {
      {0, 0, 0},
      {1, 1, 1},
      {0, 0, 1}
    };

    CircularList<TetriminoState> stateList = new CircularList<TetriminoState>();
    stateList.add(new TetriminoState(state1));
    stateList.add(new TetriminoState(state2));
    stateList.add(new TetriminoState(state3));
    stateList.add(new TetriminoState(state4));

    prototypes.put(
      TetriminoType.J, 
      new Tetrimino(TetriminoType.J, stateList, 3, 3)
    );
  }

  private void addL() {
    int[][] state1 = {
      {0, 1, 0},
      {0, 1, 0},
      {0, 1, 1}
    };

    int[][] state2 = {
      {0, 0, 0},
      {1, 1, 1},
      {1, 0, 0}
    };

    int[][] state3 = {
      {1, 1, 0},
      {0, 1, 0},
      {0, 1, 0}
    };

    int[][] state4 = {
      {0, 0, 1},
      {1, 1, 1},
      {0, 0, 0}
    };

    CircularList<TetriminoState> stateList = new CircularList<TetriminoState>();
    stateList.add(new TetriminoState(state1));
    stateList.add(new TetriminoState(state2));
    stateList.add(new TetriminoState(state3));
    stateList.add(new TetriminoState(state4));

    prototypes.put(
      TetriminoType.L, 
      new Tetrimino(TetriminoType.L, stateList, 3, 3)
    );
  }

  private void addO() {
    int[][] state1 = {
      {1, 1},
      {1, 1}
    };

    CircularList<TetriminoState> stateList = new CircularList<TetriminoState>();
    stateList.add(new TetriminoState(state1));

    prototypes.put(
      TetriminoType.O, 
      new Tetrimino(TetriminoType.O, stateList, 2, 2)
    );
  }

  private void addS() {
    int[][] state1 = {
      {0, 1, 1},
      {1, 1, 0},
      {0, 0, 0}
    };

    int[][] state2 = {
      {0, 1, 0},
      {0, 1, 1},
      {0, 0, 1}
    };

    int[][] state3 = {
      {0, 1, 1},
      {1, 1, 0},
      {0, 0, 0}
    };

    int[][] state4 = {
      {1, 0, 0},
      {1, 1, 0},
      {0, 1, 0}
    };

    CircularList<TetriminoState> stateList = new CircularList<TetriminoState>();
    stateList.add(new TetriminoState(state1));
    stateList.add(new TetriminoState(state2));
    stateList.add(new TetriminoState(state3));
    stateList.add(new TetriminoState(state4));

    prototypes.put(
      TetriminoType.S, 
      new Tetrimino(TetriminoType.S, stateList, 3, 3)
    );
  }

  private void addZ() {
    int[][] state1 = {
      {1, 1, 0},
      {0, 1, 1},
      {0, 0, 0}
    };

    int[][] state2 = {
      {0, 0, 1},
      {0, 1, 1},
      {0, 1, 0}
    };

    int[][] state3 = {
      {1, 1, 0},
      {0, 1, 1},
      {0, 0, 0}
    };

    int[][] state4 = {
      {0, 1, 0},
      {1, 1, 0},
      {1, 0, 0}
    };

    CircularList<TetriminoState> stateList = new CircularList<TetriminoState>();
    stateList.add(new TetriminoState(state1));
    stateList.add(new TetriminoState(state2));
    stateList.add(new TetriminoState(state3));
    stateList.add(new TetriminoState(state4));

    prototypes.put(
      TetriminoType.Z, 
      new Tetrimino(TetriminoType.Z, stateList, 3, 3)
    );
  }

  private void addT() {
    int[][] state1 = {
      {0, 1, 0},
      {1, 1, 1},
      {0, 0, 0}
    };

    int[][] state2 = {      
      {0, 1, 0},
      {0, 1, 1},
      {0, 1, 0}
    };

    int[][] state3 = {
      {0, 0, 0},
      {1, 1, 1},
      {0, 1, 0}
    };

    int[][] state4 = {
      {0, 1, 0},
      {1, 1, 0},
      {0, 1, 0}
    };

    CircularList<TetriminoState> stateList = new CircularList<TetriminoState>();
    stateList.add(new TetriminoState(state1));
    stateList.add(new TetriminoState(state2));
    stateList.add(new TetriminoState(state3));
    stateList.add(new TetriminoState(state4));

    prototypes.put(
      TetriminoType.T, 
      new Tetrimino(TetriminoType.T, stateList, 3, 3)
    );
  }
}