package commands;

import environment.Environment;
import game.Game;
import weapon.Weapon;

public class ReloadCommand implements Commands {
  private Environment environment;
  Game xd;

  public ReloadCommand(Environment e) {
    environment = e;
    xd = e.getGameUi();
  }

  /**
   * excecutes the reload command
   */
  public void execute() {
    int row = environment.getSelectedRow();
    int col = environment.getSelectedCol();
    if (environment.getLifeForm(row, col) != null 
        && environment.getLifeForm(row, col).hasWeapon()) {
      Weapon weapon = environment.getLifeForm(row, col).getWeapon();
      System.out.println("Before Reload: " 
          + environment.getLifeForm(row, col).getWeapon().getCurrentAmmo());
      weapon.reload();
      if (xd != null) {
        xd.printStats(xd.getCell(row, col));
      }
      System.out.println("Reloaded");
      System.out.println("After Reload: " 
          + environment.getLifeForm(row, col).getWeapon().getCurrentAmmo());
    }
  }
}
