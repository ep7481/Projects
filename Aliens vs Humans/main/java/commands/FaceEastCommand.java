package commands;

import environment.Environment;
import game.Game;

public class FaceEastCommand implements Commands {
  private Environment environment;

  public FaceEastCommand(Environment e) {
    environment = e;
  }

  /**
   * excecutes the face east command
   */
  public void execute() {
    int row = environment.getSelectedRow();
    int col = environment.getSelectedCol();
    Game ui = environment.getGameUi();

    if (environment.getLifeForm(row, col) != null) {
      environment.getLifeForm(row, col).setDirection(1);
      if (ui != null) {
        ui.drawCell(row, col);
      }
    }
  }
}
