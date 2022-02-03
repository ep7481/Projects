package commands;

import environment.Environment;
import exceptions.EnvironmentException;
import exceptions.WeaponException;
import game.Game;

public class AttackCommand implements Commands {
  private Environment environment;
  Game xd;

  public AttackCommand(Environment e) {
    environment = e;
    xd = e.getGameUi();
  }

  @Override
  public void execute() throws WeaponException, EnvironmentException {
    int row = environment.getSelectedRow();
    int col = environment.getSelectedCol();
    int dir = environment.getLifeForm(row, col).getDirection();

    if (dir == 0) {
      int y = row - 1;
      double z = environment.getDistance(row, col, y, col);
      if (environment.getLifeForm(row, col) != null 
          && environment.getLifeForm(row, col).hasWeapon()) {
        for (int i = row - 1; i >= 0; i--) {
          if (environment.getLifeForm(i, col) != null) {
            double x = environment.getDistance(row, col, i, col);
            if (environment.getLifeForm(row, col).getWeapon().getMaxRange() >= x) {
              environment.getLifeForm(row, col)
                .attack(environment.getLifeForm(i, col), (int) x);
              environment.checkDead(row, col);
              if (xd != null) {
                xd.printStats(xd.getCell(row, col));
              }
              break;
            }
          }
        }

      } else if (environment.getLifeForm(row, col) != null && z <= 5) {
        environment.getLifeForm(row, col).attack(environment.getLifeForm(y, col));
        environment.checkDead(row, col);
        if (xd != null) {
          xd.printStats(xd.getCell(row, col));
        }
      }
    }

    if (dir == 1) {
      int y = col + 1;
      double z = environment.getDistance(row, col, row, y);
      if (environment.getLifeForm(row, col) != null 
          && environment.getLifeForm(row, col).hasWeapon()) {
        for (int i = col + 1; i <= environment.getNumCols(); i++) {
          if (environment.getLifeForm(row, i) != null) {
            double x = environment.getDistance(row, col, row, i);
            if (environment.getLifeForm(row, col).getWeapon().getMaxRange() >= x) {
              environment.getLifeForm(row, col)
                .attack(environment.getLifeForm(row, i), (int) x);
              environment.checkDead(row, col);
              if (xd != null) {
                xd.printStats(xd.getCell(row, col));
              }
              break;
            }
          }
        }

      } else if (environment.getLifeForm(row, col) != null && z <= 5) {
        environment.getLifeForm(row, col).attack(environment.getLifeForm(row, y));
        environment.checkDead(row, col);
        if (xd != null) {
          xd.printStats(xd.getCell(row, col));
        }
      }
    }

    if (dir == 2) {
      int y = row + 1;
      double z = environment.getDistance(row, col, y, col);
      if (environment.getLifeForm(row, col) != null 
          && environment.getLifeForm(row, col).hasWeapon()) {
        for (int i = row + 1; i <= environment.getNumRows(); i++) {
          if (environment.getLifeForm(i, col) != null) {
            double x = environment.getDistance(row, col, i, col);
            if (environment.getLifeForm(row, col).getWeapon().getMaxRange() >= x) {
              environment.getLifeForm(row, col)
                .attack(environment.getLifeForm(i, col), (int) x);
              environment.checkDead(row, col);
              if (xd != null) {
                xd.printStats(xd.getCell(row, col));
              }
              break;
            }
          }
        }

      } else if (environment.getLifeForm(row, col) != null && z <= 5) {
        environment.getLifeForm(row, col).attack(environment.getLifeForm(y, col));
        environment.checkDead(row, col);
        if (xd != null) {
          xd.printStats(xd.getCell(row, col));
        }
      }
    }

    if (dir == 3) {
      int y = col - 1;
      double z = environment.getDistance(row, col, row, y);
      if (environment.getLifeForm(row, col) != null 
          && environment.getLifeForm(row, col).hasWeapon()) {
        for (int i = col - 1; i >= 0; i++) {
          if (environment.getLifeForm(row, i) != null) {
            double x = environment.getDistance(row, col, row, i);
            if (environment.getLifeForm(row, col).getWeapon().getMaxRange() >= x) {
              environment.getLifeForm(row, col).attack(environment.getLifeForm(row, i), (int) x);
              environment.checkDead(row, col);
              if (xd != null) {
                xd.printStats(xd.getCell(row, col));
              }
              break;
            }
          }
        }

      } else if (environment.getLifeForm(row, col) != null && z <= 5) {
        environment.getLifeForm(row, col).attack(environment.getLifeForm(row, y));
        environment.checkDead(row, col);
        if (xd != null) {
          xd.printStats(xd.getCell(row, col));
        }
      }
    }

  }

}
