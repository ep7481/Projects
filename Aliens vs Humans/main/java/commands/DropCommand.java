package commands;

import environment.Environment;
import game.Game;
import game.GameCell;
import lifeform.LifeForm;

public class DropCommand implements Commands {
  protected Environment environment;
  protected Game ui;

  public DropCommand(Environment env) {
    environment = env;
    ui = env.getGameUi();
  }
  
  /**
   * excecutes the drop command
   */
  public void execute() {
    if (ui.getHighlighted() != null) {
      GameCell cell = ui.getHighlighted();
      LifeForm form = environment.getCell(cell.getRow(), 
          cell.getCol()).getLifeForm();
      if (form.hasWeapon() == true 
          && environment.getCell(cell.getRow(), cell.getCol()).getWeaponsCount() < 2) {
        environment.dropWeapon(form, ui);
        if (ui != null) {
          ui.drawCell(form.getRow(), form.getCol());
          ui.drawUiText(form);
          ui.printStats(cell);
        }
      }
    }
  }
}
