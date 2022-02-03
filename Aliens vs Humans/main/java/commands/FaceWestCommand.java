package commands;

import environment.Environment;
import game.Game;

public class FaceWestCommand implements Commands {
  private Environment environment;

  public FaceWestCommand(Environment e) {
    environment = e;
  }

  /**
   * excecutes the face west command
   */
  public void execute() {
    int row = environment.getSelectedRow();
    int col = environment.getSelectedCol();
    Game ui = environment.getGameUi();

    if (environment.getLifeForm(row, col) != null) {
      environment.getLifeForm(row, col).setDirection(3);
      if (ui != null) {
        ui.drawCell(row, col);
      }
    }
  }
}
