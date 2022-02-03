package commands;

import environment.Environment;
import game.Game;

public class FaceSouthCommand implements Commands {
  private Environment environment;

  public FaceSouthCommand(Environment e) {
    environment = e;
  }

  /**
   * excecutes the face south command
   */
  public void execute() {
    int row = environment.getSelectedRow();
    int col = environment.getSelectedCol();
    Game ui = environment.getGameUi();

    if (environment.getLifeForm(row, col) != null) {
      environment.getLifeForm(row, col).setDirection(2);
      if (ui != null) {
        ui.drawCell(row, col);
      }
    }
  }
}
