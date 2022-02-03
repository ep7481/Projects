package commands;

import environment.Environment;
import game.Game;
import lifeform.LifeForm;
import weapon.Weapon;

public class PickWeaponOneCommand implements Commands {
  private Environment environment;

  public PickWeaponOneCommand(Environment e) {
    environment = e;
  }

  /**
   * excecutes the pick up weapon 1 command
   */
  public void execute() {
    int row = environment.getSelectedRow();
    int col = environment.getSelectedCol();
    LifeForm form = environment.getLifeForm(row, col);
    Game ui = environment.getGameUi();

    System.out.print("Weapon1");
    if (environment.getLifeForm(row, col) != null) {
      Weapon pickUp = environment.getCell(row, col).getWeapon1();

      if (pickUp != null && form.hasWeapon() == true) {
        Weapon old = environment.getLifeForm(row, col).dropWeapon();
        environment.getLifeForm(row, col).pickUpWeapon(pickUp);
        environment.removeWeapon(pickUp, row, col);
        environment.addWeapon(old, row, col);

      } else if (pickUp != null && form.hasWeapon() == false) {
        environment.getLifeForm(row, col).pickUpWeapon(pickUp);
        environment.removeWeapon(pickUp, row, col);
      }
    }

    if (ui != null) {
      ui.drawCell(row, col);
      ui.drawUiText(form);
      ui.printStats(environment.getGameCell(row, col));
    }
  }
}
