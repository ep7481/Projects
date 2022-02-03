package state;

import exceptions.EnvironmentException;
import lifeform.LifeForm;

import java.util.Random;

import environment.Environment;

public class HasWeaponState extends ActionState {

  public HasWeaponState(AiContext context) {
    super(context);
  }

  @Override
  public void executeAction() {
    int dir = context.getLifeForm().getDirection();
    Environment e = context.getEnvironment();
    Random r = new Random();
    int row = context.getLifeForm().getRow();
    int col = context.getLifeForm().getCol();
    int maxDistance = context.getLifeForm().getWeapon().getMaxRange();

    if (context.getLifeForm().getCurrentLifePoints() == 0) {
      context.setCurrentState(context.getDeadState());
    } else if (context.getLifeForm().getWeapon().getCurrentAmmo() == 0) {
      context.setCurrentState(context.getOutOfAmmoState());
    } else if (dir == 0 && context.getLifeForm().getRow() > 0) {
      System.out.print("Test " + dir);
      for (int i = row; i <= context.getEnvironment().getNumRows() - 1; i++) {
        try {
          if (e.getLifeForm(i, col) != null 
              && e.getDistance(row, col, i, col) <= maxDistance
              && e.getLifeForm(i, col) != e.getLifeForm(row, col)) {
            if (e.getLifeForm(i, col) != null) {
              LifeForm form = e.getLifeForm(i, col);
              e.getLifeForm(row, col).attack(form);
            }
          }
        } catch (EnvironmentException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
      }
    } else if (dir == 1 && context.getLifeForm()
        .getCol() < e.getNumCols() - 1) {
      System.out.print("Test " + dir);

      for (int i = col; i < context.getEnvironment().getNumCols(); i++) {
        try {
          if (e.getLifeForm(row, i) != null 
              && e.getDistance(row, col, row, i) < maxDistance
              && e.getLifeForm(i, col) != e.getLifeForm(row, col)) {
            LifeForm form = e.getLifeForm(row, i);
            e.getLifeForm(row, col).attack(form);
          }
        } catch (EnvironmentException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }

      }

    } else if (dir == 2 && context.getLifeForm()
        .getRow() < context.getEnvironment().getNumRows() - 1) {
      System.out.print("Test " + dir);

      for (int i = context.getEnvironment().getNumRows() - 1; i > row; i--) {
        try {
          if (e.getLifeForm(i, col) != null 
              && e.getDistance(row, col, i, col) <= maxDistance
              && e.getLifeForm(i, col) != e.getLifeForm(row, col)) {
            LifeForm form = e.getLifeForm(i, col);
            e.getLifeForm(row, col).attack(form);
          }
        } catch (EnvironmentException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
      }
    } else if (dir == 3 && context.getLifeForm().getCol() != 0) {
      System.out.print("Test " + dir);

      for (int i = 0; i < col; i++) {
        try {
          if (e.getLifeForm(row, i) != null 
              && e.getDistance(row, col, row, i) < maxDistance
              && e.getLifeForm(i, col) != e.getLifeForm(row, col)) {
            LifeForm form = e.getLifeForm(row, i);
            e.getLifeForm(row, col).attack(form);
          }
        } catch (EnvironmentException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
      }
    }

    int direction = r.nextInt(4);
    while (direction == context.getLifeForm().getDirection()) {
      direction = r.nextInt(4);
    }

    context.getLifeForm().setDirection(direction);
    e.move(context.getLifeForm().getRow(), context.getLifeForm().getCol());
  }
}
