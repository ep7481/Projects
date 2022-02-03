package gameplay;

import lifeform.LifeForm;
import weapon.Weapon;

public interface EnvironmentObserver {
  public void updateCell(int row, int col, LifeForm lifeform, Weapon weapon1, Weapon weapon2);
}
