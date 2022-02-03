package commands;

import environment.Environment;
import game.Game;
import game.GameCell;
import lifeform.LifeForm;

public class FaceNorthCommand implements Commands {
  private Environment environment;

  public FaceNorthCommand(Environment e) {
    environment = e;
  }
  
  /**
   * excecutes the face north command
   */
  public void execute() {
    int row = environment.getSelectedRow();
    int col = environment.getSelectedCol();
    Game ui = environment.getGameUi();

    if (environment.getLifeForm(row, col) != null) {
      environment.getLifeForm(row, col).setDirection(0);
      if (ui != null) {
        ui.drawCell(row, col);

      }
    }
  }
}
