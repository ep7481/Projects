package commands;

import environment.Environment;
import game.Game;
import lifeform.LifeForm;
import weapon.Weapon;

public class PickWeaponTwoCommand implements Commands {
  private Environment environment;

  public PickWeaponTwoCommand(Environment e) {
    environment = e;
  }

  /**
   * excecutes the pick up weapon 2 command
   */
  public void execute() {
    int row = environment.getSelectedRow();
    int col = environment.getSelectedCol();
    LifeForm form = environment.getCell(row, col).getLifeForm();
    Game ui = environment.getGameUi();

    System.out.print("Weapon 2");
    if (environment.getLifeForm(row, col) != null && environment.getWeapons(row, col).length == 2) {
      Weapon pickUp = environment.getCell(row, col).getWeapon2();

      if (pickUp != null) {
        Weapon old = environment.getLifeForm(row, col).dropWeapon();
        environment.getLifeForm(row, col).pickUpWeapon(pickUp);
        environment.removeWeapon(pickUp, row, col);
        environment.addWeapon(old, row, col);

        if (ui != null) {
          ui.drawCell(form.getRow(), form.getCol());
          ui.drawUiText(form);
          ui.printStats(environment.getGameCell(row, col));
        }
      }
    }
  }
}