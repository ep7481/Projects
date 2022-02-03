package commands;

import environment.Environment;
import game.Game;
import game.GameCell;

public class MoveCommand implements Commands {
  private Environment environment;
  GameCell cell;

  public MoveCommand(Environment e) {
    environment = e;
  }

  @Override
  public void execute() {
    int row = environment.getSelectedRow();
    int col = environment.getSelectedCol();
    Game ui = environment.getGameUi();
    if (ui != null) {
      cell = environment.getGameCell(row, col);
    }

    if (environment.getLifeForm(row, col) != null) {
      if (ui != null) {
        environment.getGameUi().highlight(cell);
      }

      int x = environment.getCell(row, col).getLifeForm().getDirection();
      System.out.println("Move executed " + x);
      environment.move(row, col);

    }
  }

}
